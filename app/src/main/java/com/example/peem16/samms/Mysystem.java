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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Mysystem extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private String jResult;
    private  String keysv = ServerConnect.KEY_SERVER;
    private ProcessgetMysystem getmysystem;
    private Button btnback;
    private ArrayList<HashMap<String, Object>> objArrayList;
    private HashMap<String, Object> objMap;
    private ArrayAdapter<HashMap<String, Object>> mAdapter;
    private ListView listView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mysystem);

        btnback = (Button) findViewById(R.id.back);
        Initial.SharePref = new PreferenceClass(this);

        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mysystem.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        getmysystem = new ProcessgetMysystem();
        if (Build.VERSION.SDK_INT >= 11) {
            //--post GB use serial executor by default --
            getmysystem.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Initial.SharePref.getID());
        }
        else
        {
            //--GB uses ThreadPoolExecutor by default--
            getmysystem.execute();
        }


    }

    private class ProcessgetMysystem extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {


            objArrayList = new ArrayList<HashMap<String,Object>>();

            ParamHolder ph = new ParamHolder();
            ph.add("iduser",params[0]);


            Initial.httpConn = new ServerConnect(Mysystem.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.getMysystem, ph);


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
                        objMap.put(FieldConstants.customer_order_no, jsonObject.getString(FieldConstants.customer_order_no));
                        objMap.put(FieldConstants.customer_id, jsonObject.getString(FieldConstants.customer_id));
                        objMap.put(FieldConstants.software_sum_total, jsonObject.getString(FieldConstants.software_sum_total));
                        objMap.put(FieldConstants.software_sum_qty, jsonObject.getString(FieldConstants.software_sum_qty));
                        objMap.put(FieldConstants.software_bill_image, jsonObject.getString(FieldConstants.software_bill_image));
                        objMap.put(FieldConstants.p_status, jsonObject.getString(FieldConstants.p_status));
                        objMap.put(FieldConstants.o_status, jsonObject.getString(FieldConstants.o_status));
                        objMap.put(FieldConstants.payment_first_date, jsonObject.getString(FieldConstants.payment_first_date));
                        objMap.put(FieldConstants.payment_second_date, jsonObject.getString(FieldConstants.payment_second_date));
                        objMap.put(FieldConstants.payment_done_date, jsonObject.getString(FieldConstants.payment_done_date));
                        objMap.put(FieldConstants.customer_order_payment_status_code, jsonObject.getString(FieldConstants.customer_order_payment_status_code));


                        objArrayList.add(objMap);

                    }
                }
                listView.setAdapter(null);
                progressBar.setVisibility(View.INVISIBLE);
////                AppStatus.setPausing(false);
                mAdapter = new mysystemAdapter(Mysystem.this, R.layout.listquatation, objArrayList);
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
        bundle.putString(MysystemDetail.PARAM_TAG,"MysystemDetail");
        bundle.putSerializable(MysystemDetail.PARAM_MAP,objMap);

        Intent intent = new Intent(getApplication(), MysystemDetail.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
