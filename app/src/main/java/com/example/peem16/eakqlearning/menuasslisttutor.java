package com.example.peem16.eakqlearning;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class menuasslisttutor  extends AppCompatActivity implements View.OnClickListener {
    public static final String PARAM_MAP = "map";
    public static final String PARAM_TAG = "tag";
    Context ctx;

    private Button btn1 , btn2;
    private Bundle bundle;
    private   HashMap<String, Object> map;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menuasslisttutor);



        btn1 = (Button)findViewById(R.id.button12);

        btn1.setOnClickListener(this);
        btn2 = (Button)findViewById(R.id.button17);

        btn2.setOnClickListener(this);

         bundle = getIntent().getExtras();
         map = (HashMap<String, Object>) bundle.getSerializable(menuasslisttutor.PARAM_MAP);

    }

    @Override
    public void onClick(View v) {

ShowMe.log("asdadasdasdadasd");
      if(v==btn1) {

          if (bundle != null) {


              ctx = this;


              String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

              SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



              try {
                  Date eighteen = parser.parse( map.get(FieldConstants.End_date).toString()+" "+map.get(FieldConstants.End_time).toString());
                  Calendar ca = Calendar.getInstance();
                  ca.setTime(eighteen);



                  Date newDate = ca.getTime();

                  Date userDate = parser.parse(timeStamp);
                  if (userDate.before(newDate)) {


                      Toast.makeText(getApplicationContext(), "จะดูได้ก็ต่อเมื่อสิ้นสุดคอร์สเรียน",
                              Toast.LENGTH_SHORT).show();



                  }else{


                      Bundle bundle2 = new Bundle();
                      bundle2.putString(asslisttutor_stu_tutor.PARAM_TAG, "asslisttutor_stu_tutor");
                      bundle2.putSerializable(asslisttutor_stu_tutor.PARAM_MAP, map);

                      Intent intent = new Intent(getApplication(), asslisttutor_stu_tutor.class);
                      intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                      intent.putExtras(bundle2);


                      startActivity(intent);




                  }
              } catch (ParseException e) {
                  // Invalid date was entered
              }




          }

      }else if(v==btn2){



          if (bundle != null) {


              ctx = this;


              Bundle bundle2 = new Bundle();
              bundle2.putString(assstu_tutor_.PARAM_TAG,"assstu_tutor_");
              bundle2.putSerializable(assstu_tutor_.PARAM_MAP,map);

              Intent intent = new Intent(getApplication(), assstu_tutor_.class);
              intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
              intent.putExtras(bundle2);


              startActivity(intent);

          }


      }









        }




}
