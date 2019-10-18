package com.example.peem16.samms;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
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

public class Basket extends AppCompatActivity {

private Button btnConfirmbuy , btnback;
    ProcessConfirmBuy confirmbuy;
    ProcessGetBasket getBasket;
    private String jResult;
    private HashMap<String, Object> objMap;
    private ArrayAdapter<HashMap<String, Object>> mAdapter;
    private ListView listView;
    private String jResult2;
    private ProgressBar progressBar;
    private TextView btnConfirm,btnCancelbuy,textprice;
    private ArrayList<HashMap<String, Object>> objArrayList;
    private Dialog dialog;
    private Double pricesumall = 0.0;
    private Integer amountall;
    private  String keysv = ServerConnect.KEY_SERVER;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.basket);
        Initial.SharePref = new PreferenceClass(this);

        btnConfirmbuy = (Button) findViewById(R.id.back);
        listView = (ListView) findViewById(R.id.listview);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        getBasket = new ProcessGetBasket();
        if (Build.VERSION.SDK_INT >= 11) {
            //--post GB use serial executor by default --
            getBasket.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Initial.SharePref.getID());
        }
        else
        {
            //--GB uses ThreadPoolExecutor by default--
            getBasket.execute();
        }


        btnConfirmbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pricesumall==0.0){
                    Toast.makeText(Basket.this, "ไม่มีสินค้า",
                            Toast.LENGTH_SHORT).show();

                }else{
                    showDialog();

                }


            }
        });



        btnback = (Button) findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Basket.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
    private View.OnClickListener cancel = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            dialog.dismiss();
        }
    };
    private void showDialog(){


        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);
        btnConfirm = (TextView) dialog.findViewById(R.id.btnpurc);

        textprice = (TextView) dialog.findViewById(R.id.textView);
        textprice.setText("ราคารวมทั้งหมด "+pricesumall.toString()+" Bath");

        btnConfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {




                btnConfirm.setEnabled(false);


                btnConfirmbuy.setEnabled(false);

                confirmbuy = new ProcessConfirmBuy();
                if (Build.VERSION.SDK_INT >= 11) {
                    //--post GB use serial executor by default --
                    confirmbuy.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Initial.SharePref.getID(),
                            pricesumall.toString(),amountall.toString());
                }
                else
                {
                    //--GB uses ThreadPoolExecutor by default--
                    confirmbuy.execute();
                }


            }
        });




        btnCancelbuy = (TextView) dialog.findViewById(R.id.btnmain);

        btnCancelbuy.setOnClickListener(cancel);


        dialog.show();




    }
    private class ProcessConfirmBuy extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("iduser",params[0]);
            ph.add("pricesumall",params[1]);
            ph.add("amountall",params[2]);


            Initial.httpConn = new ServerConnect(Basket.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.ConfirmBuy, ph);



            ShowMe.log("RESULT="+jResult);



            return null;


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            try {
                Initial.jObject = new JSONObject(jResult);

                btnConfirmbuy.setEnabled(true);

                if (Initial.jObject.getString("status").trim().equals("true")) {
                    dialog.dismiss();

                    Toast.makeText(Basket.this, "สำเร็จ",
                            Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(Basket.this, MainActivity.class);

                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(intent);

                } else {

                    Toast.makeText(Basket.this, "ล้มเหลว",
                            Toast.LENGTH_SHORT).show();
                    btnConfirmbuy.setEnabled(true);

                }


            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }

    private class ProcessGetBasket extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("iduser",params[0]);
            objArrayList = new ArrayList<HashMap<String,Object>>();


            Initial.httpConn = new ServerConnect(Basket.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.Getbasket, ph);



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
                    pricesumall = 0.0;
                    amountall = 0;
                    for(int i=0; i<size ; i++){
                        JSONObject jsonObject = jArray.getJSONObject(i);
                        objMap = new HashMap<String, Object>();
//
                        objMap.put(FieldConstants.id, jsonObject.getString(FieldConstants.id));
                        objMap.put(FieldConstants.amount, jsonObject.getString(FieldConstants.amount));
                        objMap.put(FieldConstants.software_name, jsonObject.getString(FieldConstants.software_name));
                        objMap.put(FieldConstants.software_price, jsonObject.getString(FieldConstants.software_price));
                        objMap.put(FieldConstants.pricesum, jsonObject.getString(FieldConstants.pricesum));


                        pricesumall = pricesumall+Double.parseDouble(jsonObject.getString(FieldConstants.pricesum));

                        amountall  = amountall+Integer.parseInt(jsonObject.getString(FieldConstants.amount));

                        objArrayList.add(objMap);

                    }
                }
                listView.setAdapter(null);
                progressBar.setVisibility(View.INVISIBLE);
////                AppStatus.setPausing(false);
                mAdapter = new basketAdapter(Basket.this, R.layout.listbasket, objArrayList);
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
}
