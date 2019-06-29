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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static android.view.View.VISIBLE;

public class Paymentbuy extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ArrayList<HashMap<String, Object>> objArrayList;
    private String jResult;
    private HashMap<String, Object> objMap;
    private  String keysv = ServerConnect.KEY_SERVER;
    private ArrayAdapter<HashMap<String,Object>> mAdapter;
    private ListView listView;
    private Processgetbuy getbuy;
    private ProgressBar progressBar;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        objMap = (HashMap<String, Object>)listView.getItemAtPosition(position);


        if(objMap.get(FieldConstants.status.toString()).equals("รอการตรวจสอบการชำระ")){


            Toast.makeText(getApplicationContext(), "รายการนี้อยู่ระหว่างการตรวจสอบ",
                    Toast.LENGTH_SHORT).show();
        }else{
            Bundle bundle = new Bundle();
            bundle.putString(confirmpaybuy.PARAM_TAG,"Coures_detail");
            bundle.putSerializable(confirmpaybuy.PARAM_MAP,objMap);

            Intent intent = new Intent(getApplication(), confirmpaybuy.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtras(bundle);
//        activity.startActivityForResult(intent, MainActivity_Login.REQUESTCODE_ITEMVIEW);
//  ShowMe.log("asdasdasdas"+objMap);
            startActivity(intent);

        }





    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.paymentbuy);
        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(this);



        progressBar = (ProgressBar)findViewById(R.id.progressBar4);
        Initial.SharePref = new PreferenceClass(getApplication());
        getbuy = new Processgetbuy();
        if (Build.VERSION.SDK_INT >= 11) {
            //--post GB use serial executor by default --
            getbuy.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Initial.SharePref.getStringIDUserAccount());
        }
        else
        {
            //--GB uses ThreadPoolExecutor by default--
            getbuy.execute();
        }


    }
    private class Processgetbuy extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idUserAccount",params[0]);


            objArrayList = new ArrayList<HashMap<String,Object>>();
            Initial.httpConn = new ServerConnect(getApplicationContext());
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.getbuy, ph);



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

                        objMap.put(FieldConstants.idbuy, jsonObject.getString(FieldConstants.idbuy));
                        objMap.put(FieldConstants.buy_price, jsonObject.getString(FieldConstants.buy_price));
                        objMap.put(FieldConstants.idTesting, jsonObject.getString(FieldConstants.idTesting));
                        objMap.put(FieldConstants.date_start, jsonObject.getString(FieldConstants.date_start));
                        objMap.put(FieldConstants.date_end, jsonObject.getString(FieldConstants.date_end));
                        objMap.put(FieldConstants.time_start, jsonObject.getString(FieldConstants.time_start));
                        objMap.put(FieldConstants.time_end, jsonObject.getString(FieldConstants.time_end));
                        objMap.put(FieldConstants.amount_courses, jsonObject.getString(FieldConstants.amount_courses));
                        objMap.put(FieldConstants.price, jsonObject.getString(FieldConstants.price));
                        objMap.put(FieldConstants.per_round, jsonObject.getString(FieldConstants.per_round));
                        objMap.put(FieldConstants.note, jsonObject.getString(FieldConstants.note));
                        objMap.put(FieldConstants.Time_length, jsonObject.getString(FieldConstants.Time_length));
                        objMap.put(FieldConstants.name, jsonObject.getString(FieldConstants.name));
                        objMap.put(FieldConstants.day, jsonObject.getString(FieldConstants.day));

                        objMap.put(FieldConstants.nameGrade, jsonObject.getString(FieldConstants.nameGrade));

                        objMap.put(FieldConstants.status, jsonObject.getString(FieldConstants.status));

                        objArrayList.add(objMap);
//                        try {
//                            if(Integer.parseInt(jsonObject.getString(FieldConstants.book_count)) > 0) objArrayList.add(objMap);
//                        }
//                        catch(Exception e){
//                            objArrayList.add(objMap);
//                        }
                    }
                }else{

                    TextView empty  =(TextView) findViewById(R.id.emptyList5);
                    empty.setVisibility(VISIBLE);


                }
                int currentPosition = listView.getLastVisiblePosition();

                listView.setAdapter(null);
                progressBar.setIndeterminate(false);
                progressBar.setVisibility(View.INVISIBLE);
//                AppStatus.setPausing(false);
                mAdapter = new paybuyAdapter(getApplicationContext(), R.layout.test, objArrayList);
                listView.setAdapter(mAdapter);

                mAdapter.notifyDataSetInvalidated();
                listView.invalidateViews();
                listView.setSelection(currentPosition);

                Initial.SharePref.setodj(objArrayList);

            } catch (JSONException e) {
                e.printStackTrace();
            }








        }




    }
}
