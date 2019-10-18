package com.example.peem16.samms;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.text.Html;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class Quatationdetail  extends AppCompatActivity {
    private String jResult;
    private TextView textView2;
    private  String keysv = ServerConnect.KEY_SERVER;
    public static final String PARAM_MAP = "map";
    public static final String PARAM_TAG = "tag";
    ProcessDetaill quatdetaill;
    Context ctx;
    private String quat_id,quotation_no,lookup_name,lookup_code;
    private ProcesscCancel quatbtn;
    private  Button btnconfirm,btncancel;
    private String bodyString = "";
    private void getParam() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            HashMap<String, Object> map = (HashMap<String, Object>) bundle.getSerializable(BuyDetail.PARAM_MAP);
            String tag = bundle.getString(BuyDetail.PARAM_TAG);

            ctx = this;

            quat_id = map.get(FieldConstants.id).toString();
            quotation_no = map.get(FieldConstants.quotation_no).toString();
            lookup_code = map.get(FieldConstants.lookup_code).toString();

        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.quatation_detail);
        getParam();
        textView2 = (TextView) findViewById(R.id.textView2);
        btnconfirm = (Button) findViewById(R.id.buttonConfirm);
        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quatbtn = new ProcesscCancel();
                if (Build.VERSION.SDK_INT >= 11) {
                    //--post GB use serial executor by default --
                    quatbtn.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR
                            , quat_id, Initial.SharePref.getID(),"confirm");
                } else {
                    //--GB uses ThreadPoolExecutor by default--
                    quatbtn.execute();
                }
            }
        });

        btncancel = (Button) findViewById(R.id.buttonCancel);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                quatbtn = new ProcesscCancel();
                if (Build.VERSION.SDK_INT >= 11) {
                    //--post GB use serial executor by default --
                    quatbtn.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR
                            , quat_id, Initial.SharePref.getID(),"cancel");
                } else {
                    //--GB uses ThreadPoolExecutor by default--
                    quatbtn.execute();
                }

            }
        });

        if(!lookup_code.equals("PO01")){

            btncancel.setVisibility(View.INVISIBLE);
            btnconfirm.setVisibility(View.INVISIBLE);

        }

        quatdetaill = new ProcessDetaill();
        if (Build.VERSION.SDK_INT >= 11) {
            //--post GB use serial executor by default --
            quatdetaill.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR
                    , quat_id);
        } else {
            //--GB uses ThreadPoolExecutor by default--
            quatdetaill.execute();
        }

    }

    private class ProcessDetaill extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idquta",params[0]);


            Initial.httpConn = new ServerConnect(ctx);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.GetQuatactiondeatill, ph);


            ShowMe.log("RESULT="+jResult);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            try {
                Initial.jObject = new JSONObject(jResult);
                if(Initial.jObject.getString("status").trim().equals("true")) {

                    JSONArray jArray = new JSONArray(Initial.jObject.getString("result"));
                    int size = jArray.length();
                    for(int i=0; i<size ; i++) {

                        JSONObject jsonObject = jArray.getJSONObject(i);
                        bodyString = bodyString+(i+1)+"."+jsonObject.getString("name")+"\n";
                        JSONArray jArray2= new JSONArray(jsonObject.getString("detail"));
                        int size2 = jArray2.length();
                        for(int x=0; x<size2 ; x++) {

                            JSONObject jsonObject2 = jArray2.getJSONObject(x);

                            bodyString = bodyString +"\t\t\t"+ jsonObject2.getString("hardware_name");
                            bodyString = bodyString +"\t\t\t"+ jsonObject2.getString("hardware_price")+"\n";

                        }
                    }

                }
                textView2.setText(bodyString);

            } catch (Exception e) {
                e.printStackTrace();
            }




        }




    }

    private class ProcesscCancel extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
                ph.add("idquta",params[0]);
            ph.add("iduser",params[1]);
                ph.add("status",params[2]);



            Initial.httpConn = new ServerConnect(ctx);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.Quataction, ph);



            ShowMe.log("RESULT="+jResult);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            try {
                Initial.jObject = new JSONObject(jResult);

                btncancel.setEnabled(true);
                btnconfirm.setEnabled(true);

                if (Initial.jObject.getString("status").trim().equals("true")) {

                    Toast.makeText(ctx, "สำเร็จ",
                            Toast.LENGTH_SHORT).show();




                    Intent intent = new Intent(ctx, Quatation.class);

                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    ctx.startActivity(intent);

                } else {

                    Toast.makeText(ctx, "ล้มเหลว",
                            Toast.LENGTH_SHORT).show();
                    btncancel.setEnabled(true);
                    btnconfirm.setEnabled(true);

                }


            } catch (Exception e) {
                e.printStackTrace();
            }








        }




    }
}
