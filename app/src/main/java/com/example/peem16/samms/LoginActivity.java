package com.example.peem16.samms;

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

public class LoginActivity extends AppCompatActivity {
    private Button  btnLogin , btnRegis;
    private EditText textUser, textPass;
    private ProcessLogin login ;

    private String jResult;
    private  String keysv = ServerConnect.KEY_SERVER;
    private String firstname,lastname,id,company_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textUser =  (EditText)  findViewById(R.id.editTxtUser);
        textPass =  (EditText)  findViewById(R.id.editTxtPass);
        btnLogin =  (Button)  findViewById(R.id.btnLogin);
        btnRegis =  (Button)  findViewById(R.id.btnRegister);
        textUser.setText("customer1");
        textPass.setText("customer1");
        Initial.SharePref = new PreferenceClass(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                    login = new ProcessLogin();
                    if (Build.VERSION.SDK_INT >= 11) {
                        //--post GB use serial executor by default --
                        login.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,user,pass);
                    }
                    else
                    {
                        //--GB uses ThreadPoolExecutor by default--
                        login.execute();
                    }

                }

            }
        });

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, Register.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


    }

    private class ProcessLogin extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("username",params[0]);
            ph.add("password",params[1]);


            Initial.httpConn = new ServerConnect(LoginActivity.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.Login, ph);


            ShowMe.log("RESULT="+jResult);

            try {
                Initial.jObject = new JSONObject(jResult);
                if(Initial.jObject.getString("status").trim().equals("true")){


                    Initial.jArray = new JSONArray(Initial.jObject.getString("result"));
                    Initial.jsonObject = Initial.jArray.getJSONObject(0);

                    firstname = Initial.jsonObject.getString("first_name");
                    lastname = Initial.jsonObject.getString("last_name");
                    company_id = Initial.jsonObject.getString("company_id");
                    id = Initial.jsonObject.getString("id");
                    ShowMe.log(id);

                    Initial.SharePref.setPrefsAll(firstname, lastname, company_id, id);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            if (!Initial.SharePref.getID().equals("")) {
//                ShowMe.log("Name="+Initial.SharePref.getStringFirstName());



                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else {
                btnLogin.setEnabled(true);

                Toast.makeText(getApplicationContext(), "Login Fail",
                        Toast.LENGTH_LONG).show();

            }



        }




    }
}
