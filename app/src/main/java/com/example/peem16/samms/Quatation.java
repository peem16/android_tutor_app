package com.example.peem16.samms;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Quatation  extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private String jResult;
    private  String keysv = ServerConnect.KEY_SERVER;
    private ProcessgetQuatation getQuatation;
    private Button btnback;
    private ArrayList<HashMap<String, Object>> objArrayList;
    private HashMap<String, Object> objMap;
    private ArrayAdapter<HashMap<String, Object>> mAdapter;
    private ListView listView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quatation);

        btnback = (Button) findViewById(R.id.back);
        Initial.SharePref = new PreferenceClass(this);

        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Quatation.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        getQuatation = new ProcessgetQuatation();
        if (Build.VERSION.SDK_INT >= 11) {
            //--post GB use serial executor by default --
            getQuatation.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Initial.SharePref.getID());
        }
        else
        {
            //--GB uses ThreadPoolExecutor by default--
            getQuatation.execute();
        }

    }

    private class ProcessgetQuatation extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {


            objArrayList = new ArrayList<HashMap<String,Object>>();

            ParamHolder ph = new ParamHolder();
            ph.add("iduser",params[0]);


            Initial.httpConn = new ServerConnect(Quatation.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.GetQuat, ph);


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
//
                        objMap.put(FieldConstants.id, jsonObject.getString(FieldConstants.id));
                        objMap.put(FieldConstants.quotation_no, jsonObject.getString(FieldConstants.quotation_no));
                        objMap.put(FieldConstants.quotation_date_start, jsonObject.getString(FieldConstants.quotation_date_start));
                        objMap.put(FieldConstants.lookup_name, jsonObject.getString(FieldConstants.lookup_name));
                        objMap.put(FieldConstants.lookup_code, jsonObject.getString(FieldConstants.lookup_code));

                        objArrayList.add(objMap);

                    }
                }
                listView.setAdapter(null);
                progressBar.setVisibility(View.INVISIBLE);
////                AppStatus.setPausing(false);
                mAdapter = new quatationAdapter(Quatation.this, R.layout.listquatation, objArrayList);
                listView.setAdapter(mAdapter);
//
                mAdapter.notifyDataSetInvalidated();
                listView.invalidateViews();
//                Initial.SharePref.setodj(objArrayList);

            } catch (JSONException e) {
                e.printStackTrace();
            }



        }




    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {





        objMap = (HashMap<String, Object>)listView.getItemAtPosition(position);

        Bundle bundle = new Bundle();
        bundle.putString(Quatationdetail.PARAM_TAG,"Quatationdetail");
        bundle.putSerializable(Quatationdetail.PARAM_MAP,objMap);

        Intent intent = new Intent(getApplication(), Quatationdetail.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
