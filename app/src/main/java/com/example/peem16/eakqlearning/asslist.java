package com.example.peem16.eakqlearning;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static android.view.View.VISIBLE;

public class asslist extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private  String keysv = ServerConnect.KEY_SERVER;
    private String jResult;
    private ListView listView;
    private HashMap<String, Object> objMap;
    private ArrayAdapter<HashMap<String,Object>> mAdapter;

    private ProgressBar progressBar;
    private Processtest test;
    private ArrayList<HashMap<String, Object>> objArrayList;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {





        objMap = (HashMap<String, Object>)listView.getItemAtPosition(position);

        Bundle bundle = new Bundle();
        bundle.putString(assShow.PARAM_TAG,"assShow");
        bundle.putSerializable(assShow.PARAM_MAP,objMap);

        Intent intent = new Intent(getApplication(), assShow.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtras(bundle);
//        activity.startActivityForResult(intent, MainActivity_Login.REQUESTCODE_ITEMVIEW);
//  ShowMe.log("asdasdasdas"+objMap);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);

        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(this);


        objArrayList = new ArrayList<HashMap<String,Object>>();
        progressBar = (ProgressBar)findViewById(R.id.progressBar2);
        test = new Processtest();
        if (Build.VERSION.SDK_INT >= 11) {
            //--post GB use serial executor by default --
            test.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
            ph.add("ids",Initial.SharePref.getidstudent());



            Initial.httpConn = new ServerConnect(asslist.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.asslist, ph);
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

                            objMap.put(FieldConstants.idEvaluation, jsonObject.getString(FieldConstants.idEvaluation));
//                        objMap.put(FieldConstants.datetime, jsonObject.getString(FieldConstants.datetime));
                        objMap.put(FieldConstants.Content, jsonObject.getString(FieldConstants.Content));
                        objMap.put(FieldConstants.ask1, jsonObject.getString(FieldConstants.ask1));
                        objMap.put(FieldConstants.ask2, jsonObject.getString(FieldConstants.ask2));
                        objMap.put(FieldConstants.ask3, jsonObject.getString(FieldConstants.ask3));
                        objMap.put(FieldConstants.ask4, jsonObject.getString(FieldConstants.ask4));
                        objMap.put(FieldConstants.ask5, jsonObject.getString(FieldConstants.ask5));


//                        objMap.put(FieldConstants.random_key, jsonObject.getString(FieldConstants.random_key));
                        objMap.put(FieldConstants.name, jsonObject.getString(FieldConstants.name));
                        objMap.put(FieldConstants.nameGrade, jsonObject.getString(FieldConstants.nameGrade));
                        objMap.put(FieldConstants.Start_date, jsonObject.getString(FieldConstants.Start_date));
                        objMap.put(FieldConstants.End_date, jsonObject.getString(FieldConstants.End_date));
                        objMap.put(FieldConstants.Start_time, jsonObject.getString(FieldConstants.Start_time));
                        objMap.put(FieldConstants.End_time, jsonObject.getString(FieldConstants.End_time));
                        objMap.put(FieldConstants.number, jsonObject.getString(FieldConstants.number));

                        objMap.put(FieldConstants.idtype, jsonObject.getString(FieldConstants.idtype));

                        objArrayList.add(objMap);
//                        try {
//                            if(Integer.parseInt(jsonObject.getString(FieldConstants.book_count)) > 0) objArrayList.add(objMap);
//                        }
//                        catch(Exception e){
//                            objArrayList.add(objMap);
//                        }
                    }
                }else{

                    View empty = findViewById(R.id.emptyList4);
                    empty.setVisibility(VISIBLE);


                }
                listView.setAdapter(null);
                progressBar.setIndeterminate(false);
                progressBar.setVisibility(View.INVISIBLE);
//                AppStatus.setPausing(false);
                mAdapter = new test2Adapter(getApplication(), R.layout.test5, objArrayList);
                listView.setAdapter(mAdapter);
                mAdapter.notifyDataSetInvalidated();
                listView.invalidateViews();

            } catch (JSONException e) {
                e.printStackTrace();
            }









        }




    }
}
