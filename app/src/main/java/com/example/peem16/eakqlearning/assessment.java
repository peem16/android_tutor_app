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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONObject;

public class assessment  extends AppCompatActivity  implements View.OnClickListener {
    private  String keysv = ServerConnect.KEY_SERVER;


    private Processassessment assess;
    private String jResult;
    private Button button,button5;
    private String radio1,radio2,radio3,radio4,radio5;
    private EditText editText3,editText7;

    private RadioButton radiog1_1,radiog1_2,radiog1_3,radiog1_4,radiog1_5;
    private RadioButton radiog2_1,radiog2_2,radiog2_3,radiog2_4,radiog2_5;
    private RadioButton radiog3_1,radiog3_2,radiog3_3,radiog3_4,radiog3_5;
    private RadioButton radiog4_1,radiog4_2,radiog4_3,radiog4_4,radiog4_5;
    private RadioButton radiog5_1,radiog5_2,radiog5_3,radiog5_4,radiog5_5;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.assessment);

        radiog1_1 = (RadioButton)findViewById(R.id.rba1_1);
        radiog1_2 = (RadioButton)findViewById(R.id.rba1_2);
        radiog1_3 = (RadioButton)findViewById(R.id.rba1_3);
        radiog1_4 = (RadioButton)findViewById(R.id.rba1_4);
        radiog1_5 = (RadioButton)findViewById(R.id.rba1_5);


        radiog2_1 = (RadioButton)findViewById(R.id.rba2_1);
        radiog2_2 = (RadioButton)findViewById(R.id.rba2_2);
        radiog2_3 = (RadioButton)findViewById(R.id.rba2_3);
        radiog2_4 = (RadioButton)findViewById(R.id.rba2_4);
        radiog2_5 = (RadioButton)findViewById(R.id.rba2_5);

        radiog3_1 = (RadioButton)findViewById(R.id.rba3_1);
        radiog3_2 = (RadioButton)findViewById(R.id.rba3_2);
        radiog3_3 = (RadioButton)findViewById(R.id.rba3_3);
        radiog3_4 = (RadioButton)findViewById(R.id.rba3_4);
        radiog3_5 = (RadioButton)findViewById(R.id.rba3_5);

        radiog4_1 = (RadioButton)findViewById(R.id.rba4_1);
        radiog4_2 = (RadioButton)findViewById(R.id.rba4_2);
        radiog4_3 = (RadioButton)findViewById(R.id.rba4_3);
        radiog4_4 = (RadioButton)findViewById(R.id.rba4_4);
        radiog4_5 = (RadioButton)findViewById(R.id.rba4_5);


        radiog5_1 = (RadioButton)findViewById(R.id.rba5_1);
        radiog5_2 = (RadioButton)findViewById(R.id.rba5_2);
        radiog5_3 = (RadioButton)findViewById(R.id.rba5_3);
        radiog5_4 = (RadioButton)findViewById(R.id.rba5_4);
        radiog5_5 = (RadioButton)findViewById(R.id.rba5_5);

        editText3 = (EditText)findViewById(R.id.editText3);

        editText7 = (EditText)findViewById(R.id.editText7);


        button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(this);


        button5 = (Button) findViewById(R.id.button6);
        button5.setOnClickListener(this);

    }



    private class Processassessment extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idstudent",params[0]);
            ph.add("id_timetable",params[1]);
            ph.add("ask1",params[2]);
            ph.add("ask2",params[3]);
            ph.add("ask3",params[4]);
            ph.add("ask4",params[5]);
            ph.add("ask5",params[6]);
            ph.add("context",params[7]);
            ph.add("context2",params[8]);


            Initial.httpConn = new ServerConnect(assessment.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.assessment, ph);



            ShowMe.log("RESULT="+jResult);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            button.setEnabled(true);
            try {
                Initial.jObject = new JSONObject(jResult);

                if(Initial.jObject.getString("status").trim().equals("true")){



                    Intent intent = new Intent(assessment.this,MainActivity_Login.class);
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



    @Override
    public void onClick(View v) {

        if (v == button) {

            if(radiog1_1.isChecked()==true){

                radio1 = "5";

            }else if(radiog1_2.isChecked()==true){
                radio1 = "4";

            }else if(radiog1_3.isChecked()==true){
                radio1 = "3";

            }else if(radiog1_4.isChecked()==true){
                radio1 = "2";

            }else if(radiog1_5.isChecked()==true){
                radio1 = "1";

            }else{
                radio1 = "";

            }

            if(radiog2_1.isChecked()==true){

                radio2 = "5";

            }else if(radiog2_2.isChecked()==true){
                radio2 = "4";

            }else if(radiog2_3.isChecked()==true){
                radio2 = "3";

            }else if(radiog2_4.isChecked()==true){
                radio2 = "2";

            }else if(radiog2_5.isChecked()==true){
                radio2 = "1";

            }else{
                radio2 = "";

            }

            if(radiog3_1.isChecked()==true){

                radio3 = "5";

            }else if(radiog3_2.isChecked()==true){
                radio3 = "4";

            }else if(radiog3_3.isChecked()==true){
                radio3 = "3";

            }else if(radiog3_4.isChecked()==true){
                radio3 = "2";

            }else if(radiog3_5.isChecked()==true){
                radio3 = "1";

            }else{
                radio3 = "";

            }


            if(radiog4_1.isChecked()==true){

                radio4 = "5";

            }else if(radiog4_2.isChecked()==true){
                radio4 = "4";

            }else if(radiog4_3.isChecked()==true){
                radio4 = "3";

            }else if(radiog4_4.isChecked()==true){
                radio4 = "2";

            }else if(radiog4_5.isChecked()==true){
                radio4 = "1";

            }else{
                radio4 = "";

            }


            if(radiog5_1.isChecked()==true){

                radio5 = "5";

            }else if(radiog5_2.isChecked()==true){
                radio5 = "4";

            }else if(radiog5_3.isChecked()==true){
                radio5 = "3";

            }else if(radiog5_4.isChecked()==true){
                radio5 = "2";

            }else if(radiog5_5.isChecked()==true){
                radio5 = "1";

            }else{
                radio5 = "";

            }


            if(radio1.trim().equals("")){
                Toast.makeText(getApplicationContext(), getString(R.string.radio1),
                        Toast.LENGTH_SHORT).show();


            }
            if(radio2.trim().equals("")){
                Toast.makeText(getApplicationContext(), getString(R.string.radio2),
                        Toast.LENGTH_SHORT).show();


            }
            if(radio3.trim().equals("")){
                Toast.makeText(getApplicationContext(), getString(R.string.radio3),
                        Toast.LENGTH_SHORT).show();


            }
            if(radio4.trim().equals("")){
                Toast.makeText(getApplicationContext(), getString(R.string.radio4),
                        Toast.LENGTH_SHORT).show();


            }
            if(radio5.trim().equals("")){
                Toast.makeText(getApplicationContext(), getString(R.string.radio5),
                        Toast.LENGTH_SHORT).show();


            }


            if( !(radio5.trim().equals("")) && !(radio4.trim().equals(""))&& !(radio3.trim().equals(""))&& !(radio2.trim().equals(""))&& !(radio1.trim().equals(""))) {

                button.setEnabled(false);
                assess = new Processassessment();
                if (Build.VERSION.SDK_INT >= 11) {
                    //--post GB use serial executor by default --
                    assess.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Initial.SharePref.getidstudent(),Initial.SharePref.getStringtimetable(),radio1,radio2,radio3,radio4,radio5,editText3.getText().toString(),editText7.getText().toString());
                }
                else
                {
                    //--GB uses ThreadPoolExecutor by default--
                    assess.execute();
                }

            }



        }else if(v == button5){



            finish();
        }


    }
}
