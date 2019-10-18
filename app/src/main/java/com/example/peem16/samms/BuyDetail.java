package com.example.peem16.samms;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;

public class BuyDetail  extends AppCompatActivity {

    public static final String PARAM_MAP = "map";
    public static final String PARAM_TAG = "tag";
    Context ctx;
    private static String software_name,software_detail,software_price,is_hardware_set,software_id;
    private Dialog dialog;
    private Button btnBack , btnBuy;
    private TextView headertxt, detailtxt,pricetxt,btnConfirm,btnCancelbuy,textprice;
    private EditText editamout;
    private  String keysv = ServerConnect.KEY_SERVER;
    ProcesscAddbacket addbacket;
    private String jResult;
    private void getParam() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            HashMap<String, Object> map = (HashMap<String, Object>) bundle.getSerializable(BuyDetail.PARAM_MAP);
            String tag = bundle.getString(BuyDetail.PARAM_TAG);

            ctx = this;

            software_id = map.get(FieldConstants.id).toString();

            software_name = map.get(FieldConstants.software_name).toString();
            software_detail = map.get(FieldConstants.software_detail).toString();



            software_price = map.get(FieldConstants.software_price).toString();

        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_buydetail);
        getParam();

        headertxt = (TextView) findViewById(R.id.header);
        detailtxt = (TextView) findViewById(R.id.detail);
        pricetxt = (TextView) findViewById(R.id.price);

        editamout = (EditText) findViewById(R.id.editText2);

        headertxt.setText(software_name);
        detailtxt.setText(software_detail);
        pricetxt.setText("Price "+software_price+" Bath");

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyDetail.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        btnBuy = (Button) findViewById(R.id.btnbuy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amout = editamout.getText().toString();


                if (amout.trim().equals("")) {

                    Toast.makeText(getApplicationContext(), getString(R.string.validate_price),
                            Toast.LENGTH_SHORT).show();

                }

                if (!(amout.trim().equals(""))) {

                    showDialog();
                }
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


        dialog = new Dialog(this.ctx);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);
        btnConfirm = (TextView) dialog.findViewById(R.id.btnpurc);

        textprice = (TextView) dialog.findViewById(R.id.textView);
        Double number = Double.parseDouble(software_price) * Double.parseDouble(editamout.getText().toString()) ;
        textprice.setText("ราคา "+number.toString()+" Bath");

        btnConfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {



                    Double number = Double.parseDouble(software_price) * Double.parseDouble(editamout.getText().toString());

                    btnConfirm.setEnabled(false);


                    addbacket = new ProcesscAddbacket();
                    if (Build.VERSION.SDK_INT >= 11) {
                        //--post GB use serial executor by default --
                        addbacket.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR
                                , software_id, Initial.SharePref.getID(), number.toString(), editamout.getText().toString());
                    } else {
                        //--GB uses ThreadPoolExecutor by default--
                        addbacket.execute();
                    }

            }
        });




        btnCancelbuy = (TextView) dialog.findViewById(R.id.btnmain);

        btnCancelbuy.setOnClickListener(cancel);


        dialog.show();




    }

    private class ProcesscAddbacket extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idsoft",params[0]);
            ph.add("iduser",params[1]);
            ph.add("pricesum",params[2]);
            ph.add("amount",params[3]);



            Initial.httpConn = new ServerConnect(ctx);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.Addbacket, ph);



            ShowMe.log("RESULT="+jResult);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            try {
                Initial.jObject = new JSONObject(jResult);

                btnConfirm.setEnabled(true);

                if (Initial.jObject.getString("status").trim().equals("true")) {

                    dialog.dismiss();
                    Toast.makeText(ctx, "สำเร็จ",
                            Toast.LENGTH_SHORT).show();




                    Intent intent = new Intent(ctx, MainActivity.class);

                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    ctx.startActivity(intent);

                } else {

                    Toast.makeText(ctx, "ล้มเหลว",
                            Toast.LENGTH_SHORT).show();
                    btnConfirm.setEnabled(true);

                }


            } catch (Exception e) {
                e.printStackTrace();
            }








        }




    }

}
