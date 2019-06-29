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

public class Paymentreser extends AppCompatActivity implements  AdapterView.OnItemClickListener {
    private ArrayList<HashMap<String, Object>> objArrayList;
    private String jResult;
    private HashMap<String, Object> objMap;
    private  String keysv = ServerConnect.KEY_SERVER;
    private ArrayAdapter<HashMap<String,Object>> mAdapter;
    private ListView listView;
    private Processgetreser getreser;
    private ProgressBar progressBar;


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        objMap = (HashMap<String, Object>)listView.getItemAtPosition(position);

        Bundle bundle = new Bundle();
        bundle.putString(confirmpayreser.PARAM_TAG,"Coures_detail");
        bundle.putSerializable(confirmpayreser.PARAM_MAP,objMap);

        Intent intent = new Intent(getApplication(), confirmpayreser.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtras(bundle);
//        activity.startActivityForResult(intent, MainActivity_Login.REQUESTCODE_ITEMVIEW);
//  ShowMe.log("asdasdasdas"+objMap);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymentreser);
        listView = (ListView) findViewById(R.id.listview2);
        listView.setOnItemClickListener(this);











        progressBar = (ProgressBar)findViewById(R.id.progressBar3);
        Initial.SharePref = new PreferenceClass(getApplication());
        getreser = new Processgetreser();


        if (Build.VERSION.SDK_INT >= 11) {
            //--post GB use serial executor by default --
            getreser.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Initial.SharePref.getStringIDUserAccount());
        }
        else
        {
            //--GB uses ThreadPoolExecutor by default--
            getreser.execute();
        }

    }






    private class Processgetreser extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idUserAccount",params[0]);


            objArrayList = new ArrayList<HashMap<String,Object>>();
            Initial.httpConn = new ServerConnect(getApplicationContext());
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.getreser, ph);



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

                        objMap.put(FieldConstants.idpay_reserve, jsonObject.getString(FieldConstants.idpay_reserve));
                        objMap.put(FieldConstants.day, jsonObject.getString(FieldConstants.day));
                        objMap.put(FieldConstants.reserve_price, jsonObject.getString(FieldConstants.reserve_price));
                        objMap.put(FieldConstants.name, jsonObject.getString(FieldConstants.name));

                        objArrayList.add(objMap);
//
                    }
                }
                int currentPosition = listView.getLastVisiblePosition();

                listView.setAdapter(null);
                progressBar.setIndeterminate(false);
                progressBar.setVisibility(View.INVISIBLE);
//                AppStatus.setPausing(false);
                mAdapter = new payreserAdapter(getApplicationContext(), R.layout.test, objArrayList);
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
