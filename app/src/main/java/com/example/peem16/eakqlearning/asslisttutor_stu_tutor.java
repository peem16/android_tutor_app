package com.example.peem16.eakqlearning;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static android.view.View.VISIBLE;

public class asslisttutor_stu_tutor  extends AppCompatActivity implements View.OnClickListener {
    public static final String PARAM_MAP = "map";
    public static final String PARAM_TAG = "tag";
    private ArrayList<HashMap<String, Object>> objArrayList;
    private  String keysv = ServerConnect.KEY_SERVER;
    private HashMap<String, Object> objMap;
    private String jResult;
    private ArrayAdapter<HashMap<String,Object>> mAdapter;
    private Processasstutor asstutor;
    private String idtimetable;
    private TextView t1_1,t1_2,t1_3,t1_4,t1_5,t2_1,t2_2,t2_3,t2_4,t2_5,t3_1,t3_2,t3_3,t3_4,t3_5,t4_1,t4_2,t4_3,t4_4,t4_5,t5_1,t5_2,t5_3,t5_4,t5_5;
    private TextView t1_1n,t1_2n,t1_3n,t1_4n,t1_5n,t2_1n,t2_2n,t2_3n,t2_4n,t2_5n,t3_1n,t3_2n,t3_3n,t3_4n,t3_5n,t4_1n,t4_2n,t4_3n,t4_4n,t4_5n,t5_1n,t5_2n,t5_3n,t5_4n,t5_5n;

