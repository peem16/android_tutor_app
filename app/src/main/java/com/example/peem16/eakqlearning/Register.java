package com.example.peem16.eakqlearning;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Register  extends AppCompatActivity implements View.OnClickListener {
    private  String keysv = ServerConnect.KEY_SERVER;
    private String jResult;
    private EditText editTxtUsername;
    private EditText editTxtPassword;
    private EditText editTxtFirstname;
    private EditText editTxtLastname;



    private  Processregis regis ;
    private EditText editTxtAge;
    private RadioButton radioBtnMale;
    private RadioButton radioBtnFemale;
    private EditText editTxtEmail;

    private EditText editPhone;
    private EditText editTxtAddress;


    private Button btnConRegister;

    private Button btnCanRegister;
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnConRegister:

                register();

                break;

            case R.id.btnCanRegister:


                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
                finish();


                break;

        }


    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);


        editTxtUsername = (EditText) findViewById(R.id.editTxtUsername);
        editTxtPassword = (EditText) findViewById(R.id.editTxtPassword);
        editTxtFirstname = (EditText) findViewById(R.id.editTxtFirstname);
        editTxtLastname = (EditText) findViewById(R.id.editTxtLastname);
        editTxtAge = (EditText) findViewById(R.id.editTxtAge);
        editTxtEmail = (EditText) findViewById(R.id.editTxtEmail);

        editPhone = (EditText) findViewById(R.id.editPhone);
        editTxtAddress = (EditText) findViewById(R.id.editTxtAddress);

        radioBtnMale = (RadioButton) findViewById(R.id.radioBtnMale);
        radioBtnFemale = (RadioButton) findViewById(R.id.radioBtnFemale);



        btnConRegister = (Button) findViewById(R.id.btnConRegister);
        btnCanRegister = (Button) findViewById(R.id.btnCanRegister);
        btnConRegister.setOnClickListener(this);
        btnCanRegister.setOnClickListener(this);



    }



    private  void register() {
        String user = editTxtUsername.getText().toString();
        String pass = editTxtPassword.getText().toString();

        String first = editTxtFirstname.getText().toString();
        String last = editTxtLastname.getText().toString();

        String age = editTxtAge.getText().toString();
        String email = editTxtEmail.getText().toString();

        String phone = editPhone.getText().toString();
        String address = editTxtAddress.getText().toString();

        String sex  = "";

        if(radioBtnMale.isChecked()==true){

            sex = "Male";

        }else if(radioBtnFemale.isChecked()==true){
            sex = "Female";

        }

        if(user.trim().equals("")){

            Toast.makeText(getApplicationContext(), getString(R.string.validate_username),
                    Toast.LENGTH_LONG).show();
            editTxtUsername.requestFocus();

        }

        if(pass.trim().equals("")){
            Toast.makeText(getApplicationContext(), getString(R.string.validate_password),
                    Toast.LENGTH_LONG).show();
            editTxtPassword.requestFocus();

        }
       if(first.trim().equals("")){
            Toast.makeText(getApplicationContext(), getString(R.string.validate_first),
                    Toast.LENGTH_LONG).show();
            editTxtPassword.requestFocus();

        }
        if(last.trim().equals("")){
            Toast.makeText(getApplicationContext(), getString(R.string.validate_last),
                    Toast.LENGTH_LONG).show();
            editTxtPassword.requestFocus();

        }
        if(age.trim().equals("")){
            Toast.makeText(getApplicationContext(), getString(R.string.validate_age),
                    Toast.LENGTH_LONG).show();
            editTxtPassword.requestFocus();

        }
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(email.trim().equals("")){
            Toast.makeText(getApplicationContext(), getString(R.string.validate_email),
                    Toast.LENGTH_LONG).show();
            editTxtPassword.requestFocus();

        }else if (!email.matches(emailPattern)){

            Toast.makeText(getApplicationContext(), "รูปแบบอีเมล์ไม่ถูกต้อง",
                    Toast.LENGTH_LONG).show();
            editTxtPassword.requestFocus();


        }
        if(phone.trim().equals("")){
            Toast.makeText(getApplicationContext(), getString(R.string.validate_phone),
                    Toast.LENGTH_LONG).show();
            editTxtPassword.requestFocus();

        }

        if(address.trim().equals("")){
            Toast.makeText(getApplicationContext(), getString(R.string.validate_address),
                    Toast.LENGTH_LONG).show();
            editTxtPassword.requestFocus();

        }

        ShowMe.log("user"+user);
        ShowMe.log("pass"+pass);
        ShowMe.log("first"+first);
        ShowMe.log("last"+last);
        ShowMe.log("age"+age);
        ShowMe.log("email"+email);
        ShowMe.log("phone"+phone);
        ShowMe.log("address"+address);
        ShowMe.log("sex"+sex);





if( !(user.trim().equals("")) && !(pass.trim().equals("")) && !(first.trim().equals("")) && !(last.trim().equals("")) && !(age.trim().equals("")) && !(email.trim().equals("")) && !(phone.trim().equals("")) && !(address.trim().equals(""))&& !(sex.trim().equals("")) && email.matches(emailPattern)){
    btnConRegister.setEnabled(false);


    regis = new Processregis();
    if (Build.VERSION.SDK_INT >= 11) {
        //--post GB use serial executor by default --
        regis.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,user,pass,first,last,age,email,phone,address,sex);
    }
    else
    {
        //--GB uses ThreadPoolExecutor by default--
        regis.execute();
    }

}



    }








    private class Processregis extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {


            ParamHolder ph = new ParamHolder();
            ph.add("user",params[0]);
            ph.add("password",params[1]);
            ph.add("first",params[2]);
            ph.add("last",params[3]);
            ph.add("age",params[4]);
            ph.add("email",params[5]);
            ph.add("phone",params[6]);
            ph.add("address",params[7]);
            ph.add("sex",params[8]);



            Initial.httpConn = new ServerConnect(Register.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.REGISTER, ph);



            ShowMe.log("RESULT="+jResult);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);





            try {
                Initial.jObject = new JSONObject(jResult);

                if(Initial.jObject.getString("status").trim().equals("true")){

                    Intent intent = new Intent(Register.this,Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(intent);


                }


            } catch (Exception e) {
                e.printStackTrace();
            }




        }

    }

}
