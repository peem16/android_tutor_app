package com.example.peem16.eakqlearning;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class buydetail extends AppCompatActivity implements View.OnClickListener {
    public static  EditText editText4;
    private Dialog d,d2;
    private static TextView mEdit3,mEdit4;
    public   Button btn,button13;
    public static Button  button;
    public static  TextView mEdit,mEdit2;

    public  ImageButton button12;
    public   TextView btnkey,btnCancel;
    private ArrayList<HashMap<String, Object>> objArrayList2;
    public ListView ListView3;
    private ArrayAdapter<HashMap<String,Object>> mAdapter;
    public static final String PARAM_MAP = "map";
    public static final String PARAM_TAG = "tag";
    private  Processcdetail pcd;
    private SimpleDateFormat dateFormat;
    private Processgettimetable timetable;
    private Processgettimetable2 timetable2;

    private  Processbuycourse buy;
    private HashMap<String, Object> objMap;
    private Processgetkey getkey;
    private static String jResult,cd,price,per_round,amount_courses,Isgroup,idtype;
    Context ctx;
    private String key2 = "";
    private  String keysv = ServerConnect.KEY_SERVER;




    private void getParam() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            HashMap<String, Object> map = (HashMap<String, Object>) bundle.getSerializable(buydetail.PARAM_MAP);
            String tag = bundle.getString(buydetail.PARAM_TAG);

            ctx = this;


            cd = map.get(FieldConstants.Idcourse_detail).toString();
            price = map.get(FieldConstants.price).toString();



            per_round = map.get(FieldConstants.per_round).toString();
            amount_courses = map.get(FieldConstants.amount_courses).toString();

            Isgroup = map.get(FieldConstants.Isgroup).toString();
            idtype = map.get(FieldConstants.idtype).toString();

        }
    }

    @Override
    public void onClick(View v) {
if(v == button){

    String date1 =  mEdit.getText().toString();
    String date2 =   mEdit3.getText().toString();

    String time1 =  mEdit2.getText().toString();
    String time2 =   mEdit4.getText().toString();

    timetable = new Processgettimetable();
    if (Build.VERSION.SDK_INT >= 11) {
        //--post GB use serial executor by default --
        timetable.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cd,date1,time1,date2,time2);
    }
    else
    {
        //--GB uses ThreadPoolExecutor by default--
        timetable.execute();
    }

}

    else if (v == btn){

            String date1 =  mEdit.getText().toString();
            String date2 =   mEdit3.getText().toString();

            String time1 =  mEdit2.getText().toString();
            String time2 =   mEdit4.getText().toString();

            String test = "test";

btn.setEnabled(false);
            buy = new Processbuycourse();
            if (Build.VERSION.SDK_INT >= 11) {
                //--post GB use serial executor by default --
                buy.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,price,cd,Initial.SharePref.getStringIDUserAccount(),test,date1,date2,time1,time2,key2);
            }
            else
            {
                //--GB uses ThreadPoolExecutor by default--
                buy.execute();
            }




        }else if(v == button12){

            showDialog();
        }else if(v == button13){

            showDialog2();
        }



    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buydetail);
        mEdit = (TextView)findViewById(R.id.editText);
        mEdit2 = (TextView)findViewById(R.id.editText2);
        mEdit3 = (TextView)findViewById(R.id.textView6);
        mEdit4 = (TextView)findViewById(R.id.textView7);

        btn = (Button)findViewById(R.id.button11);
//        btn.setBackgroundColor(R.drawable.custom_button_round_rect_border_diable);
        btn.setBackgroundResource(R.drawable.custom_button_round_rect_border_diable);

        btn.setOnClickListener(this);
        getParam();


        button = (Button)findViewById(R.id.button9);
        button.setOnClickListener(this);


        button12 = (ImageButton)findViewById(R.id.imgBtnKey);
        button12.setOnClickListener(this);


        button13 = (Button)findViewById(R.id.button13);
        button13.setOnClickListener(this);


