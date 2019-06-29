package com.example.peem16.eakqlearning;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by peem16 on 4/3/2018.
 */

public class Login extends AppCompatActivity implements View.OnClickListener {
//
private  Processfogetpass foget ;
    private String firstname, lastname,IDUserAccount,idstudent,idtutor,idemp,qr,profilepic;


    private EditText textUser;
    private EditText textPass;
    private TextView btnLogin,btnRegis;
    private String strResult;
    private String[] params;
//    private AsyncLogin aL;
private String jResult;

    private  String keysv = ServerConnect.KEY_SERVER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//
        setContentView(R.layout.activity_login);
////




////
       textUser = (EditText) findViewById(R.id.editTxtUser);
        textPass = (EditText) findViewById(R.id.editTxtPass);
       btnLogin = (TextView) findViewById(R.id.btnLogin);
      btnRegis = (TextView) findViewById(R.id.btnRegister);
        btnLogin.setOnClickListener(this);
        btnRegis.setOnClickListener(this);

        textUser.setText("peem16");
        textPass.setText("123321");

        Initial.SharePref = new PreferenceClass(this);

    }



    private  void Login(){
        String user = textUser.getText().toString();
        String pass = textPass.getText().toString();

        Initial.SharePref.clearPrefs();
        if(user.trim().equals("")){

            Toast.makeText(getApplicationContext(), getString(R.string.validate_username),
                    Toast.LENGTH_LONG).show();
            textUser.requestFocus();

        }
        if(pass.trim().equals("")){
            Toast.makeText(getApplicationContext(), getString(R.string.validate_password),
                    Toast.LENGTH_LONG).show();
            textPass.requestFocus();

        }

        if( !(pass.trim().equals("")) && !(user.trim().equals(""))){


            btnLogin.setEnabled(false);
            foget = new Processfogetpass();
            if (Build.VERSION.SDK_INT >= 11) {
                //--post GB use serial executor by default --
                foget.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,user,pass);
            }
            else
            {
                //--GB uses ThreadPoolExecutor by default--
                foget.execute();
            }

        }











    }

    private class Processfogetpass extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("user",params[0]);
            ph.add("password",params[1]);


            Initial.httpConn = new ServerConnect(Login.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.LOGIN, ph);



            ShowMe.log("RESULT="+jResult);

            try {
                Initial.jObject = new JSONObject(jResult);

                if(Initial.jObject.getString("status").trim().equals("true")){


                    Initial.jArray = new JSONArray(Initial.jObject.getString("result"));
                    Initial.jsonObject = Initial.jArray.getJSONObject(0);

                            firstname = Initial.jsonObject.getString("firstname");
                    lastname = Initial.jsonObject.getString("lastname");


                idstudent = Initial.jsonObject.getString("idstudent");


                idemp = Initial.jsonObject.getString("idemp");


                idtutor = Initial.jsonObject.getString("idtutor");


                IDUserAccount = Initial.jsonObject.getString("IDUserAccount");

                qr = Initial.jsonObject.getString("QR_code");

                profilepic = Initial.jsonObject.getString("profilepic");

                Initial.SharePref.setPrefsAll(IDUserAccount, idtutor, idemp, idstudent, firstname,lastname,qr,profilepic);
            }


            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);









            if (!Initial.SharePref.getStringFirstName().equals("")) {
//                ShowMe.log("Name="+Initial.SharePref.getStringFirstName());



                Intent intent = new Intent(Login.this,MainActivity_Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
                //Toast.makeText(getApplicationContext(), strResult,Toast.LENGTH_LONG).show();
            } else {
                btnLogin.setEnabled(true);

                   Toast.makeText(getApplicationContext(), "Login Fail",
                        Toast.LENGTH_LONG).show();

            }



        }




    }

//    private void Login() {
//        String user = textUser.getText().toString();
//        String pass = textPass.getText().toString();
//
//        if (user.trim().equals("")) {
//            Toast.makeText(getApplicationContext(), getString(R.string.emptyusername),
//                    Toast.LENGTH_LONG).show();
//            textUser.requestFocus();
//        } else if (pass.trim().equals("")) {
//            Toast.makeText(getApplicationContext(), getString(R.string.emptyusername),
//                    Toast.LENGTH_LONG).show();
//            textPass.requestFocus();
//
//        } else {
//
//
//            params = new String[2];
//            params[0] = user;
//            params[1] = pass;
//
//            //new AsyncLogin().execute(user,pass);
//            aL = new AsyncLogin();
//            //aL.execute();
//            if (Build.VERSION.SDK_INT >= 11) { //เวอร์ชั่นโทรศัพท์
//                //--post GB use serial executor by default --
//                aL.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,user,pass);
//            }
//            else
//            {
//                //--GB uses ThreadPoolExecutor by default--
//                aL.execute(user,pass);
//            }
//            //new AsyncLogin().execute(params);
//
//        }
//    }


//
//    private class AsyncLogin extends AsyncTask<String, Integer, String> {
//
//        private String Name, Type,sName,loginName,email;
//        private String Password,Email,UserId,userpic;
//        private String University = "";
//        private ProgressDialog dialog;
//        String keysv = ServerConnect.KEY_SERVER;
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//
//            dialog = new ProgressDialog(Login.this);
//
//            // make the progress bar cancelable
//            dialog.setCancelable(true);
//
//            // set a message text
//            dialog.setMessage("Loading...");
//
//            // show it
//            dialog.show();
//        }
//
//
//
//        @Override
//        protected String doInBackground(String... params) {
//
//
//
//            try {
//
//
//                Name = "";
//                sName = "";
//
//
//                ParamHolder ph = new ParamHolder();
//                ph.add("user", params[0]);
//                ph.add("password",params[1]);
//
//
//
//
//                keysv = ServerConnect.getServerKey();
//
//                //if()
//                ShowMe.log("KEYSV="+keysv+PHPPath.LOGIN);
//                strResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.LOGIN,ph);
//                ShowMe.log("RESULT="+strResult);
//                Initial.jObject = new JSONObject(strResult);
//
//                if(Initial.jObject.getString("status").trim().equals("true")){
//                    Initial.jArray = new JSONArray(Initial.jObject.getString("result"));
//                    Initial.jsonObject = Initial.jArray.getJSONObject(0);
////                    loginName = Initial.jsonObject.getString("user_loginname");
//
////                    Initial.SharePref.setPrefsAll(loginName, Password, Email, University,"72",params[4], Type,UserId,userpic);
//                }
//                //}
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//
//            return null;
//        }
//
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//
//
//
//
//        }
//
//
//
//    }

    private void display(String msg){
        Log.i("benDebugger", msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                Login();

                break;

            case R.id.btnRegister:


                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);



                break;

        }
    }
}
