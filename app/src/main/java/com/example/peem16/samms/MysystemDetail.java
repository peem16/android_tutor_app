package com.example.peem16.samms;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

public class MysystemDetail extends AppCompatActivity {
    private String jResult;
    private  String keysv = ServerConnect.KEY_SERVER;
    public static final String PARAM_MAP = "map";
    public static final String PARAM_TAG = "tag";
    public TextView txtnum , body,btncancelConfirm,btncancelexit,btnpayConfirm,btnpayexit,btnconconpurc,btnconconmain,btnexit;
    private Button btnback,btncontract,btnpay,btncancel;
    private TextView textpriceall;
    public String strfile ;
    private ImageView state1,state2,state3;
    final int CODE_GALLERY_REQUEST = 999;
    private Uri filePath ;
    private TextView btnpayConfirm2 ,statusp;
    private ProcessMysystemDetail MysystemDetail;
    String serverUrl = ServerConnect.KEY_SERVER+"samms/"+"uploadsilp.php";
    Bitmap bitmap;
    private ProgressBar bar1,bar2;
    private String bodystring = "";
    private Dialog dialog;
    Context ctx;
    private Double price = 0.0;
    private ArrayList<HashMap<String, Object>> objArrayList;
    private String customer_order_payment_status_code;
    private String id,customer_order_no,customer_id,software_sum_total,software_sum_qty,software_bill_image,p_status,o_status,payment_first_date,payment_second_date,payment_done_date;
    private void getParam() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            HashMap<String, Object> map = (HashMap<String, Object>) bundle.getSerializable(BuyDetail.PARAM_MAP);
            String tag = bundle.getString(BuyDetail.PARAM_TAG);

            ctx = this;