if(idtype.equals("1")){


    if(Isgroup.equals("0")) {

        button12.setImageResource(R.drawable.ic_btn_keygroup_off);

        button12.setEnabled(false);

    }

//    button13.setVisibility(View.GONE);

    button13.setBackgroundResource(R.drawable.custom_button_round_rect_border_diable);
    button13.setEnabled(false);
}else{

    button12.setImageResource(R.drawable.ic_btn_keygroup_off);

    button12.setEnabled(false);

//        button12.setVisibility(View.GONE);
//    button13.setVisibility(View.VISIBLE);
    button13.setBackgroundResource(R.drawable.custom_button_round_rect_border);

}


    }
    private View.OnClickListener cancel = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            d.dismiss();
        }
    };
    private View.OnClickListener cancel2 = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            d2.dismiss();
        }
    };

    private View.OnClickListener con = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub


            String key = editText4.getText().toString();


            getkey = new Processgetkey();
            if (Build.VERSION.SDK_INT >= 11) {
                //--post GB use serial executor by default --
                getkey.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cd,key);
            }
            else
            {
                //--GB uses ThreadPoolExecutor by default--
                getkey.execute();
            }





        }
    };
    private void showDialog2(){


        d2 = new Dialog(this);
        d2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d2.setContentView(R.layout.buyselect);




        btnCancel = (TextView) d2.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(cancel2);
        d2.show();

        ListView3 = (ListView) d2.findViewById(R.id.Listview3);


        ListView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                objMap = (HashMap<String, Object>)ListView3.getItemAtPosition(position);

                mEdit.setText(objMap.get(FieldConstants.Start_date).toString());
                mEdit3.setText(objMap.get(FieldConstants.End_date).toString());

                mEdit2.setText(objMap.get(FieldConstants.Start_time).toString());
                mEdit4.setText(objMap.get(FieldConstants.End_time).toString());





                String date1 =  mEdit.getText().toString();
                String date2 =   mEdit3.getText().toString();

                String time1 =  mEdit2.getText().toString();
                String time2 =   mEdit4.getText().toString();

                timetable2 = new Processgettimetable2();
                if (Build.VERSION.SDK_INT >= 11) {
                    //--post GB use serial executor by default --
                    timetable2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cd,date1,time1,date2,time2);
                }
                else
                {
                    //--GB uses ThreadPoolExecutor by default--
                    timetable2.execute();
                }








                d2.dismiss();






                key2 = objMap.get(FieldConstants.privatekey).toString();
