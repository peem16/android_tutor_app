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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class assessment_tutor extends AppCompatActivity  implements View.OnClickListener {
    private  String keysv = ServerConnect.KEY_SERVER;




    private Processassessment assess;
    private String jResult,id,type;
    private Button button,button5;
    private String radio1,radio2,radio3,radio4,radio5;
    private EditText editText3;
    private EditText editText5;
    private TextView textView52;
private     String  number;
        private  int point = 0;
    private RadioButton radiog1_1,radiog1_2,radiog1_3,radiog1_4,radiog1_5;
    private RadioButton radiog2_1,radiog2_2,radiog2_3,radiog2_4,radiog2_5;
    private RadioButton radiog3_1,radiog3_2,radiog3_3,radiog3_4,radiog3_5;
    private RadioButton radiog4_1,radiog4_2,radiog4_3,radiog4_4,radiog4_5;
    private RadioButton radiog5_1,radiog5_2,radiog5_3,radiog5_4,radiog5_5;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        type = getIntent().getExtras().get("type").toString();


        if(type.equals("1")){

            setContentView(R.layout.activity_assessment_tutor);

        }else{
            setContentView(R.layout.activity_assessment_tutor2);


        }

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




        radiog1_1.setOnClickListener(this);
        radiog1_2.setOnClickListener(this);
        radiog1_3.setOnClickListener(this);
        radiog1_4.setOnClickListener(this);
        radiog1_5.setOnClickListener(this);


        radiog2_1.setOnClickListener(this);
        radiog2_2.setOnClickListener(this);
        radiog2_3.setOnClickListener(this);
        radiog2_4.setOnClickListener(this);
        radiog2_5.setOnClickListener(this);

        radiog3_1.setOnClickListener(this);
        radiog3_2.setOnClickListener(this);
        radiog3_3.setOnClickListener(this);
        radiog3_4.setOnClickListener(this);
        radiog3_5.setOnClickListener(this);

        radiog4_1.setOnClickListener(this);
        radiog4_2.setOnClickListener(this);
        radiog4_3.setOnClickListener(this);
        radiog4_4.setOnClickListener(this);
        radiog4_5.setOnClickListener(this);


        radiog5_1.setOnClickListener(this);
        radiog5_2.setOnClickListener(this);
        radiog5_3.setOnClickListener(this);
        radiog5_4.setOnClickListener(this);
        radiog5_5.setOnClickListener(this);










        editText3 = (EditText)findViewById(R.id.editText3);


        editText5 = (EditText)findViewById(R.id.editText5);



//        textView52 = (TextView) findViewById(R.id.textView52);


        button = (Button) findViewById(R.id.button6);
        button.setOnClickListener(this);


        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(this);




        id = getIntent().getExtras().get("Idtimetable_deteil").toString();


    }




    private class Processassessment extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idtutor",params[1]);
            ph.add("Idtimetable_deteil",params[0]);
            ph.add("ask1",params[2]);
            ph.add("ask2",params[3]);
            ph.add("ask3",params[4]);
            ph.add("ask4",params[5]);
            ph.add("ask5",params[6]);
            ph.add("context",params[7]);
            ph.add("Total",params[8]);


            Initial.httpConn = new ServerConnect(assessment_tutor.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+ PHPPath.assessment_tutor, ph);



            ShowMe.log("RESULT="+jResult);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            button5.setEnabled(true);
            try {
                Initial.jObject = new JSONObject(jResult);

                if(Initial.jObject.getString("status").trim().equals("true")){



                    Intent intent = new Intent(assessment_tutor.this,MainActivity_Login.class);
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

      if(v == button5){


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
//
//
//
//
//         number = editText5.getText().toString();
//          if(number.trim().equals("")){
//              Toast.makeText(getApplicationContext(), getString(R.string.number),
//                      Toast.LENGTH_SHORT).show();
//
//
//          }else{
//
//              if(Integer.parseInt(number)>100 || Integer.parseInt(number)==0){
//                  Toast.makeText(getApplicationContext(), getString(R.string.number1),
//                          Toast.LENGTH_SHORT).show();
//
//
//              }
//          }


          if( !(radio5.trim().equals("")) && !(radio4.trim().equals(""))&& !(radio3.trim().equals(""))&& !(radio2.trim().equals(""))&& !(radio1.trim().equals(""))) {
              button5.setEnabled(false);
              assess = new Processassessment();
              if (Build.VERSION.SDK_INT >= 11) {
                  //--post GB use serial executor by default --
                  assess.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, id, Initial.SharePref.getStringtutor().toString(), radio1, radio2, radio3, radio4, radio5, editText3.getText().toString(),""+point);
              } else {
                  //--GB uses ThreadPoolExecutor by default--
                  assess.execute();
              }
          }

        }  else if (v == button) {

            finish();

        }  else  {

           point = 0;

          if(radiog1_1.isChecked()==true){

              radio1 = "5";
              point = point+5;
          }else if(radiog1_2.isChecked()==true){
              radio1 = "4";
              point = point+4;
          }else if(radiog1_3.isChecked()==true){
              radio1 = "3";
              point = point+3;
          }else if(radiog1_4.isChecked()==true){
              radio1 = "2";
              point = point+2;
          }else if(radiog1_5.isChecked()==true){
              radio1 = "1";
              point = point+1;
          }else{
              radio1 = "";
              point = point+0;
          }

          if(radiog2_1.isChecked()==true){

              radio2 = "5";
              point = point+5;

          }else if(radiog2_2.isChecked()==true){
              radio2 = "4";
              point = point+4;

          }else if(radiog2_3.isChecked()==true){
              radio2 = "3";
              point = point+3;

          }else if(radiog2_4.isChecked()==true){
              radio2 = "2";
              point = point+2;

          }else if(radiog2_5.isChecked()==true){
              radio2 = "1";
              point = point+1;

          }else{
              radio2 = "";
              point = point+0;

          }

          if(radiog3_1.isChecked()==true){

              radio3 = "5";
              point = point+5;

          }else if(radiog3_2.isChecked()==true){
              radio3 = "4";
              point = point+4;

          }else if(radiog3_3.isChecked()==true){
              radio3 = "3";
              point = point+3;

          }else if(radiog3_4.isChecked()==true){
              radio3 = "2";
              point = point+2;

          }else if(radiog3_5.isChecked()==true){
              radio3 = "1";
              point = point+1;

          }else{
              radio3 = "";
              point = point+0;

          }


          if(radiog4_1.isChecked()==true){

              radio4 = "5";
              point = point+5;

          }else if(radiog4_2.isChecked()==true){
              radio4 = "4";
              point = point+4;

          }else if(radiog4_3.isChecked()==true){
              radio4 = "3";
              point = point+3;

          }else if(radiog4_4.isChecked()==true){
              radio4 = "2";
              point = point+2;

          }else if(radiog4_5.isChecked()==true){
              radio4 = "1";
              point = point+1;

          }else{
              radio4 = "";
              point = point+0;

          }


          if(radiog5_1.isChecked()==true){

              radio5 = "5";
              point = point+5;

          }else if(radiog5_2.isChecked()==true){
              radio5 = "4";
              point = point+4;

          }else if(radiog5_3.isChecked()==true){
              radio5 = "3";
              point = point+3;

          }else if(radiog5_4.isChecked()==true){
              radio5 = "2";
              point = point+2;

          }else if(radiog5_5.isChecked()==true){
              radio5 = "1";
              point = point+1;

          }else{
              radio5 = "";
              point = point+0;

          }


//            if(point<=5){
//
//                textView52.setText("แย่");
//
//              }else if(point<=10){
//
//                textView52.setText("พอใช้");
//            }else if(point<=15){
//
//                textView52.setText("ปานกลาง");
//            }else if(point<=20){
//
//                textView52.setText("ดี");
//            }else if(point<=25){
//
//                textView52.setText("ดีมาก");
//            }


      }


    }
}
