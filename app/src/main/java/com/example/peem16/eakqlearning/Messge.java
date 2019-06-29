package com.example.peem16.eakqlearning;

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

public class Messge   extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private ProgressBar progressBar;
    private ArrayList<HashMap<String, Object>> objArrayList;
    private  String keysv = ServerConnect.KEY_SERVER;
    private HashMap<String, Object> objMap;
    private String jResult;
    private ProcessMessge Messgee;
    private  ProcessMessge2 Messgee2;
    private ArrayAdapter<HashMap<String,Object>> mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messgelist);


        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(this);

        objArrayList = new ArrayList<HashMap<String,Object>>();
        progressBar = (ProgressBar)findViewById(R.id.progressBar4);




        String sss = Initial.SharePref.getidstudent().toString();

        String sss2 = Initial.SharePref.getStringtutor().toString();


        if(sss.equals("null")){


            Messgee = new ProcessMessge();
            if (Build.VERSION.SDK_INT >= 11) {
                //--post GB use serial executor by default --
                Messgee.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
            else
            {
                //--GB uses ThreadPoolExecutor by default--
                Messgee.execute();
            }


        }else if(sss2.equals("null")){

            Messgee2 = new ProcessMessge2();
            if (Build.VERSION.SDK_INT >= 11) {
                //--post GB use serial executor by default --
                Messgee2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
            else
            {
                //--GB uses ThreadPoolExecutor by default--
                Messgee2.execute();
            }




        }



    }







    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private class ProcessMessge extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idtutor",Initial.SharePref.getStringtutor());



            Initial.httpConn = new ServerConnect(Messge.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.getleaveStuden, ph);
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

                        objMap.put(FieldConstants.id_timetable, jsonObject.getString(FieldConstants.id_timetable));
                        objMap.put(FieldConstants.Start_time, jsonObject.getString(FieldConstants.Start_time));
                        objMap.put(FieldConstants.End_time, jsonObject.getString(FieldConstants.End_time));

                        objMap.put(FieldConstants.name, jsonObject.getString(FieldConstants.name));


                        objMap.put(FieldConstants.Start_date,jsonObject.getString(FieldConstants.Start_date));
                        objMap.put(FieldConstants.End_date,jsonObject.getString(FieldConstants.End_date));
                        objMap.put(FieldConstants.room_number,jsonObject.getString(FieldConstants.room_number));
                        objMap.put(FieldConstants.firstname,jsonObject.getString(FieldConstants.firstname));
                        objMap.put(FieldConstants.lastname,jsonObject.getString(FieldConstants.lastname));
                        objMap.put(FieldConstants.nameGrade,jsonObject.getString(FieldConstants.nameGrade));


                        objMap.put(FieldConstants.Idtimetable_deteil,jsonObject.getString(FieldConstants.Idtimetable_deteil));




                        objArrayList.add(objMap);
//                        try {
//                            if(Integer.parseInt(jsonObject.getString(FieldConstants.book_count)) > 0) objArrayList.add(objMap);
//                        }
//                        catch(Exception e){
//                            objArrayList.add(objMap);
//                        }
                    }
                }else{

                    View empty = findViewById(R.id.emptyList2);
                    empty.setVisibility(VISIBLE);


                }
                listView.setAdapter(null);
                progressBar.setIndeterminate(false);
                progressBar.setVisibility(View.INVISIBLE);
//                AppStatus.setPausing(false);
                mAdapter = new MessgeAdapter(getApplication(), R.layout.list_messge, objArrayList);
                listView.setAdapter(mAdapter);
                mAdapter.notifyDataSetInvalidated();
                listView.invalidateViews();

            } catch (JSONException e) {
                e.printStackTrace();
            }









        }




    }
    private class ProcessMessge2 extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("ids",Initial.SharePref.getidstudent());



            Initial.httpConn = new ServerConnect(Messge.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.getleavetutor, ph);
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

                        objMap.put(FieldConstants.id_timetable, jsonObject.getString(FieldConstants.id_timetable));
                        objMap.put(FieldConstants.Start_time, jsonObject.getString(FieldConstants.Start_time));
                        objMap.put(FieldConstants.End_time, jsonObject.getString(FieldConstants.End_time));

                        objMap.put(FieldConstants.name, jsonObject.getString(FieldConstants.name));


                        objMap.put(FieldConstants.Start_date,jsonObject.getString(FieldConstants.Start_date));
                        objMap.put(FieldConstants.End_date,jsonObject.getString(FieldConstants.End_date));
                        objMap.put(FieldConstants.room_number,jsonObject.getString(FieldConstants.room_number));
                        objMap.put(FieldConstants.firstname,jsonObject.getString(FieldConstants.firstname));
                        objMap.put(FieldConstants.lastname,jsonObject.getString(FieldConstants.lastname));
                        objMap.put(FieldConstants.nameGrade,jsonObject.getString(FieldConstants.nameGrade));


                        objMap.put(FieldConstants.Idtimetable_deteil,jsonObject.getString(FieldConstants.Idtimetable_deteil));




                        objArrayList.add(objMap);
//                        try {
//                            if(Integer.parseInt(jsonObject.getString(FieldConstants.book_count)) > 0) objArrayList.add(objMap);
//                        }
//                        catch(Exception e){
//                            objArrayList.add(objMap);
//                        }
                    }
                }else{

                    View empty = findViewById(R.id.emptyList2);
                    empty.setVisibility(VISIBLE);


                }
                listView.setAdapter(null);
                progressBar.setIndeterminate(false);
                progressBar.setVisibility(View.INVISIBLE);
//                AppStatus.setPausing(false);
                mAdapter = new Messge2Adapter(getApplication(), R.layout.list_messge, objArrayList);
                listView.setAdapter(mAdapter);
                mAdapter.notifyDataSetInvalidated();
                listView.invalidateViews();

            } catch (JSONException e) {
                e.printStackTrace();
            }









        }




    }
}
