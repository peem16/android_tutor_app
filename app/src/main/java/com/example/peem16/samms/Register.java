package com.example.peem16.samms;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Date;

public class Register extends AppCompatActivity {
    private Button btnConfirm , btnClose;
    private EditText textUser, textPass,firstname,lastname,email,phone,address,idcard,mobile,company;
    private TextView birthdate ,txtRegister;
    private  Processregis regis ;
    private String jResult;
    private  String keysv = ServerConnect.KEY_SERVER;
    private  Integer date = 1,year = 1996 ,month= 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textUser = (EditText)  findViewById(R.id.editTxtUsername);
        textPass = (EditText)  findViewById(R.id.editTxtPassword);
        firstname = (EditText)  findViewById(R.id.editTxtFirstname);
        lastname = (EditText)  findViewById(R.id.editTxtLastname);
        birthdate = (TextView)  findViewById(R.id.editTxtDate);
        email = (EditText)  findViewById(R.id.editTxtEmail);
        phone = (EditText)  findViewById(R.id.editPhone2);
        address = (EditText)  findViewById(R.id.editTxtAddress);
        idcard = (EditText)  findViewById(R.id.idcard);
        mobile = (EditText) findViewById(R.id.editMobile);
        company = (EditText) findViewById(R.id.company);

        txtRegister = (TextView) findViewById(R.id.txtRegister);
        txtRegister.setText("Register");

        btnConfirm = (Button) findViewById(R.id.btnConRegister);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    String user = textUser.getText().toString();
                    String pass = textPass.getText().toString();

                    String first = firstname.getText().toString();
                    String last = lastname.getText().toString();

                    String bdata = birthdate.getText().toString();
                    String emailtxt = email.getText().toString();

                    String phonetxt  = phone.getText().toString();
                    String addresstxt  = address.getText().toString();

                    String idcardtxt = idcard.getText().toString();

                   String mobiletxt = mobile.getText().toString();

                String companytxt = company.getText().toString();


                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (!emailtxt.matches(emailPattern)){

                    Toast.makeText(getApplicationContext(), "Invalid Email Format",
                            Toast.LENGTH_SHORT).show();
                }

                if( !(user.trim().equals("")) &&
                    !(pass.trim().equals("")) &&
                        !(first.trim().equals("")) &&
                        !(last.trim().equals("")) &&
                        !(bdata.trim().equals("")) &&
                        !(emailtxt.trim().equals("")) &&
                        !(phonetxt.trim().equals("")) &&
                        !(addresstxt.trim().equals(""))&&
                        !(mobiletxt.trim().equals(""))&&
                        !(idcardtxt.trim().equals(""))
                        && emailtxt.matches(emailPattern)){

                    btnConfirm.setEnabled(false);


                    regis = new Processregis();
                    if (Build.VERSION.SDK_INT >= 11) {
                        //--post GB use serial executor by default --
                        regis.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,user,pass,first,last,bdata,emailtxt,phonetxt,addresstxt,idcardtxt,mobiletxt,companytxt);
                    }
                    else
                    {
                        //--GB uses ThreadPoolExecutor by default--
                        regis.execute();
                    }

                }


            }
        });



        btnClose = (Button) findViewById(R.id.btnCanRegister);

        birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog mdiDialog =new DatePickerDialog(Register.this ,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        birthdate.setText(year+"/"+monthOfYear+1+'/'+dayOfMonth);
                    }
                }, year, month, date);
                mdiDialog.show();

            }

        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Register.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);

            }
        });


    }


    private class Processregis extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {


            ParamHolder ph = new ParamHolder();
            ph.add("user",params[0]);
            ph.add("password",params[1]);
            ph.add("first",params[2]);
            ph.add("last",params[3]);
            ph.add("bdata",params[4]);
            ph.add("email",params[5]);
            ph.add("phone",params[6]);
            ph.add("address",params[7]);
            ph.add("idcard",params[8]);
            ph.add("mobile",params[9]);
            ph.add("company",params[10]);



            Initial.httpConn = new ServerConnect(Register.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.Register, ph);



            ShowMe.log("RESULT="+jResult);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);





            try {
                Initial.jObject = new JSONObject(jResult);

                if(Initial.jObject.getString("status").trim().equals("true")){

                    Toast.makeText(getApplicationContext(), "Register Success",
                            Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Register.this,LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    startActivity(intent);


                }else{
                    btnConfirm.setEnabled(true);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }




        }

    }
}
