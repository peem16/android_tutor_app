package com.example.peem16.eakqlearning;

import android.content.Context;
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

public class listbuydetail extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static final String PARAM_MAP = "map";
    public static final String PARAM_TAG = "tag";
    private ArrayAdapter<HashMap<String,Object>> mAdapter;
    private String jResult;
    private ArrayList<HashMap<String, Object>> objArrayList,objArrayList2;
    private  String keysv = ServerConnect.KEY_SERVER;
    private HashMap<String, Object> objMap;
    public static final int REQUESTCODE_ITEMVIEW = 994;
    private  String idcourse ,name;
    private Processcdetail cd;
    private ListView listView;
    private ProgressBar progressBar;
    Context ctx;

    private HashMap<String,Object> map;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.listbuy);

        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
        objArrayList = PreferenceClass.objArrayLists;
        Initial.SharePref = new PreferenceClass(getApplication());

        int s  = Integer.parseInt(Initial.SharePref.getStringposition())  ;
        objArrayList2 = PreferenceClass.objArrayLists;

        map = objArrayList2.get(s);



        progressBar = (ProgressBar)findViewById(R.id.progressBar4);
        cd = new Processcdetail();
        if (Build.VERSION.SDK_INT >= 11) {
            //--post GB use serial executor by default --
            cd.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,""+map.get(FieldConstants.idcourse));
        }
        else
        {
            //--GB uses ThreadPoolExecutor by default--
            cd.execute();
        }



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {





        objMap = (HashMap<String, Object>)listView.getItemAtPosition(position);

        Bundle bundle = new Bundle();
        bundle.putString(buydetail.PARAM_TAG,"buydetail");
        bundle.putSerializable(buydetail.PARAM_MAP,objMap);

        Intent intent = new Intent(getApplication(), buydetail.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtras(bundle);
//        activity.startActivityForResult(intent, MainActivity_Login.REQUESTCODE_ITEMVIEW);
//  ShowMe.log("asdasdasdas"+objMap);
        startActivity(intent);






    }


    private class Processcdetail extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idc",params[0]);


            objArrayList = new ArrayList<HashMap<String,Object>>();
            Initial.httpConn = new ServerConnect(getApplication());
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.getcd, ph);



            ShowMe.log("RESULT="+jResult);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            try {

                JSONObject jObject = new JSONObject(jResult);
                if(!jObject.getString("result").trim().equals("null")){
                    JSONArray jArray = new JSONArray(jObject.getString("result"));
                    int size = jArray.length();
                    for(int i=0; i<size ; i++){
                        JSONObject jsonObject = jArray.getJSONObject(i);
                        objMap = new HashMap<String, Object>();
                        objMap.put(FieldConstants.nameGrade, jsonObject.getString(FieldConstants.nameGrade));
                        objMap.put(FieldConstants.Idcourse_detail, jsonObject.getString(FieldConstants.Idcourse_detail));
                        objMap.put(FieldConstants.amount_courses, jsonObject.getString(FieldConstants.amount_courses));

                        objMap.put(FieldConstants.price, jsonObject.getString(FieldConstants.price));




                        objMap.put(FieldConstants.per_round, jsonObject.getString(FieldConstants.per_round));
                        objMap.put(FieldConstants.note, jsonObject.getString(FieldConstants.note));
                        objMap.put(FieldConstants.Time_length, jsonObject.getString(FieldConstants.Time_length));
                        objMap.put(FieldConstants.Isgroup, jsonObject.getString(FieldConstants.Isgroup));
                        objMap.put(FieldConstants.idtype, jsonObject.getString(FieldConstants.idtype));



                        objArrayList.add(objMap);




                    }
                    listView.setAdapter(null);


                    mAdapter = new detailCouresAdapter(getApplication(), R.layout.test3, objArrayList);
                    listView.setAdapter(mAdapter);

                    mAdapter.notifyDataSetInvalidated();
                    listView.invalidateViews();
                    progressBar.setIndeterminate(false);
                    progressBar.setVisibility(View.INVISIBLE);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }









        }




    }



}
