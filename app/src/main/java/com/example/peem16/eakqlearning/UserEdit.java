package com.example.peem16.eakqlearning;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserEdit extends AppCompatActivity implements View.OnClickListener {
    private  String keysv = ServerConnect.KEY_SERVER;
    private String jResult;

    private  Processedituser edituser;
    private EditText editTxtFirstname2,editTxtLastname2,editTxtEmail2,editTxtPhone2,editTxtAddress2;
    private Button btnConEditUser,btnChangPass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_detail);

        editTxtFirstname2 = (EditText) findViewById(R.id.editTxtFirstname2);
        editTxtLastname2 = (EditText) findViewById(R.id.editTxtLastname2);
        editTxtEmail2 = (EditText) findViewById(R.id.editTxtEmail2);
        editTxtPhone2 = (EditText) findViewById(R.id.editTxtPhone2);
        editTxtAddress2 = (EditText) findViewById(R.id.editTxtAddress2);

        btnConEditUser = (Button) findViewById(R.id.btnConEditUser);

        btnChangPass = (Button) findViewById(R.id.btnChangPass);


        btnConEditUser.setOnClickListener(this);
        btnChangPass.setOnClickListener(this);
        Initial.SharePref = new PreferenceClass(this);
    }

    private  void edituer(){
        String first = editTxtFirstname2.getText().toString();
        String last = editTxtLastname2.getText().toString();
        String email = editTxtEmail2.getText().toString();
        String phone = editTxtPhone2.getText().toString();
        String address = editTxtAddress2.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

       ;
        if(first.trim().equals("")){

            Toast.makeText(getApplicationContext(), getString(R.string.validate_first),
                    Toast.LENGTH_LONG).show();
            editTxtFirstname2.requestFocus();

        }
        if(last.trim().equals("")){
            Toast.makeText(getApplicationContext(), getString(R.string.validate_last),
                    Toast.LENGTH_LONG).show();
            editTxtLastname2.requestFocus();

        }
        if(email.trim().equals("")){
            Toast.makeText(getApplicationContext(), getString(R.string.validate_email),
                    Toast.LENGTH_LONG).show();
            editTxtEmail2.requestFocus();

        }else  if (!email.matches(emailPattern)){

            Toast.makeText(getApplicationContext(), "รูปแบบอีเมล์ไม่ถูกต้อง",
                    Toast.LENGTH_LONG).show();
            editTxtEmail2.requestFocus();


        }
        if(phone.trim().equals("")){
            Toast.makeText(getApplicationContext(), getString(R.string.validate_phone),
                    Toast.LENGTH_LONG).show();
            editTxtPhone2.requestFocus();

        }
        if(address.trim().equals("")){
            Toast.makeText(getApplicationContext(), getString(R.string.validate_address),
                    Toast.LENGTH_LONG).show();
            editTxtAddress2.requestFocus();

        }

        if( !(first.trim().equals("")) && !(last.trim().equals(""))&& !(email.trim().equals(""))&& !(phone.trim().equals(""))&& !(address.trim().equals("")) && email.matches(emailPattern)){


            edituser = new Processedituser();
            if (Build.VERSION.SDK_INT >= 11) {
                //--post GB use serial executor by default --
                edituser.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,first,last,email,phone,address,Initial.SharePref .getStringIDUserAccount());
            }
            else
            {
                //--GB uses ThreadPoolExecutor by default--
                edituser.execute();
            }



        }




    }


    private class Processedituser extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {


            ParamHolder ph = new ParamHolder();
            ph.add("first", params[0]);
            ph.add("last", params[1]);
            ph.add("email", params[2]);
            ph.add("phone", params[3]);
            ph.add("address", params[4]);
            ph.add("IDUserAccount", params[5]);
            Initial.httpConn = new ServerConnect(UserEdit.this);
            jResult = Initial.httpConn.getDataFromServer(keysv + PHPPath.edituser, ph);


            ShowMe.log("RESULT=" + jResult);


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            try {
                Initial.jObject = new JSONObject(jResult);

                if (Initial.jObject.getString("status").trim().equals("true")) {


                    Intent intent = new Intent(UserEdit.this, Login.class);
                    startActivity(intent);
                    //Toast.makeText(getApplicationContext(), strResult,Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(getApplicationContext(), "Edit Fail",
                            Toast.LENGTH_LONG).show();

                }


            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnConEditUser:
                edituer();

                break;


        }




    }
}