//                Toast.makeText(getContext(),"view"+position,Toast.LENGTH_SHORT).show();

            }
        });

        pcd = new Processcdetail();
        if (Build.VERSION.SDK_INT >= 11) {
            //--post GB use serial executor by default --
            pcd.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cd);
        }
        else
        {
            //--GB uses ThreadPoolExecutor by default--
            pcd.execute();
        }



    }
    private class Processcdetail extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idc",params[0]);


            objArrayList2 = new ArrayList<HashMap<String,Object>>();
            Initial.httpConn = new ServerConnect(getApplicationContext());
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.gettimetable_type2, ph);



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
                        objMap = new HashMap<String, Object>();


                        objMap.put(FieldConstants.Start_time, jsonObject.getString(FieldConstants.Start_time));
                        objMap.put(FieldConstants.End_time, jsonObject.getString(FieldConstants.End_time));
                        objMap.put(FieldConstants.Start_date, jsonObject.getString(FieldConstants.Start_date));

                        objMap.put(FieldConstants.End_date, jsonObject.getString(FieldConstants.End_date));

                        objMap.put(FieldConstants.privatekey, jsonObject.getString(FieldConstants.privatekey));

                        objArrayList2.add(objMap);




                    }
                    ListView3.setAdapter(null);


                    mAdapter = new listtimetableAdapter(getApplicationContext(), R.layout.test44, objArrayList2);
                    ListView3.setAdapter(mAdapter);

                    mAdapter.notifyDataSetInvalidated();
                    ListView3.invalidateViews();

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }









        }




    }
    private void showDialog(){


        d = new Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.key);

        editText4 = (EditText) d.findViewById(R.id.editText4);



        btnkey = (TextView) d.findViewById(R.id.btnkey);
        btnkey.setOnClickListener(con);

        btnCancel = (TextView) d.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(cancel);
        d.show();



    }
    private class Processgetkey extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idcd",params[0]);
            ph.add("key",params[1]);


            Initial.httpConn = new ServerConnect(buydetail.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.getkey, ph);



            ShowMe.log("RESULT="+jResult);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            try {
                Initial.jObject = new JSONObject(jResult);

                if(Initial.jObject.getString("status").trim().equals("true")){


                    Initial.jArray = new JSONArray(Initial.jObject.getString("result"));
                    Initial.jsonObject = Initial.jArray.getJSONObject(0);

                    int s1 = Integer.parseInt(Initial.jsonObject.getString("num"));

                    int s2 = Integer.parseInt(Initial.jsonObject.getString("amount"));

if(s1>=s2){

    Toast.makeText(getApplicationContext(), "จำนวนนักเรียนเต็ม",
            Toast.LENGTH_SHORT).show();
}else{


    mEdit.setText(Initial.jsonObject.getString("Start_date"));



    mEdit2.setText(Initial.jsonObject.getString("Start_time"));

    mEdit3.setText(Initial.jsonObject.getString("End_date"));

    mEdit4.setText(Initial.jsonObject.getString("End_time"));



    d.dismiss();

    key2 =  editText4.getText().toString();
    btn.setEnabled(true);
    btn.setBackgroundResource(R.drawable.custom_button_round_rect_border);


    String date1 =  mEdit.getText().toString();
    String date2 =   mEdit3.getText().toString();

    String time1 =  mEdit2.getText().toString();
    String time2 =   mEdit4.getText().toString();

    timetable2 = new Processgettimetable2();
    if (Build.VERSION.SDK_INT >= 11) {
        //--post GB use serial executor by default --
        timetable2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,cd,date1,time1,date2,time2);
    }
    else
    {
        //--GB uses ThreadPoolExecutor by default--
        timetable2.execute();
    }
}





                }else if(Initial.jObject.getString("status").trim().equals("FULL")){

                    Toast.makeText(getApplicationContext(), "FULL",
                            Toast.LENGTH_LONG).show();

                    btn.setEnabled(false);
                    btn.setBackgroundResource(R.drawable.custom_button_round_rect_border_diable);
                }else if(Initial.jObject.getString("status").trim().equals("notfound")){

                    Toast.makeText(getApplicationContext(), "NotFound",
                            Toast.LENGTH_LONG).show();

                    btn.setEnabled(false);
                    btn.setBackgroundResource(R.drawable.custom_button_round_rect_border_diable);
                }else if(Initial.jObject.getString("status").trim().equals("timeout")){

                    Toast.makeText(getApplicationContext(), "Timeout",
                            Toast.LENGTH_LONG).show();

                    btn.setEnabled(false);
                    btn.setBackgroundResource(R.drawable.custom_button_round_rect_border_diable);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }





        }




    }
    private class Processgettimetable2 extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idcd",params[0]);
            ph.add("date1",params[1]);
            ph.add("time1",params[2]);
            ph.add("date2",params[3]);
            ph.add("time2",params[4]);
            ph.add("ids",Initial.SharePref.getidstudent());

            Initial.httpConn = new ServerConnect(buydetail.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.checktimetable2, ph);



            ShowMe.log("RESULT="+jResult);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            try {
                Initial.jObject = new JSONObject(jResult);

                if(Initial.jObject.getString("status").trim().equals("true")){



                    btn.setEnabled(true);
                    btn.setBackgroundResource(R.drawable.custom_button_round_rect_border);

                    Toast.makeText(getApplicationContext(), "สามารถซื้อคอร์สได้",
                            Toast.LENGTH_LONG).show();



                }else if(Initial.jObject.getString("status").trim().equals("not")){


                    btn.setEnabled(false);
                    btn.setBackgroundResource(R.drawable.custom_button_round_rect_border_diable);

                    Toast.makeText(getApplicationContext(), "คุณมีเวลาเรียนในช่วงเวลานี้แล้ว",
                            Toast.LENGTH_LONG).show();



                }


            } catch (Exception e) {
                e.printStackTrace();
            }





        }




    }

        private class Processgettimetable extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idcd",params[0]);
            ph.add("date1",params[1]);
            ph.add("time1",params[2]);
            ph.add("date2",params[3]);
            ph.add("time2",params[4]);
            ph.add("ids",Initial.SharePref.getidstudent());

            Initial.httpConn = new ServerConnect(buydetail.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.checktimetable, ph);



            ShowMe.log("RESULT="+jResult);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            try {
                Initial.jObject = new JSONObject(jResult);

                if(Initial.jObject.getString("status").trim().equals("true")){



                    btn.setEnabled(true);
                    btn.setBackgroundResource(R.drawable.custom_button_round_rect_border);

                    Toast.makeText(getApplicationContext(), "สามารถซื้อคอร์สได้",
                            Toast.LENGTH_LONG).show();



                }else if(Initial.jObject.getString("status").trim().equals("not")){


                    btn.setEnabled(false);
                    btn.setBackgroundResource(R.drawable.custom_button_round_rect_border_diable);

                    Toast.makeText(getApplicationContext(), "คุณมีเวลาเรียนในช่วงเวลานี้แล้ว",
                            Toast.LENGTH_LONG).show();



                }else{

                    btn.setEnabled(false);
                    btn.setBackgroundResource(R.drawable.custom_button_round_rect_border_diable);

                    Toast.makeText(getApplicationContext(), "กรุณาเลือกช่วงเวลาอื่น",
                            Toast.LENGTH_LONG).show();

                }


            } catch (Exception e) {
                e.printStackTrace();
            }





        }




    }

    private class Processbuycourse extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("buy_price",params[0]);
            ph.add("Idcourse_detail",params[1]);
            ph.add("idUserAccount",params[2]);
            ph.add("idTesting",params[3]);
            ph.add("date_start",params[4]);
            ph.add("date_end",params[5]);
            ph.add("time_start",params[6]);
            ph.add("time_end",params[7]);
            ph.add("keyinput",params[8]);

            Initial.httpConn = new ServerConnect(buydetail.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.buycourse, ph);



            ShowMe.log("RESULT="+jResult);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            try {
                Initial.jObject = new JSONObject(jResult);
                btn.setEnabled(true);

                if(Initial.jObject.getString("status").trim().equals("true")){

                    key2 = "";
                    Intent intent = new Intent(getApplicationContext(),MainActivity_Login.class);
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




    public void populateSetDate(int year, int month, int day) {


        mEdit.setText(month+"/"+day+"/"+year);

        Date m = new Date(month+"/"+day+"/"+year);
        Calendar cal = Calendar.getInstance();
        cal.setTime(m);
        cal.add(Calendar.DATE, (Integer.parseInt(amount_courses)-1)*7); // 10 is the days you want to add or subtract
        String mm = ""+(cal.get(Calendar.MONTH)+1);

        mEdit3.setText(mm+"/"+cal.get(Calendar.DATE)+"/"+cal.get(Calendar.YEAR));


        mEdit2.setText("");
        mEdit4.setText("");

    }

    public void populateSettime(int hourOfDay, int minute) {

            String tt1 = ""+hourOfDay;

        String tt2 = ""+minute;

        if(tt1.toString().length()==2){

            tt1 = tt1;


        }else{

            tt1 = "0"+tt1;

        }

        if(tt2.toString().length()==2){

            tt2 = tt2;


        }else{

            tt2 =  "0"+tt2;

        }
        mEdit2.setText(tt1+":"+tt2);







        String[] separated = per_round.split(":");

        int s1 = Integer.parseInt(separated[0]) ;
        int s2 = Integer.parseInt(separated[1]) ;

        String t1= ""+(s1+hourOfDay);
        String t2 = ""+(s2+minute);


        if(t1.toString().length()==2){

            t1 = t1;


        }else{

            t1 = "0"+t1;

        }

        if(t2.toString().length()==2){

            t2 = t2;


        }else{

            t2 = "0"+t2;

        }
        mEdit4.setText(t1+":"+t2);


        button.performClick();

    }


    public void showTimePickerDialog1(View v) {

        String edit = mEdit.getText().toString();

        if(edit.trim().equals("")){

            Toast.makeText(getApplicationContext(), "กรุณาเลือกวันก่อน",
                    Toast.LENGTH_LONG).show();
        }else{
            btn.setEnabled(false);
            btn.setBackgroundResource(R.drawable.custom_button_round_rect_border_diable);
            key2 = "";
            DialogFragment newFragment = new TimePickerFragment();

            newFragment.show(getFragmentManager(), "timePicker");


        }



    }

    public void showDatePickerDialog1(View v) {
        btn.setEnabled(false);
        btn.setBackgroundResource(R.drawable.custom_button_round_rect_border_diable);
        key2 = "";
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

//


}