            id = map.get(FieldConstants.id).toString();
            customer_order_no = map.get(FieldConstants.customer_order_no).toString();
            customer_id = map.get(FieldConstants.customer_id).toString();
            software_sum_total = map.get(FieldConstants.software_sum_total).toString();
            software_sum_qty = map.get(FieldConstants.software_sum_qty).toString();
            software_bill_image = map.get(FieldConstants.software_bill_image).toString();
            p_status = map.get(FieldConstants.p_status).toString();
            o_status = map.get(FieldConstants.o_status).toString();
            payment_first_date = map.get(FieldConstants.payment_first_date).toString();
            payment_second_date = map.get(FieldConstants.payment_second_date).toString();
            payment_done_date = map.get(FieldConstants.payment_done_date).toString();
            customer_order_payment_status_code = map.get(FieldConstants.customer_order_payment_status_code).toString();

        }
    }
    private View.OnClickListener cancel = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            dialog.dismiss();
        }
    };

    private View.OnClickListener confirmpay = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

        }
    };
    private void showDialog(){


        dialog = new Dialog(this.ctx);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogcancel);
        btncancelConfirm = (TextView) dialog.findViewById(R.id.btnpurc);


        btncancelConfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {




            }
        });




        btncancelexit = (TextView) dialog.findViewById(R.id.btnmain);

        btncancelexit.setOnClickListener(cancel);


        dialog.show();




    }
    private void showDialogpay(){


        dialog = new Dialog(this.ctx);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogpay);
        btnpayConfirm = (TextView) dialog.findViewById(R.id.btnpurc);
        btnpayConfirm2 = (TextView) dialog.findViewById(R.id.btnpurc2);

        TextView number =  (TextView) dialog.findViewById(R.id.textView);
        number.setText(price+" Bath");


        btnpayConfirm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                btnpayConfirm2.setEnabled(false);

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            ShowMe.log(response);

                            strfile = response;
                            try {
                                Initial.jObject = new JSONObject(strfile);
                                btnpayConfirm2.setEnabled(true);
                                if (Initial.jObject.getString("status").trim().equals("true")) {


                                    Toast.makeText(getApplicationContext(), "Success",
                                            Toast.LENGTH_LONG).show();


                                    Intent intent = new Intent(MysystemDetail.this, Mysystem.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);

                                } else {

                                    Toast.makeText(getApplicationContext(), "ล้มเหลว",
                                            Toast.LENGTH_LONG).show();

                                }


                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            ShowMe.log("error" + error.toString());
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            String imageDate = imageToString(bitmap);
                            params.put("image", imageDate);
                            params.put("customer_order_no",customer_order_no);
                            params.put("customer_order_payment_status_code",customer_order_payment_status_code);


                            return params;

                        }

                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(MysystemDetail.this);
                    requestQueue.add(stringRequest);

                }

        });




        btnpayConfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                ActivityCompat.requestPermissions(MysystemDetail.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        CODE_GALLERY_REQUEST);


            }
        });




        btnpayexit = (TextView) dialog.findViewById(R.id.btnmain);

        btnpayexit.setOnClickListener(cancel);


        dialog.show();




    }
    private void showDialogContract(){


        dialog = new Dialog(this.ctx);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogcontract);
        btnconconpurc = (TextView) dialog.findViewById(R.id.btnpurc);


        btnconconpurc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {




            }
        });



        btnconconmain = (TextView) dialog.findViewById(R.id.btnmain);

        btnexit = (TextView) dialog.findViewById(R.id.textView4);
        btnexit.setOnClickListener(cancel);



        dialog.show();




    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysystemdetail);

        getParam();

        statusp = (TextView) findViewById(R.id.statusp);
        if(!p_status.equals("null")){
            statusp.setText("สถานะการชำระเงิน "+p_status);

        }else{
            statusp.setText("");
        }
        textpriceall = (TextView) findViewById(R.id.textView5);
        textpriceall.setText("ราคารวม "+software_sum_total+" Bath");
        btnback = (Button) findViewById(R.id.back);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MysystemDetail.this, Mysystem.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        btnpay = (Button) findViewById(R.id.btnpay);
        btnpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogpay();
                btnpayConfirm.setVisibility(View.VISIBLE);
                btnpayConfirm2.setVisibility(View.GONE);
            }
        });
        btncontract = (Button) findViewById(R.id.btncontract);
        btncontract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogContract();
            }
        });
        btncancel = (Button) findViewById(R.id.btncancel);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        txtnum = (TextView) findViewById(R.id.txtnum);
        txtnum.setText("หมายเลขรายการ: "+customer_order_no);
        body = (TextView) findViewById(R.id.body);

        state1 = (ImageView) findViewById(R.id.state1);
        state2 = (ImageView) findViewById(R.id.state2);
        state3 = (ImageView) findViewById(R.id.state3);
        bar1 = (ProgressBar) findViewById(R.id.bar1);
        bar2 = (ProgressBar) findViewById(R.id.bar2);

            price = (Double.parseDouble(software_sum_total)/100)*50 ;
        if(p_status.equals("ชำระบางส่วน (งวดที่ 1)")){
            price = (Double.parseDouble(software_sum_total)/100)*30;

           state1.setImageResource(R.drawable.status_cy);
        }else if(p_status.equals("รอตรวจสอบการชำระ (งวดที่ 1)")){
            btnpay.setVisibility(View.INVISIBLE);

        }else if(p_status.equals("ชำระบางส่วน (งวดที่ 2)")){
            price = (Double.parseDouble(software_sum_total)/100)*20;
            state1.setImageResource(R.drawable.status_cy);
            state2.setImageResource(R.drawable.status_cy);
            bar1.setProgress(100);



        }else if(p_status.equals("รอตรวจสอบการชำระ (งวดที่ 2)")){
            state1.setImageResource(R.drawable.status_cy);
            bar1.setProgress(100);
            btnpay.setVisibility(View.INVISIBLE);


        } else if(p_status.equals("ชำระเต็มจำนวน")) {
            bar1.setProgress(100);
            bar2.setProgress(100);
            state1.setImageResource(R.drawable.status_cy);
            state2.setImageResource(R.drawable.status_cy);
            state3.setImageResource(R.drawable.status_cy);
            btnpay.setVisibility(View.INVISIBLE);
            btncancel.setVisibility(View.INVISIBLE);

        }else if(p_status.equals("รอตรวจสอบการชำระ (งวดที่ 3)")) {
            bar1.setProgress(100);
            bar2.setProgress(100);
            state1.setImageResource(R.drawable.status_cy);
            state2.setImageResource(R.drawable.status_cy);
            btnpay.setVisibility(View.INVISIBLE);
            btncancel.setVisibility(View.INVISIBLE);

        }

        MysystemDetail = new ProcessMysystemDetail();
        if (Build.VERSION.SDK_INT >= 11) {
            //--post GB use serial executor by default --
            MysystemDetail.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,customer_order_no);
        }
        else
        {
            //--GB uses ThreadPoolExecutor by default--
            MysystemDetail.execute();
        }



    }

    private class ProcessMysystemDetail extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("customer_order_no",params[0]);


            Initial.httpConn = new ServerConnect(MysystemDetail.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.getMysystemDetaill, ph);



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
//                        objMap = new HashMap<String, Object>();
//                        objMap.put(FieldConstants.id, jsonObject.getString(FieldConstants.id));
//                        objMap.put(FieldConstants.amount, jsonObject.getString(FieldConstants.amount));
//                        objMap.put(FieldConstants.software_name, jsonObject.getString(FieldConstants.software_name));
//                        objMap.put(FieldConstants.software_price, jsonObject.getString(FieldConstants.software_price));
//                        objMap.put(FieldConstants.pricesum, jsonObject.getString(FieldConstants.pricesum));
                        bodystring = bodystring+(i+1)+"."+jsonObject.getString(FieldConstants.software_name);
                        bodystring = bodystring+"\t\t"+jsonObject.getString(FieldConstants.software_qty)+" จำนวน\n";

                    }
                    body.setText(bodystring);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResult) {
        if (requestCode == CODE_GALLERY_REQUEST) {
            if (grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, CODE_GALLERY_REQUEST);

            }else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access gallery!", Toast.LENGTH_LONG).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResult);
    }
    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] imageByte = outputStream.toByteArray();

        String encodedImage = Base64.encodeToString(imageByte, Base64.DEFAULT);
        return  encodedImage;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        ShowMe.log("RETURN IMAGEPICKER ResultCode="+resultCode);
        if (requestCode == CODE_GALLERY_REQUEST && resultCode == RESULT_OK && data != null){
            filePath = data.getData();
            ShowMe.log("RETURN URI="+filePath);
            try {
                InputStream inputStream = getContentResolver() .openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);

                String[] separated = filePath.getPath().toString().split("/");
                for (String fruit : separated) {
                    // fruit is an element of the `fruits` array.

//                    TxtFlies.setText(fruit);
                    btnpayConfirm.setVisibility(View.GONE);
                    btnpayConfirm2.setVisibility(View.VISIBLE);

                }


//                TxtFlie.setText(filePath.getPath().toString());


            }catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
