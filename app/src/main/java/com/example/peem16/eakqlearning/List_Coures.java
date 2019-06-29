package com.example.peem16.eakqlearning;

import android.app.Activity;
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

public class List_Coures  extends AppCompatActivity implements AdapterView.OnItemClickListener {




    private ArrayList<HashMap<String, Object>> objArrayList;
    private  String keysv = ServerConnect.KEY_SERVER;
    private HashMap<String, Object> objMap;
    private String jResult;
    private ArrayAdapter<HashMap<String,Object>> mAdapter;
    private ProcessCoures Showcoures;
    private ListView listView;
    private ProgressBar progressBar;
    private Activity activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rollcall);

        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(this);


        objArrayList = new ArrayList<HashMap<String,Object>>();
        progressBar = (ProgressBar)findViewById(R.id.progressBar2);
        Showcoures = new ProcessCoures();
        if (Build.VERSION.SDK_INT >= 11) {
            //--post GB use serial executor by default --
            Showcoures.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        else
        {
            //--GB uses ThreadPoolExecutor by default--
            Showcoures.execute();
        }



    }



    private class ProcessCoures extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idstudent",Initial.SharePref.getidstudent());



            Initial.httpConn = new ServerConnect(List_Coures.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.Showlistcoures, ph);
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
                if(jObject.getString("status").trim().equals("true")){

                    if(!jObject.getString("result").trim().equals("null")){
                    JSONArray jArray = new JSONArray(jObject.getString("result"));
                    int size = jArray.length();
                    for(int i=0; i<size ; i++){
                        JSONObject jsonObject = jArray.getJSONObject(i);
                        objMap = new HashMap<String, Object>();
                        objMap.put(FieldConstants.title, "1");
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

                        objMap.put(FieldConstants.per_round,jsonObject.getString(FieldConstants.per_round));

    objMap.put(FieldConstants.Idtimetable_deteil,jsonObject.getString(FieldConstants.Idtimetable_deteil));




                        objArrayList.add(objMap);
//                        try {
//                            if(Integer.parseInt(jsonObject.getString(FieldConstants.book_count)) > 0) objArrayList.add(objMap);
//                        }
//                        catch(Exception e){
//                            objArrayList.add(objMap);
//                        }
                    }
                    }

                    if(!jObject.getString("desc").trim().equals("null")){
                        JSONArray jArray2 = new JSONArray(jObject.getString("desc"));
                        int size2 = jArray2.length();
                        for(int i=0; i<size2 ; i++){
                            JSONObject jsonObject = jArray2.getJSONObject(i);
                            objMap = new HashMap<String, Object>();
                            objMap.put(FieldConstants.title, "2");
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

                            objMap.put(FieldConstants.per_round,jsonObject.getString(FieldConstants.per_round));

                            objMap.put(FieldConstants.Idtimetable_deteil,jsonObject.getString(FieldConstants.Idtimetable_deteil));


                            objMap.put(FieldConstants.date,jsonObject.getString(FieldConstants.date));
                            objMap.put(FieldConstants.id_timetable_overtime,jsonObject.getString(FieldConstants.id_timetable_overtime));


                            objArrayList.add(objMap);
//                        try {
//                            if(Integer.parseInt(jsonObject.getString(FieldConstants.book_count)) > 0) objArrayList.add(objMap);
//                        }
//                        catch(Exception e){
//                            objArrayList.add(objMap);
//                        }
                        }
                    }

                }else{

                    View empty = findViewById(R.id.emptyList3);
                    empty.setVisibility(VISIBLE);


                }
                listView.setAdapter(null);
                progressBar.setIndeterminate(false);
                progressBar.setVisibility(View.INVISIBLE);
//                AppStatus.setPausing(false);
                mAdapter = new CategoriesAdapter2(getApplication(), R.layout.test, objArrayList);
                listView.setAdapter(mAdapter);
                mAdapter.notifyDataSetInvalidated();
                listView.invalidateViews();

            } catch (JSONException e) {
                e.printStackTrace();
            }









        }




    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
        objMap = (HashMap<String, Object>)listView.getItemAtPosition(position);

        Bundle bundle = new Bundle();
        bundle.putString(Coures_detail.PARAM_TAG,"Coures_detail");
        bundle.putSerializable(Coures_detail.PARAM_MAP,objMap);

        Intent intent = new Intent(getApplication(), Coures_detail.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtras(bundle);
//        activity.startActivityForResult(intent, MainActivity_Login.REQUESTCODE_ITEMVIEW);
//  ShowMe.log("asdasdasdas"+objMap);
        startActivity(intent);

    }


}
