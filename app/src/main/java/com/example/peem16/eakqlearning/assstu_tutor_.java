package com.example.peem16.eakqlearning;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static android.view.View.VISIBLE;

public class assstu_tutor_ extends AppCompatActivity implements View.OnClickListener {
    private  String keysv = ServerConnect.KEY_SERVER;
    private String jResult;
    public static final String PARAM_MAP = "map";
    public static final String PARAM_TAG = "tag";
    private HashMap<String, Object> objMap;
    private ArrayAdapter<HashMap<String,Object>> mAdapter;
    private ArrayList<HashMap<String, Object>> objArrayList;
    private Processtest test;
    private String idtimetable;
    private Button cancel;
    Context ctx;

    private ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_assessment_stu);


        cancel   = (Button)findViewById(R.id.button6);

        cancel.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listview);


        Bundle bundle = getIntent().getExtras();



        if (bundle != null) {

            HashMap<String, Object> map = (HashMap<String, Object>) bundle.getSerializable(menuasslisttutor.PARAM_MAP);

            ctx = this;


            idtimetable = map.get(FieldConstants.id_timetable).toString();

        }


        test = new Processtest();
        if (Build.VERSION.SDK_INT >= 11) {
            //--post GB use serial executor by default --
            test.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,idtimetable);
        }
        else
        {
            //--GB uses ThreadPoolExecutor by default--
            test.execute();
        }

    }


    private class Processtest extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("id_timetable",params[0]);

            objArrayList = new ArrayList<HashMap<String,Object>>();
            Initial.httpConn = new ServerConnect(assstu_tutor_.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.assliststu, ph);
            Initial.SharePref = new PreferenceClass(getApplication());


            ShowMe.log("RESULT="+jResult);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            try {
                objArrayList.clear();
                JSONObject jObject = new JSONObject(jResult);
                if(!jObject.getString("result").trim().equals("null")){
                    JSONArray jArray = new JSONArray(jObject.getString("result"));
                    int size = jArray.length();
                    for(int i=0; i<size ; i++){
                        JSONObject jsonObject = jArray.getJSONObject(i);
                        objMap = new HashMap<String, Object>();

                        objMap.put(FieldConstants.firstname, jsonObject.getString(FieldConstants.firstname));
//
                        objMap.put(FieldConstants.lastname, jsonObject.getString(FieldConstants.lastname));
                        objMap.put(FieldConstants.t1, jsonObject.getString(FieldConstants.t1));
                        objMap.put(FieldConstants.bf1, jsonObject.getString(FieldConstants.bf1));
                        objMap.put(FieldConstants.bf2, jsonObject.getString(FieldConstants.bf2));
                        objMap.put(FieldConstants.bf3, jsonObject.getString(FieldConstants.bf3));
                        objMap.put(FieldConstants.bf4, jsonObject.getString(FieldConstants.bf4));
                        objMap.put(FieldConstants.bf5, jsonObject.getString(FieldConstants.bf5));

                        objMap.put(FieldConstants.t2, jsonObject.getString(FieldConstants.t2));
                        objMap.put(FieldConstants.af1, jsonObject.getString(FieldConstants.af1));
                        objMap.put(FieldConstants.af2, jsonObject.getString(FieldConstants.af2));
                        objMap.put(FieldConstants.af3, jsonObject.getString(FieldConstants.af3));
                        objMap.put(FieldConstants.af4, jsonObject.getString(FieldConstants.af4));
                        objMap.put(FieldConstants.af5, jsonObject.getString(FieldConstants.af5));

                        objArrayList.add(objMap);



                    }
                }

                listView.setAdapter(null);

//                AppStatus.setPausing(false);
                mAdapter = new asslist_assstuAdapter(getApplication(), R.layout.ass_stu__list, objArrayList);
                listView.setAdapter(mAdapter);
                mAdapter.notifyDataSetInvalidated();
                listView.invalidateViews();

            } catch (JSONException e) {
                e.printStackTrace();
            }









        }




    }
    @Override
    public void onClick(View v) {

if(v==cancel){



finish();


}

    }
}
