package com.example.peem16.eakqlearning;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class assShowtutor extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onClick(View v) {

    }

    Context ctx;


    public static final String PARAM_MAP = "map";
    public static final String PARAM_TAG = "tag";
    private  String keysv = ServerConnect.KEY_SERVER;
    private String jResult;
    private String  Content,ask1,ask2,ask3,ask4,ask5,name,idEvaluation;
    private TextView textView11, textView12, textView13, textView14, textView15,textView16,textView17;

    private  Processgettass_view gettass_view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assstutorshow);

        textView11 = (TextView) findViewById(R.id.textView11);

        textView12 = (TextView) findViewById(R.id.textView12);
        textView13 = (TextView) findViewById(R.id.textView13);
        textView14 = (TextView) findViewById(R.id.textView14);
        textView15 = (TextView) findViewById(R.id.textView15);

        textView16 = (TextView) findViewById(R.id.textView16);





        getParam();

        bindData();
    }



    private void bindData() {
        if(ask1.equals("5")){
            textView11.setText("ดีมาก 5 คะแนน");


        }else if(ask1.equals("4")){
            textView11.setText("ดี 4 คะแนน");


        }else if(ask1.equals("3")){

            textView11.setText("ปานกลาง 3 คะแนน");

        }else if(ask1.equals("2")){

            textView11.setText("พอใช้ 2 คะแนน");

        }else if(ask1.equals("1")){

            textView11.setText("แก้ไขโดยด่วน 1 คะแนน");

        }

        if(ask2.equals("5")){

            textView12.setText("ดีมาก 5 คะแนน");

        }else if(ask2.equals("4")){
            textView12.setText("ดี 4 คะแนน");


        }else if(ask2.equals("3")){
            textView12.setText("ปานกลาง 3 คะแนน");


        }else if(ask2.equals("2")){

            textView12.setText("พอใช้ 2 คะแนน");

        }else if(ask2.equals("1")){

            textView12.setText("แก้ไขโดยด่วน 1 คะแนน");

        }

        if(ask3.equals("5")){

            textView13.setText("ดีมาก 5 คะแนน");

        }else if(ask3.equals("4")){
            textView13.setText("ดี 4 คะแนน");


        }else if(ask3.equals("3")){
            textView13.setText("ปานกลาง 3 คะแนน");


        }else if(ask3.equals("2")){

            textView13.setText("พอใช้ 2 คะแนน");

        }else if(ask3.equals("1")){

            textView13.setText("แก้ไขโดยด่วน 1 คะแนน");

        }

        if(ask4.equals("5")){
            textView14.setText("ดีมาก 5 คะแนน");


        }else if(ask4.equals("4")){
            textView14.setText("ดี 4 คะแนน");


        }else if(ask4.equals("3")){
            textView14.setText("ปานกลาง 3 คะแนน");


        }else if(ask4.equals("2")){
            textView14.setText("พอใช้ 2 คะแนน");


        }else if(ask4.equals("1")){
            textView14.setText("แก้ไขโดยด่วน 1 คะแนน");


        }

        if(ask5.equals("5")){
            textView15.setText("ดีมาก 5 คะแนน");


        }else if(ask5.equals("4")){
            textView15.setText("ดี 4 คะแนน");


        }else if(ask5.equals("3")){
            textView15.setText("ปานกลาง 3 คะแนน");


        }else if(ask5.equals("2")){
            textView15.setText("พอใช้ 2 คะแนน");


        }else if(ask5.equals("1")){
            textView15.setText("แก้ไขโดยด่วน 1 คะแนน");


        }



        textView16.setText(name);




        gettass_view = new Processgettass_view();
        if (Build.VERSION.SDK_INT >= 11) {
            //--post GB use serial executor by default --
            gettass_view.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        else
        {
            //--GB uses ThreadPoolExecutor by default--
            gettass_view.execute();
        }

    }

    private void getParam() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            HashMap<String, Object> map = (HashMap<String, Object>) bundle.getSerializable(assShowtutor.PARAM_MAP);
            String tag = bundle.getString(testingShow.PARAM_TAG);

            ctx = this;




            ask1 = map.get(FieldConstants.ask1).toString();
            ask2 = map.get(FieldConstants.ask2).toString();
            ask3 = map.get(FieldConstants.ask3).toString();
            ask4 = map.get(FieldConstants.ask4).toString();

            ask5 = map.get(FieldConstants.ask5).toString();


            name = map.get(FieldConstants.name).toString();


            idEvaluation = map.get(FieldConstants.idEvaluation).toString();


        }
    }


    private class Processgettass_view extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();

            ph.add("idevaluation",idEvaluation);



            Initial.httpConn = new ServerConnect(assShowtutor.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.getass_view, ph);



            ShowMe.log("RESULT="+jResult);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            try {

                JSONObject jObject = new JSONObject(jResult);
                if(jObject.getString("status").trim().equals("true")){





                }


            } catch (JSONException e) {
                e.printStackTrace();
            }









        }




    }
}
