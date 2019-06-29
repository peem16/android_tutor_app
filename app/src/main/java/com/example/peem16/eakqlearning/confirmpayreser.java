package com.example.peem16.eakqlearning;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static com.example.peem16.eakqlearning.Initial.SharePref;
import static com.example.peem16.eakqlearning.ServerConnect.KEY_SERVER2;

public class confirmpayreser extends AppCompatActivity implements View.OnClickListener {
    Bitmap bitmap;
    final int CODE_GALLERY_REQUEST = 999;
    private Uri filePath ;
    private TextView TxtFlie,TxtFlies,coursename,coursename2,coursename3;
    public static final String PARAM_MAP = "map";
    public static final String PARAM_TAG = "tag";
    public String strfile ;
    private Button btnChoose,btnsent;
    Context ctx;
    private String idpay_reserve,day,reserve_price,name;


    String serverUrl = KEY_SERVER2+"upload_pic_slip_reser.php";
    private void getParam() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            HashMap<String, Object> map = (HashMap<String, Object>) bundle.getSerializable(Coures_detail.PARAM_MAP);
            String tag = bundle.getString(Coures_detail.PARAM_TAG);

            ctx = this;


            idpay_reserve = map.get(FieldConstants.idpay_reserve).toString();
            day = map.get(FieldConstants.day).toString();
            reserve_price = map.get(FieldConstants.reserve_price).toString();
            name = map.get(FieldConstants.name).toString();




        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getParam();

        setContentView(R.layout.payreserdetail);

        coursename = (TextView) findViewById(R.id.coursename);
        coursename.setText(name);

        coursename2 = (TextView) findViewById(R.id.date);
        coursename2.setText(day);

        coursename3 = (TextView) findViewById(R.id.price);
        coursename3.setText(reserve_price);


        TxtFlies = (TextView) findViewById(R.id.txtfile);
        btnChoose = (Button) findViewById(R.id.button);
        btnsent = (Button) findViewById(R.id.button4);

        btnChoose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(confirmpayreser.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        CODE_GALLERY_REQUEST




                );

            }
        });
        btnsent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String file = TxtFlies.getText().toString();
                if(file.trim().equals("")){

                    Toast.makeText(getApplicationContext(), getString(R.string.validate_pic),
                            Toast.LENGTH_LONG).show();


                }else{

                    btnsent.setEnabled(false);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            ShowMe.log(response);

                            strfile =  response;
                            try {
                                Initial.jObject = new JSONObject(strfile);
                                btnsent.setEnabled(true);

                                if (Initial.jObject.getString("status").trim().equals("true")) {


                                    Toast.makeText(getApplicationContext(), "ชำระการจองเสร็จสิ้น",
                                            Toast.LENGTH_LONG).show();


                                    Intent intent = new Intent(confirmpayreser.this,MainActivity_Login.class);
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
                    }, new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){

                            ShowMe.log("error"+error.toString());
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            String imageDate = imageToString(bitmap);
                            params.put("image", imageDate);
                            params.put("idUserAccount", SharePref.getStringIDUserAccount());
                            params.put("idpay", idpay_reserve);


//                        params.put("nameimage",""+filePath.getPath());
//                        ShowMe.log("para"+params);


                            return params;

                        }

                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(confirmpayreser.this);
                    requestQueue.add(stringRequest);


                }



            }
        });

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

                    TxtFlies.setText(fruit);


                }


//                TxtFlie.setText(filePath.getPath().toString());


            }catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onClick(View v) {

    }






}