    private Button cancle ;
    Context ctx;
    @Override
    public void onClick(View v) {


        if(v==cancle){

            finish();


        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_assessment_showtutor);


            cancle = (Button)findViewById(R.id.button6);

        cancle.setOnClickListener(this);

        t1_1 = (TextView)findViewById(R.id.textView1_1);
        t1_2 = (TextView)findViewById(R.id.textView1_2);
        t1_3 = (TextView)findViewById(R.id.textView1_3);
        t1_4 = (TextView)findViewById(R.id.textView1_4);
        t1_5 = (TextView)findViewById(R.id.textView1_5);

        t2_1 = (TextView)findViewById(R.id.textView2_1);
        t2_2 = (TextView)findViewById(R.id.textView2_2);
        t2_3 = (TextView)findViewById(R.id.textView2_3);
        t2_4 = (TextView)findViewById(R.id.textView2_4);
        t2_5 = (TextView)findViewById(R.id.textView2_5);


        t3_1 = (TextView)findViewById(R.id.textView3_1);
        t3_2 = (TextView)findViewById(R.id.textView3_2);
        t3_3 = (TextView)findViewById(R.id.textView3_3);
        t3_4 = (TextView)findViewById(R.id.textView3_4);
        t3_5 = (TextView)findViewById(R.id.textView3_5);

        t4_1 = (TextView)findViewById(R.id.textView4_1);
        t4_2 = (TextView)findViewById(R.id.textView4_2);
        t4_3 = (TextView)findViewById(R.id.textView4_3);
        t4_4 = (TextView)findViewById(R.id.textView4_4);
        t4_5 = (TextView)findViewById(R.id.textView4_5);


        t5_1 = (TextView)findViewById(R.id.textView5_1);
        t5_2 = (TextView)findViewById(R.id.textView5_2);
        t5_3 = (TextView)findViewById(R.id.textView5_3);
        t5_4 = (TextView)findViewById(R.id.textView5_4);
        t5_5 = (TextView)findViewById(R.id.textView5_5);



        t1_1n = (TextView)findViewById(R.id.textView1_1n);
        t1_2n  = (TextView)findViewById(R.id.textView1_2n);
        t1_3n  = (TextView)findViewById(R.id.textView1_3n);
        t1_4n  = (TextView)findViewById(R.id.textView1_4n);
        t1_5n  = (TextView)findViewById(R.id.textView1_5n);

        t2_1n  = (TextView)findViewById(R.id.textView2_1n);
        t2_2n  = (TextView)findViewById(R.id.textView2_2n);
        t2_3n  = (TextView)findViewById(R.id.textView2_3n);
        t2_4n  = (TextView)findViewById(R.id.textView2_4n);
        t2_5n  = (TextView)findViewById(R.id.textView2_5n);


        t3_1n  = (TextView)findViewById(R.id.textView3_1n);
        t3_2n  = (TextView)findViewById(R.id.textView3_2n);
        t3_3n  = (TextView)findViewById(R.id.textView3_3n);
        t3_4n  = (TextView)findViewById(R.id.textView3_4n);
        t3_5n  = (TextView)findViewById(R.id.textView3_5n);

        t4_1n  = (TextView)findViewById(R.id.textView4_1n);
        t4_2n  = (TextView)findViewById(R.id.textView4_2n);
        t4_3n  = (TextView)findViewById(R.id.textView4_3n);
        t4_4n  = (TextView)findViewById(R.id.textView4_4n);
        t4_5n  = (TextView)findViewById(R.id.textView4_5n);


        t5_1n  = (TextView)findViewById(R.id.textView5_1n);
        t5_2n  = (TextView)findViewById(R.id.textView5_2n);
        t5_3n  = (TextView)findViewById(R.id.textView5_3n);
        t5_4n  = (TextView)findViewById(R.id.textView5_4n);
        t5_5n  = (TextView)findViewById(R.id.textView5_5n);



        Bundle bundle = getIntent().getExtras();



        if (bundle != null) {

            HashMap<String, Object> map = (HashMap<String, Object>) bundle.getSerializable(menuasslisttutor.PARAM_MAP);

            ctx = this;


            idtimetable = map.get(FieldConstants.id_timetable).toString();

        }


        asstutor = new Processasstutor();
        if (Build.VERSION.SDK_INT >= 11) {
            //--post GB use serial executor by default --
            asstutor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,idtimetable);
        }
        else
        {
            //--GB uses ThreadPoolExecutor by default--
            asstutor.execute();
        }


    }

    private class Processasstutor extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idtimetable",params[0]);



            Initial.httpConn = new ServerConnect(asslisttutor_stu_tutor.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.getassstu_tutor, ph);
            Initial.SharePref = new PreferenceClass(getApplication());


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






                        t1_1.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum1_1"))+ "%");

                        t1_2.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum1_2"))+ "%");
                        t1_3.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum1_3"))+ "%");
                        t1_4.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum1_4"))+ "%");
                        t1_5.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum1_5"))+ "%");





                        t2_1.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum2_1"))+ "%");

                        t2_2.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum2_2"))+ "%");
                        t2_3.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum2_3"))+ "%");
                        t2_4.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum2_4"))+ "%");
                        t2_5.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum2_5"))+ "%");



                        t3_1.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum3_1"))+ "%");

                        t3_2.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum3_2"))+ "%");
                        t3_3.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum3_3"))+ "%");
                        t3_4.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum3_4"))+ "%");
                        t3_5.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum3_5"))+ "%");


                        t4_1.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum4_1"))+ "%");

                        t4_2.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum4_2"))+ "%");
                        t4_3.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum4_3"))+ "%");
                        t4_4.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum4_4"))+ "%");
                        t4_5.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum4_5"))+ "%");


                        t5_1.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum5_1"))+ "%");

                        t5_2.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum5_2"))+ "%");
                        t5_3.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum5_3"))+ "%");
                        t5_4.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum5_4"))+ "%");
                        t5_5.setText(""+(100/Integer.parseInt( jsonObject.getString("sum")))*Integer.parseInt( jsonObject.getString("sum5_5"))+ "%");






                        t1_1n.setText(""+jsonObject.getString("sum1_1"));

                        t1_2n.setText(""+ jsonObject.getString("sum1_2"));
                        t1_3n.setText(""+ jsonObject.getString("sum1_3"));
                        t1_4n.setText(""+jsonObject.getString("sum1_4"));
                        t1_5n.setText(""+jsonObject.getString("sum1_5"));






                        t2_1n.setText(""+jsonObject.getString("sum2_1"));

                        t2_2n.setText(""+ jsonObject.getString("sum2_2"));
                        t2_3n.setText(""+ jsonObject.getString("sum2_3"));
                        t2_4n.setText(""+jsonObject.getString("sum2_4"));
                        t2_5n.setText(""+jsonObject.getString("sum2_5"));


                        t3_1n.setText(""+jsonObject.getString("sum3_1"));

                        t3_2n.setText(""+ jsonObject.getString("sum3_2"));
                        t3_3n.setText(""+ jsonObject.getString("sum3_3"));
                        t3_4n.setText(""+jsonObject.getString("sum3_4"));
                        t3_5n.setText(""+jsonObject.getString("sum3_5"));



                        t4_1n.setText(""+jsonObject.getString("sum4_1"));

                        t4_2n.setText(""+ jsonObject.getString("sum4_2"));
                        t4_3n.setText(""+ jsonObject.getString("sum4_3"));
                        t4_4n.setText(""+jsonObject.getString("sum4_4"));
                        t4_5n.setText(""+jsonObject.getString("sum4_5"));



                        t5_1n.setText(""+jsonObject.getString("sum5_1"));

                        t5_2n.setText(""+ jsonObject.getString("sum5_2"));
                        t5_3n.setText(""+ jsonObject.getString("sum5_3"));
                        t5_4n.setText(""+jsonObject.getString("sum5_4"));
                        t5_5n.setText(""+jsonObject.getString("sum5_5"));







                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }









        }




    }


}
