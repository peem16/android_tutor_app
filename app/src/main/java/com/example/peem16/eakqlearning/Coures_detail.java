package com.example.peem16.eakqlearning;

import android.Manifest;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;
import java.util.HashMap;

import static android.view.View.VISIBLE;


public class Coures_detail extends ActionBarActivity implements View.OnClickListener, Serializable {
    private FusedLocationProviderClient mFusedLocationClient;
    private static int numberroom ;
    private static Context ctx;
    public static final String PARAM_MAP = "map";
    public static final String PARAM_TAG = "tag";
    private  Processassessment assess ;
    private Processgettimetable timetable;
    private Processfindtime findtime;
    private Processcheckconleave checkconleave;
    private  EditText et1;
    private Dialog d,dialog1,d2,d3,d1;
    private HashMap<String, Object> objMap;
    private Boolean last = true;
    private  static TextView textView75,textView76,textView77,btnconn;
    private static Button button18,button23;
    private TextView coursename,tv,tv2,btnd3;
    private TextView Start_time;
    private ArrayList<HashMap<String, Object>> objArrayList,objArrayList2;
    private  String dayOfTheWeek;
    private TextView End_time;
    private TextView Start_date;
    private TextView End_date;
    private TextView room,checkstu;
    private TextView firstname,txtdate;
    private TextView lastname;
    private TextView btnCancel2,btnkey2,btnCancel;
    private Button button,button2,button3,button14,button15,button19,button20,btntime,btndate;
    private String jResult,jResult3,jResult4,jResult5,jResultstuall,jResult132,jResultmap,jResulttime,jResult1101;
    private Processleavecheck check;
    private ListView listView,listViewd;
    private Processleavechecktutor checktutor;
    private Boolean mLocationPermissionsGranted = false;
        private  float dis ;
    private  Processleave leave;
    private Processleavetutor_fix leavetutor_fix;
private  Processcheckstuden checkstuden;
    private  Processcheckstuden2 checkstuden2;
    private  Processcheckstuden3 checkstuden3;

    private ProgressBar progressBar;
    private  Processgetassstuden getassstuden;
private Processleavetutor leavetutor;
private Processcheckstudenone checkstudenone;
private TextView textView45;
    private Processcheckstudenall checkstuall;
    private LocationManager locationManager;
    private String wheretime = "";
    private LocationCallback mLocationCallback;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private ArrayAdapter<HashMap<String,Object>> mAdapter;
    private ArrayAdapter<HashMap<String,Object>> mAdapter2;
    private Processcmon cmon;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private Processmap mapss;
    private ProcessleavetutorFind leavetuFind;
    private  Processleavedate leavedate;
    private EditText ee11;
    private float lat , Longg;
    private static String per_round,strcourseid;
    private String id_tutor_compensate,idovertime,date,tital,strStart_time,strEnd_time,strStart_date,Idtimetable_deteil,strEnd_date,strroom_number,strfirstname,strlastname,strnameGrade,strname;
    private  String keysv = ServerConnect.KEY_SERVER;
    LatLng myCoordinates;
    private void getParam() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            HashMap<String, Object> map = (HashMap<String, Object>) bundle.getSerializable(Coures_detail.PARAM_MAP);
            String tag = bundle.getString(Coures_detail.PARAM_TAG);

            ctx = this;

            tital = map.get(FieldConstants.title).toString();

            if(tital.equals("2")||tital.equals("3")){

                date = map.get(FieldConstants.date).toString();

            }

            if(tital.equals("3")){

                idovertime = map.get(FieldConstants.id_timetable_overtime).toString();

            }
            if(tital.equals("2")) {

                if (Initial.SharePref.getStringtutor() != "null") {

                    id_tutor_compensate = map.get(FieldConstants.id_tutor_compensate).toString();

                } else if (Initial.SharePref.getidstudent() != "null") {

                    idovertime = map.get(FieldConstants.id_timetable_overtime).toString();


                }
            }
            strcourseid = map.get(FieldConstants.id_timetable).toString();
            strStart_time = map.get(FieldConstants.Start_time).toString();
            strEnd_time = map.get(FieldConstants.End_time).toString();
            strStart_date = map.get(FieldConstants.Start_date).toString();
            strEnd_date = map.get(FieldConstants.End_date).toString();
            strroom_number = map.get(FieldConstants.room_number).toString();
            strfirstname = map.get(FieldConstants.firstname).toString();
            strlastname = map.get(FieldConstants.lastname).toString();
            strnameGrade = map.get(FieldConstants.nameGrade).toString();
            strname =  map.get(FieldConstants.name).toString();

            per_round =  map.get(FieldConstants.per_round).toString();



            objArrayList = new ArrayList<HashMap<String,Object>>();

            progressBar = (ProgressBar)findViewById(R.id.progressBar7);

//




            if(Initial.SharePref.getidstudent()!="null"){
                Idtimetable_deteil  =  map.get(FieldConstants.Idtimetable_deteil).toString();

                check = new Processleavecheck();
                if (Build.VERSION.SDK_INT >= 11) {
                    //--post GB use serial executor by default --
                    check.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Idtimetable_deteil);
                }
                else
                {
                    //--GB uses ThreadPoolExecutor by default--
                    check.execute();
                }

                assess = new Processassessment();
                if (Build.VERSION.SDK_INT >= 11) {
                    //--post GB use serial executor by default --
                    assess.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Initial.SharePref.getidstudent(),strcourseid,Idtimetable_deteil);
                }
                else
                {
                    //--GB uses ThreadPoolExecutor by default--
                    assess.execute();
                }


                checkstudenone = new Processcheckstudenone();
                if (Build.VERSION.SDK_INT >= 11) {
                    //--post GB use serial executor by default --
                    checkstudenone.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Idtimetable_deteil);
                }
                else
                {
                    //--GB uses ThreadPoolExecutor by default--
                    checkstudenone.execute();
                }

            }else if(Initial.SharePref.getStringtutor()!="null"){
//                button3.setVisibility(View.INVISIBLE);

                checkconleave = new Processcheckconleave();
                if (Build.VERSION.SDK_INT >= 11) {
                    //--post GB use serial executor by default --
                    checkconleave.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Initial.SharePref.getStringtutor(),strcourseid);
                }
                else
                {
                    //--GB uses ThreadPoolExecutor by default--
                    checkconleave.execute();
                }



                mapss = new Processmap();
                if (Build.VERSION.SDK_INT >= 11) {
                    //--post GB use serial executor by default --
                    mapss.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
                else
                {
                    //--GB uses ThreadPoolExecutor by default--
                    mapss.execute();
                }



                checktutor = new Processleavechecktutor();
                if (Build.VERSION.SDK_INT >= 11) {
                    //--post GB use serial executor by default --
                    checktutor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,strcourseid);
                }
                else
                {
                    //--GB uses ThreadPoolExecutor by default--
                    checktutor.execute();
                }




                String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

                SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");



                try {
                    Date  eighteen = parser.parse(strEnd_date);
                    Calendar ca = Calendar.getInstance();
                    ca.setTime(eighteen);
                    ca.add(Calendar.DAY_OF_YEAR, 1);


                    Date newDate = ca.getTime();

                    Date userDate = parser.parse(timeStamp);
                    if (userDate.before(newDate)) {




                    }else{

                        last = false;
                        if(tital.equals("1")){
                            getassstuden = new Processgetassstuden();
                            if (Build.VERSION.SDK_INT >= 11) {
                                //--post GB use serial executor by default --
                                getassstuden.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,strcourseid);
                            }
                            else
                            {
                                //--GB uses ThreadPoolExecutor by default--
                                getassstuden.execute();
                            }


                        }else{



                        }




                    }
                } catch (ParseException e) {
                    // Invalid date was entered
                }








            }




            Initial.SharePref = new PreferenceClass(this);
            Initial.SharePref.setCoures(strcourseid);





            listView = (ListView) findViewById(R.id.listview);

            if(tital.equals("1")){
                if(last ==true){

                    checkstuden = new Processcheckstuden();
                    if (Build.VERSION.SDK_INT >= 11) {
                        //--post GB use serial executor by default --
                        checkstuden.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,strcourseid);
                    }
                    else
                    {
                        //--GB uses ThreadPoolExecutor by default--
                        checkstuden.execute();
                    }
                }

            }else if(tital.equals("2")){

            if(Initial.SharePref.getidstudent()!="null"){


                checkstuden3 = new Processcheckstuden3();
                if (Build.VERSION.SDK_INT >= 11) {
                    //--post GB use serial executor by default --
                    checkstuden3.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,idovertime);
                }
                else
                {
                    //--GB uses ThreadPoolExecutor by default--
                    checkstuden3.execute();
                }
            }else{

                checkstuden2 = new Processcheckstuden2();
                if (Build.VERSION.SDK_INT >= 11) {
                    //--post GB use serial executor by default --
                    checkstuden2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,id_tutor_compensate);
                }
                else
                {
                    //--GB uses ThreadPoolExecutor by default--
                    checkstuden2.execute();
                }

            }




            }else if(tital.equals("3")){


                    checkstuden3 = new Processcheckstuden3();
                    if (Build.VERSION.SDK_INT >= 11) {
                        //--post GB use serial executor by default --
                        checkstuden3.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,idovertime);
                    }
                    else
                    {
                        //--GB uses ThreadPoolExecutor by default--
                        checkstuden3.execute();
                    }




            }





        }
    }
    private class Processgetassstuden extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("timetable",params[0]);

            Initial.httpConn = new ServerConnect(Coures_detail.this);
            jResult5 = Initial.httpConn.getDataFromServer(keysv+PHPPath.getassstu , ph);



            ShowMe.log("RESULT="+jResult5);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            try {

                objArrayList.clear();
                JSONObject jObject = new JSONObject(jResult5);
                if(jObject.getString("status").trim().equals("true")) {
                    if (!jObject.getString("result").trim().equals("null")) {
                        JSONArray jArray = new JSONArray(jObject.getString("result"));
                        int size = jArray.length();
                        for (int i = 0; i < size; i++) {
                            JSONObject jsonObject = jArray.getJSONObject(i);
                            objMap = new HashMap<String, Object>();

                            objMap.put(FieldConstants.Idtimetable_deteil, jsonObject.getString(FieldConstants.Idtimetable_deteil));


                            objMap.put(FieldConstants.firstname, jsonObject.getString(FieldConstants.firstname));
                            objMap.put(FieldConstants.lastname, jsonObject.getString(FieldConstants.lastname));


                            objMap.put(FieldConstants.idEvaluation, jsonObject.getString(FieldConstants.idEvaluation));

                            objMap.put(FieldConstants.idtype, jsonObject.getString(FieldConstants.idtype));

                            objArrayList.add(objMap);


                        }
                    }

                }else{

                    View empty = findViewById(R.id.emptyList);
                    empty.setVisibility(VISIBLE);


                }
                listView.setAdapter(null);

//                AppStatus.setPausing(false);
                mAdapter = new StudentAdapter2(getApplication(), R.layout.list_student, objArrayList);
                mAdapter.notifyDataSetInvalidated();
                listView.invalidateViews();
                Initial.SharePref.setodjstuden(objArrayList);
//

//                TextView empty1 = (TextView) findViewById(R.id.textView20);
//                empty1.setText("No Channels Found!");
//                listView.setEmptyView(empty);


                listView.setAdapter(mAdapter);

                progressBar.setIndeterminate(false);
                progressBar.setVisibility(View.INVISIBLE);

            } catch (Exception e) {
                e.printStackTrace();
            }





        }




    }
    private class Processcheckstuden3 extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idovertime",params[0]);

            Initial.httpConn = new ServerConnect(Coures_detail.this);
            jResult5 = Initial.httpConn.getDataFromServer(keysv+PHPPath.checkstu3 , ph);



            ShowMe.log("RESULT="+jResult5);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            try {

                objArrayList.clear();
                JSONObject jObject = new JSONObject(jResult5);
                if(jObject.getString("status").trim().equals("true")) {
                    if (!jObject.getString("result").trim().equals("null")) {
                        JSONArray jArray = new JSONArray(jObject.getString("result"));
                        int size = jArray.length();
                        for (int i = 0; i < size; i++) {
                            JSONObject jsonObject = jArray.getJSONObject(i);
                            objMap = new HashMap<String, Object>();

                            objMap.put(FieldConstants.Idtimetable_deteil, jsonObject.getString(FieldConstants.Idtimetable_deteil));


                            objMap.put(FieldConstants.firstname, jsonObject.getString(FieldConstants.firstname));
                            objMap.put(FieldConstants.lastname, jsonObject.getString(FieldConstants.lastname));


                            objMap.put(FieldConstants.datetime, jsonObject.getString(FieldConstants.datetime));
                            objMap.put(FieldConstants.num, jsonObject.getString(FieldConstants.num));


                            objMap.put(FieldConstants.End_time, jsonObject.getString(FieldConstants.End_time));
                            objMap.put(FieldConstants.End_date, jsonObject.getString(FieldConstants.End_date));


                            objMap.put(FieldConstants.status, jsonObject.getString(FieldConstants.status));

                            objMap.put(FieldConstants.idtype, jsonObject.getString(FieldConstants.idtype));


                            objMap.put(FieldConstants.idEvaluation, jsonObject.getString(FieldConstants.idEvaluation));

                            objArrayList.add(objMap);


                        }
                    }

                }else if(jObject.getString("status").trim().equals("leave")){


                    View empty2 = findViewById(R.id.emptyList7);
                    empty2.setVisibility(VISIBLE);
                    button3.setVisibility(View.INVISIBLE);
                    button18.setVisibility(View.INVISIBLE);
                }else{

                    View empty = findViewById(R.id.emptyList);
                    empty.setVisibility(VISIBLE);

                }
                if(tital.equals("2")||tital.equals("3")){

                    button18.setVisibility(View.INVISIBLE);

                }
                listView.setAdapter(null);

//                AppStatus.setPausing(false);
                mAdapter = new StudentAdapter3(getApplication(), R.layout.list_student, objArrayList);
                mAdapter.notifyDataSetInvalidated();
                listView.invalidateViews();
                Initial.SharePref.setodjstuden(objArrayList);
//

//                TextView empty1 = (TextView) findViewById(R.id.textView20);
//                empty1.setText("No Channels Found!");
//                listView.setEmptyView(empty);


                listView.setAdapter(mAdapter);

                progressBar.setIndeterminate(false);
                progressBar.setVisibility(View.INVISIBLE);

            } catch (Exception e) {
                e.printStackTrace();
            }





        }




    }

    private class Processcheckstuden2 extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("id_tutor_compensate",params[0]);

            Initial.httpConn = new ServerConnect(Coures_detail.this);
            jResult5 = Initial.httpConn.getDataFromServer(keysv+PHPPath.checkstu2 , ph);



            ShowMe.log("RESULT="+jResult5);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            try {

                objArrayList.clear();
                JSONObject jObject = new JSONObject(jResult5);
                if(jObject.getString("status").trim().equals("true")) {
                    if (!jObject.getString("result").trim().equals("null")) {
                        JSONArray jArray = new JSONArray(jObject.getString("result"));
                        int size = jArray.length();
                        for (int i = 0; i < size; i++) {
                            JSONObject jsonObject = jArray.getJSONObject(i);
                            objMap = new HashMap<String, Object>();

                            objMap.put(FieldConstants.Idtimetable_deteil, jsonObject.getString(FieldConstants.Idtimetable_deteil));


                            objMap.put(FieldConstants.firstname, jsonObject.getString(FieldConstants.firstname));
                            objMap.put(FieldConstants.lastname, jsonObject.getString(FieldConstants.lastname));


                            objMap.put(FieldConstants.datetime, jsonObject.getString(FieldConstants.datetime));
                            objMap.put(FieldConstants.num, jsonObject.getString(FieldConstants.num));


                            objMap.put(FieldConstants.End_time, jsonObject.getString(FieldConstants.End_time));
                            objMap.put(FieldConstants.End_date, jsonObject.getString(FieldConstants.End_date));


                            objMap.put(FieldConstants.status, jsonObject.getString(FieldConstants.status));

                            objMap.put(FieldConstants.idtype, jsonObject.getString(FieldConstants.idtype));


                            objMap.put(FieldConstants.idEvaluation, jsonObject.getString(FieldConstants.idEvaluation));

                            objArrayList.add(objMap);


                        }
                    }

                }else if(jObject.getString("status").trim().equals("leave")){


                    View empty2 = findViewById(R.id.emptyList7);
                    empty2.setVisibility(VISIBLE);
                    button3.setVisibility(View.INVISIBLE);
                    button18.setVisibility(View.INVISIBLE);

                }else{

                    View empty = findViewById(R.id.emptyList);
                    empty.setVisibility(VISIBLE);

                }
                if(tital.equals("2")||tital.equals("3")){

                    button18.setVisibility(View.INVISIBLE);

                }
                listView.setAdapter(null);

//                AppStatus.setPausing(false);
                mAdapter = new StudentAdapter(getApplication(), R.layout.list_student, objArrayList);
                mAdapter.notifyDataSetInvalidated();
                listView.invalidateViews();
                Initial.SharePref.setodjstuden(objArrayList);
//

//                TextView empty1 = (TextView) findViewById(R.id.textView20);
//                empty1.setText("No Channels Found!");
//                listView.setEmptyView(empty);


                listView.setAdapter(mAdapter);

                progressBar.setIndeterminate(false);
                progressBar.setVisibility(View.INVISIBLE);

            } catch (Exception e) {
                e.printStackTrace();
            }





        }




    }

    private class Processcheckstuden extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("timetable",params[0]);

            Initial.httpConn = new ServerConnect(Coures_detail.this);
            jResult5 = Initial.httpConn.getDataFromServer(keysv+PHPPath.checkstu , ph);



            ShowMe.log("RESULT="+jResult5);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            try {

                objArrayList.clear();
                JSONObject jObject = new JSONObject(jResult5);
    if(jObject.getString("status").trim().equals("true")) {
    if (!jObject.getString("result").trim().equals("null")) {
        JSONArray jArray = new JSONArray(jObject.getString("result"));
        int size = jArray.length();
        for (int i = 0; i < size; i++) {
            JSONObject jsonObject = jArray.getJSONObject(i);
            objMap = new HashMap<String, Object>();

            objMap.put(FieldConstants.Idtimetable_deteil, jsonObject.getString(FieldConstants.Idtimetable_deteil));


            objMap.put(FieldConstants.firstname, jsonObject.getString(FieldConstants.firstname));
            objMap.put(FieldConstants.lastname, jsonObject.getString(FieldConstants.lastname));


            objMap.put(FieldConstants.datetime, jsonObject.getString(FieldConstants.datetime));
            objMap.put(FieldConstants.num, jsonObject.getString(FieldConstants.num));


            objMap.put(FieldConstants.End_time, jsonObject.getString(FieldConstants.End_time));
            objMap.put(FieldConstants.End_date, jsonObject.getString(FieldConstants.End_date));


            objMap.put(FieldConstants.status, jsonObject.getString(FieldConstants.status));

            objMap.put(FieldConstants.idtype, jsonObject.getString(FieldConstants.idtype));


            objMap.put(FieldConstants.idEvaluation, jsonObject.getString(FieldConstants.idEvaluation));

            objArrayList.add(objMap);


        }
        button18.setVisibility(View.INVISIBLE);

    }

}else if(jObject.getString("status").trim().equals("leave")){


        View empty2 = findViewById(R.id.emptyList7);
        empty2.setVisibility(VISIBLE);
        button3.setVisibility(View.INVISIBLE);
        button18.setVisibility(View.INVISIBLE);

    }else{

        View empty = findViewById(R.id.emptyList);
        empty.setVisibility(VISIBLE);

    }


                listView.setAdapter(null);

//                AppStatus.setPausing(false);
                mAdapter = new StudentAdapter(getApplication(), R.layout.list_student, objArrayList);
                mAdapter.notifyDataSetInvalidated();
                listView.invalidateViews();
                Initial.SharePref.setodjstuden(objArrayList);
//

//                TextView empty1 = (TextView) findViewById(R.id.textView20);
//                empty1.setText("No Channels Found!");
//                listView.setEmptyView(empty);


                listView.setAdapter(mAdapter);

                progressBar.setIndeterminate(false);
                progressBar.setVisibility(View.INVISIBLE);

            } catch (Exception e) {
                e.printStackTrace();
            }





        }




    }
    private class Processleavechecktutor extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("timetable",params[0]);

            Initial.httpConn = new ServerConnect(Coures_detail.this);
            jResult4 = Initial.httpConn.getDataFromServer(keysv+PHPPath.leavechecktutor , ph);



            ShowMe.log("RESULT="+jResult4);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            try {
                Initial.jObject = new JSONObject(jResult4);

                if(Initial.jObject.getString("status").trim().equals("true")){



                    button.setVisibility(VISIBLE);
//                    button14.setVisibility(View.INVISIBLE);

                    String timeStamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());

                    SimpleDateFormat parser = new SimpleDateFormat("HH:mm");

                    Date eighteen = parser.parse(strStart_time);

                    Calendar ca = Calendar.getInstance();
                    ca.setTime(eighteen);
                    ca.add(Calendar.MINUTE, -15);


                    Date newDate = ca.getTime();

                    ShowMe.log("asdasdasd"+newDate);
                    try {
                        Date userDate = parser.parse(timeStamp);
                        if (userDate.before(newDate)) {


                        }else{

//                         button14.setVisibility(View.INVISIBLE);

                        }
                    } catch (ParseException e) {
                        // Invalid date was entered
                    }




                    String timeStamp2 = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());

                    SimpleDateFormat parser2 = new SimpleDateFormat("HH:mm");

                    Date eighteen2 = parser2.parse(strStart_time);

                    Calendar ca2 = Calendar.getInstance();
                    ca2.setTime(eighteen2);
                    ca2.add(Calendar.MINUTE, +15);

                    Date newDate2 = ca2.getTime();



                    try {
                        Date userDate2 = parser2.parse(timeStamp2);
                        if (userDate2.after(newDate2)) {

                            button.setVisibility(View.INVISIBLE);
                        }else{



                        }
                    } catch (ParseException e) {
                        // Invalid date was entered
                    }



                }else  if(Initial.jObject.getString("status").trim().equals("true_hide")){


                    button15.setVisibility(View.INVISIBLE);

//                    button14.setVisibility(View.VISIBLE);
                    button.setVisibility(View.INVISIBLE);

                    String timeStamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());

                    SimpleDateFormat parser = new SimpleDateFormat("HH:mm");

                    Date eighteen = parser.parse(strStart_time);

                    Calendar ca = Calendar.getInstance();
                    ca.setTime(eighteen);
                    ca.add(Calendar.MINUTE, -15);


                    Date newDate = ca.getTime();



                    try {
                        Date userDate = parser.parse(timeStamp);
                        if (userDate.before(newDate)) {


                        }else{



//                            button14.setVisibility(View.INVISIBLE);


//                            le = new Processle();
//                            if (Build.VERSION.SDK_INT >= 11) {
//                                //--post GB use serial executor by default --
//                                le.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//                            }
//                            else
//                            {
//                                //--GB uses ThreadPoolExecutor by default--
//                                le.execute();
//                            }

                        }
                    } catch (ParseException e) {
                        // Invalid date was entered
                    }


                    String timeStamp2 = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());

                    SimpleDateFormat parser2 = new SimpleDateFormat("HH:mm");

                    Date eighteen2 = parser2.parse(strStart_time);

                    Calendar ca2 = Calendar.getInstance();
                    ca2.setTime(eighteen2);
                    ca2.add(Calendar.MINUTE,-15);




                    Date newDate2 = ca2.getTime();


                    try {
                        Date userDate2 = parser.parse(timeStamp2);
                        if (userDate2.after(newDate2)) {

                            button15.setVisibility(View.VISIBLE);

                        }else{






                        }
                    } catch (ParseException e) {
                        // Invalid date was entered
                    }

                    String timeStamp3 = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());

                    SimpleDateFormat parser3 = new SimpleDateFormat("HH:mm");

                    Date eighteen3 = parser3.parse(strStart_time);

                    Calendar ca3 = Calendar.getInstance();
                    ca3.setTime(eighteen3);



                    Date newDate3 = ca3.getTime();


                    try {
                        Date userDate3 = parser.parse(timeStamp3);
                        if (userDate3.after(newDate3)) {

                            button15.setVisibility(View.INVISIBLE);

                        }else{






                        }
                    } catch (ParseException e) {
                        // Invalid date was entered
                    }



                }else  if(Initial.jObject.getString("status").trim().equals("false")){
                    button.setVisibility(View.INVISIBLE);

//                    button14.setVisibility(View.INVISIBLE);
                }else  if(Initial.jObject.getString("status").trim().equals("not")){

                    button.setVisibility(View.INVISIBLE);
//                    button14.setVisibility(View.INVISIBLE);

                }


            } catch (Exception e) {
                e.printStackTrace();
            }





        }




    }

    private class Processmap extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();


            Initial.httpConn = new ServerConnect(Coures_detail.this);
            jResultmap = Initial.httpConn.getDataFromServer(keysv+PHPPath.getmap , ph);



            ShowMe.log("RESULT="+jResultmap);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            try {


                JSONObject jObject = new JSONObject(jResultmap);
                if(jObject.getString("status").trim().equals("true")) {
                    if (!jObject.getString("result").trim().equals("null")) {
                        JSONArray jArray = new JSONArray(jObject.getString("result"));
                        int size = jArray.length();
                        for (int i = 0; i < size; i++) {
                            JSONObject jsonObject = jArray.getJSONObject(i);

                            Longg = Float.parseFloat(jsonObject.getString("longitude"));
                            lat = Float.parseFloat(jsonObject.getString("latitude"));
                            dis = Float.parseFloat(jsonObject.getString("Singup_distance"));
                            ShowMe.log("asdasd"+dis);
                            ShowMe.log("asdasd"+lat);
                            ShowMe.log("asdasd"+Longg);

                        }
                    }

                }


            } catch (Exception e) {
                e.printStackTrace();
            }





        }




    }
    private class Processleavecheck extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("Idtimetable_deteil",params[0]);

            Initial.httpConn = new ServerConnect(Coures_detail.this);
            jResult3 = Initial.httpConn.getDataFromServer(keysv+PHPPath.leavecheck , ph);



            ShowMe.log("RESULT="+jResult3);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            try {
                Initial.jObject = new JSONObject(jResult3);

                if(Initial.jObject.getString("status").trim().equals("true")){



//                    button3.setVisibility(VISIBLE);

                    String timeStamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());

                    SimpleDateFormat parser = new SimpleDateFormat("HH:mm");

                    Date eighteen = parser.parse(strEnd_time);

                    try {
                        Date userDate = parser.parse(timeStamp);
                        if (userDate.before(eighteen)) {


                        }else{

                            button3.setVisibility(View.INVISIBLE);

                        }
                    } catch (ParseException e) {
                        // Invalid date was entered
                    }


                }else{

                    button3.setVisibility(View.INVISIBLE);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }





        }




    }

    private class Processleavetutor_fix extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("tutor",params[0]);
            ph.add("timetable",params[1]);
            ph.add("content",params[2]);

            Initial.httpConn = new ServerConnect(Coures_detail.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.leavetutor_fix , ph);



            ShowMe.log("RESULT="+jResult);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            try {
                Initial.jObject = new JSONObject(jResult);
                tv.setEnabled(true);
                if(Initial.jObject.getString("status").trim().equals("true")){

                    d.dismiss();


//                    button14.setVisibility(View.INVISIBLE);
                    button15.setVisibility(View.INVISIBLE);

                    checkstuden = new Processcheckstuden();
                    if (Build.VERSION.SDK_INT >= 11) {
                        //--post GB use serial executor by default --
                        checkstuden.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,strcourseid);
                    }
                    else
                    {
                        //--GB uses ThreadPoolExecutor by default--
                        checkstuden.execute();
                    }

                }else{



                    Toast.makeText(getApplicationContext(), "ไม่สามารถลาได้เนื่องจากตารางเรียนเต็ม \nกรุณาเลือกช่องทางแบบอื่น",
                            Toast.LENGTH_SHORT).show();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }





        }




    }

    private class Processleavedate extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idt",params[0]);
            ph.add("date",params[1]);
            ph.add("time1",params[2]);
            ph.add("time2",params[3]);
            ph.add("room",numberroom+"");


            Initial.httpConn = new ServerConnect(Coures_detail.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.leavetutordate , ph);



            ShowMe.log("RESULT="+jResult);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            try {
                Initial.jObject = new JSONObject(jResult);

                if(Initial.jObject.getString("status").trim().equals("true")){

                    d2.dismiss();
                    d1.dismiss();

//                    button14.setVisibility(View.INVISIBLE);
                    button15.setVisibility(View.INVISIBLE);

                    button15.setVisibility(View.INVISIBLE);

                    button18.setVisibility(View.INVISIBLE);
                    checkstuden = new Processcheckstuden();
                    if (Build.VERSION.SDK_INT >= 11) {
                        //--post GB use serial executor by default --
                        checkstuden.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,strcourseid);
                    }
                    else
                    {
                        //--GB uses ThreadPoolExecutor by default--
                        checkstuden.execute();
                    }

                }


            } catch (Exception e) {
                e.printStackTrace();
            }





        }




    }




    private class Processleavetutor extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("tutor",params[0]);
            ph.add("timetable",params[1]);
            ph.add("content",params[2]);
            ph.add("date",params[3]);

            Initial.httpConn = new ServerConnect(Coures_detail.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.leavetutor , ph);



            ShowMe.log("RESULT="+jResult);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            try {
                Initial.jObject = new JSONObject(jResult);
                btnkey2.setEnabled(true);
                if(Initial.jObject.getString("status").trim().equals("true")){
                    d1.dismiss();
                    d3.dismiss();
//                    button14.setVisibility(View.INVISIBLE);
                    button15.setVisibility(View.INVISIBLE);

                    checkstuden = new Processcheckstuden();
                    if (Build.VERSION.SDK_INT >= 11) {
                        //--post GB use serial executor by default --
                        checkstuden.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,strcourseid);
                    }
                    else
                    {
                        //--GB uses ThreadPoolExecutor by default--
                        checkstuden.execute();
                    }

                }else{



                    Toast.makeText(getApplicationContext(), "ไม่สามารถลาได้เนื่องจากตารางเรียนเต็ม \nกรุณาเลือกช่องทางแบบอื่น",
                            Toast.LENGTH_SHORT).show();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }





        }




    }

    private class Processcheckconleave extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idtutor",params[0]);
            ph.add("idtable",params[1]);


            Initial.httpConn = new ServerConnect(Coures_detail.this);
            jResult1101 = Initial.httpConn.getDataFromServer(keysv+PHPPath.getCheckbtn , ph);



            ShowMe.log("RESULT="+jResult1101);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            try {
                Initial.jObject = new JSONObject(jResult1101);

                if(Initial.jObject.getString("status").trim().equals("true")){


                    button18.setVisibility(View.GONE);
                }else{

                    button18.setVisibility(View.VISIBLE);


                }


            } catch (Exception e) {
                e.printStackTrace();
            }





        }




    }
    private class Processcheckstudenone extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idtd",params[0]);


            Initial.httpConn = new ServerConnect(Coures_detail.this);
            jResult132 = Initial.httpConn.getDataFromServer(keysv+PHPPath.getCheckstuone , ph);



            ShowMe.log("RESULT="+jResult132);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            try {
                Initial.jObject = new JSONObject(jResult132);

                if(Initial.jObject.getString("status").trim().equals("true")){


                    JSONArray jArray = new JSONArray(Initial.jObject.getString("result"));
                    int size = jArray.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject jsonObject = jArray.getJSONObject(i);


                        textView45.setText("การเข้าเรียน"+jsonObject.getString(FieldConstants.count)+"/"+Initial.jObject.getString("desc").trim());

                    }

                    textView45.setVisibility(VISIBLE);


                }


            } catch (Exception e) {
                e.printStackTrace();
            }





        }




    }
    private class Processleave extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();

            ph.add("Idtimetable_deteil",params[0]);


            Initial.httpConn = new ServerConnect(Coures_detail.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.leave , ph);



            ShowMe.log("RESULT="+jResult);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            try {
                Initial.jObject = new JSONObject(jResult);
                button3.setEnabled(true);

                if(Initial.jObject.getString("status").trim().equals("true")){


                    button3.setVisibility(View.INVISIBLE);


                    Toast.makeText(getApplicationContext(), "แจ้งลาสำเร็จ",
                            Toast.LENGTH_LONG).show();




                    checkstuden = new Processcheckstuden();
                    if (Build.VERSION.SDK_INT >= 11) {
                        //--post GB use serial executor by default --
                        checkstuden.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,strcourseid);
                    }
                    else
                    {
                        //--GB uses ThreadPoolExecutor by default--
                        checkstuden.execute();
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }





        }




    }




    private class Processcmon extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();

            ph.add("id_timetable",params[0]);
            ph.add("idtutor",Initial.SharePref.getStringtutor());


            Initial.httpConn = new ServerConnect(Coures_detail.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.cmon , ph);



            ShowMe.log("RESULT="+jResult);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            try {
                Initial.jObject = new JSONObject(jResult);
                button15.setEnabled(true);

                if(Initial.jObject.getString("status").trim().equals("true")){










                    button15.setVisibility(View.GONE);
                    button.setVisibility(View.VISIBLE);
//                    button14.setVisibility(View.INVISIBLE);

                    Toast.makeText(getApplicationContext(), "บันทึกการเข้าสอน",
                            Toast.LENGTH_LONG).show();

                }


            } catch (Exception e) {
                e.printStackTrace();
            }





        }




    }



    private class Processassessment extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idstudent",params[0]);
            ph.add("id_timetable",params[1]);
            ph.add("Idtimetable_deteil",params[2]);

            Initial.httpConn = new ServerConnect(Coures_detail.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.finalcoures, ph);



            ShowMe.log("RESULT="+jResult);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            try {
                Initial.jObject = new JSONObject(jResult);

                if(Initial.jObject.getString("status").trim().equals("true")){


                    String timeStamp2 = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());

                    SimpleDateFormat parser2 = new SimpleDateFormat("HH:mm");

                    Date eighteen2 = parser2.parse(strEnd_time);

                    Calendar ca2 = Calendar.getInstance();
                    ca2.setTime(eighteen2);



                    Date newDate2 = ca2.getTime();


                    try {
                        Date userDate2 = parser2.parse(timeStamp2);
                        if (userDate2.after(newDate2)) {
                            button2.setVisibility(VISIBLE);

                        }else{

                            button2.setVisibility(View.INVISIBLE);


                        }
                    } catch (ParseException e) {
                        // Invalid date was entered
                    }








                }else{
                    button2.setVisibility(View.INVISIBLE);

                }


            } catch (Exception e) {
                e.printStackTrace();
            }





        }




    }


    private class Processcheckstudenall extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("id_timetable",params[0]);

            objArrayList2 = new ArrayList<HashMap<String,Object>>();

            Initial.httpConn = new ServerConnect(Coures_detail.this);
            jResultstuall = Initial.httpConn.getDataFromServer(keysv+PHPPath.getCheckstuall, ph);



            ShowMe.log("RESULT="+jResultstuall);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            try {
                Initial.jObject = new JSONObject(jResultstuall);

                if(Initial.jObject.getString("status").trim().equals("true")){
                    objArrayList2.clear();
                        JSONArray jArray = new JSONArray(Initial.jObject.getString("result"));
                        int size = jArray.length();
                        for (int i = 0; i < size; i++) {
                            JSONObject jsonObject = jArray.getJSONObject(i);
                            objMap = new HashMap<String, Object>();

                            objMap.put(FieldConstants.firstname, jsonObject.getString(FieldConstants.firstname));


                            objMap.put(FieldConstants.lastname, jsonObject.getString(FieldConstants.lastname));

                            objMap.put(FieldConstants.count, jsonObject.getString(FieldConstants.count));

                            objMap.put(FieldConstants.num, Initial.jObject.getString("desc").trim().toString());




                            objArrayList2.add(objMap);


                        }
                    listViewd.setAdapter(null);

                    mAdapter2 = new StudentcheckallAdapter(getApplicationContext(), R.layout.list_studentcheck, objArrayList2);
                    mAdapter2.notifyDataSetInvalidated();

                    listViewd.setAdapter(mAdapter2);
                    mAdapter2.notifyDataSetInvalidated();
                    listViewd.invalidateViews();
                    dialog1.show();

                }


            } catch (Exception e) {
                e.printStackTrace();
            }





        }




    }


    private void requestLocation() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_MEDIUM);
        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
        String provider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(provider);
        Log.d("mylog", "In Requesting Location");
        if (location != null && (System.currentTimeMillis() - location.getTime()) <= 1000 * 2) {
            LatLng myCoordinates = new LatLng(location.getLatitude(), location.getLongitude());
//            String cityName = getCityName(myCoordinates);
//            Toast.makeText(this, cityName, Toast.LENGTH_SHORT).show();
        } else {
            LocationRequest locationRequest = new LocationRequest();
            locationRequest.setNumUpdates(1);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            Log.d("mylog", "Last location too old getting new location!");
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            mFusedLocationClient.requestLocationUpdates(locationRequest,
                    mLocationCallback, Looper.myLooper());
        }
    }
    private void getLocationPermission(){

        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;

            }else{
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.course_detail);





        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Location mCurrentLocation = locationResult.getLastLocation();
                myCoordinates = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());




            };



        };

        if (Build.VERSION.SDK_INT >= 23) {
            Log.d("mylog", "Getting Location Permission");
            if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.d("mylog", "Not granted");
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else
                requestLocation();
        } else
            requestLocation();



        ;

        getLocationPermission();



        coursename = (TextView) findViewById(R.id.coursename);
//        Start_date = (TextView) findViewById(R.id.Start_date);
//        Start_time = (TextView) findViewById(R.id.Start_time);
        End_date = (TextView) findViewById(R.id.End_date);
        End_time = (TextView) findViewById(R.id.End_time);
        firstname = (TextView) findViewById(R.id.firstname);
        lastname = (TextView) findViewById(R.id.lastname);

        txtdate = (TextView) findViewById(R.id.txtdate);
        textView45 = (TextView) findViewById(R.id.textView45);

        button18 = (Button) findViewById(R.id.button18);
        button18.setOnClickListener(this);
        button15 = (Button) findViewById(R.id.button15);

        button15.setOnClickListener(this);

        room = (TextView) findViewById(R.id.room);



        checkstu = (TextView) findViewById(R.id.checkstu);

        checkstu.setOnClickListener(this);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        getParam();
        bindData();

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);

        button2.setVisibility(View.INVISIBLE);

//        button14 = (Button) findViewById(R.id.button14);
//        button14.setOnClickListener(this);



        button.setVisibility(View.INVISIBLE);



        if(Initial.SharePref.getidstudent()!="null"){



//            button14.setVisibility(View.INVISIBLE);
            button.setVisibility(View.INVISIBLE);


        }else if(Initial.SharePref.getStringtutor()!="null"){

            button18.setVisibility(View.VISIBLE);
            checkstu.setVisibility(View.VISIBLE);


                button3.setVisibility(View.INVISIBLE);


            String timeStamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());

            SimpleDateFormat parser = new SimpleDateFormat("HH:mm");











            try {
                Date end = parser.parse(strEnd_time);

                Date sta = parser.parse(strStart_time);
                Calendar ca = Calendar.getInstance();
                ca.setTime(sta);
                ca.add(Calendar.MINUTE, +15);

                Date newDate = ca.getTime();



                Date userDate = parser.parse(timeStamp);
                if (userDate.before(newDate) && userDate.after(sta)  ) {

                    button.setVisibility(View.VISIBLE);

                }else{

                    button.setVisibility(View.INVISIBLE);


                }
            } catch (ParseException e) {
                // Invalid date was entered
            }


        }




        if(tital.equals("2")){

//            button14.setVisibility(View.INVISIBLE);
            button.setVisibility(View.INVISIBLE);
            button18.setVisibility(View.INVISIBLE);
        }

        if(tital.equals("3")){

//            button14.setVisibility(View.INVISIBLE);
            button.setVisibility(View.INVISIBLE);
            button18.setVisibility(View.INVISIBLE);
        }


    }
    private void bindData() {

        coursename.setText(strname);
//        Start_date.setText(strStart_date);
//        Start_time.setText(strStart_time);
        End_time.setText(strStart_time+"/"+strEnd_time);
        if(tital.equals("2")||tital.equals("3")){
            txtdate.setText("วันที่เรียน");
            End_date.setText(date);

        }else{
            End_date.setText(strStart_date+"/"+strEnd_date);

        }
        firstname.setText(strfirstname);
        lastname.setText(strlastname);
        room.setText(strroom_number);




    }
    @Override
    public void onClick(View v) {


        if (v == button) {

            Intent intent = new Intent(this,Scan_qr.class);
            startActivity(intent);

        }else if(v == button2){
            Initial.SharePref.settimetable(strcourseid);

            Intent intent = new Intent(this,assessment.class);
            startActivity(intent);

        }else if(v == button15){


            Location loc1 = new Location("");
            loc1.setLatitude(lat);
            loc1.setLongitude(Longg);

            Location loc2 = new Location("");
            loc2.setLatitude(myCoordinates.latitude);
            loc2.setLongitude(myCoordinates.longitude);

            float distanceInMeters = loc1.distanceTo(loc2);

                ShowMe.log("asdasdasdasd"+distanceInMeters);
            if(distanceInMeters <= dis) {


                button15.setEnabled(false);

                cmon = new Processcmon();
                if (Build.VERSION.SDK_INT >= 11) {
                    //--post GB use serial executor by default --
                    cmon.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,strcourseid);
                }
                else
                {
                    //--GB uses ThreadPoolExecutor by default--
                    cmon.execute();
                }

            }else{
                Toast.makeText(getApplicationContext(), "คุณไม่ได้อยู่ในพื้นที่ " ,  Toast.LENGTH_SHORT).show();
            }











        }
        else if(v == button3){

            button3.setEnabled(false);
            leave = new Processleave();
            if (Build.VERSION.SDK_INT >= 11) {
                //--post GB use serial executor by default --
                leave.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Idtimetable_deteil);
            }
            else
            {
                //--GB uses ThreadPoolExecutor by default--
                leave.execute();
            }

        }
//        else if(v == button14){
//
//
//
//            d = new Dialog(this);
//            d.requestWindowFeature(Window.FEATURE_NO_TITLE);
//            d.setContentView(R.layout.key);
//
//
//
//
//
//             tv = (TextView) d.findViewById(R.id.btnkey) ;
//            tv.setOnClickListener(this);
//
//
//            tv2= (TextView) d.findViewById(R.id.btnCancel) ;
//            tv2.setOnClickListener(cancel2);
//
//
//
//            d.show();
//
//            et1 = (EditText) d.findViewById(R.id.editText4) ;
//            et1.setHint("ระบุสาเหตุ");
//
//    }
    else if(v == tv){
            String pass = et1.getText().toString();

            if(pass.trim().equals("")){

                Toast.makeText(getApplicationContext(), getString(R.string.validate_le),
                        Toast.LENGTH_SHORT).show();
                et1.requestFocus();

            }

            if( !(pass.trim().equals(""))){


                tv.setEnabled(false);

//                leavetutor = new Processleavetutor();
//                if (Build.VERSION.SDK_INT >= 11) {
//                    //--post GB use serial executor by default --
//                    leavetutor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Initial.SharePref.getStringtutor(),strcourseid,et1.getText().toString());
//                }
//                else
//                {
//                    //--GB uses ThreadPoolExecutor by default--
//                    leavetutor.execute();
//                }

                leavetutor_fix = new Processleavetutor_fix();
                if (Build.VERSION.SDK_INT >= 11) {
                    //--post GB use serial executor by default --
                    leavetutor_fix.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Initial.SharePref.getStringtutor(),strcourseid,et1.getText().toString());
                }
                else
                {
                    //--GB uses ThreadPoolExecutor by default--
                    leavetutor_fix.execute();
                }
            }




        }else if(v ==  checkstu){



            dialog1 = new Dialog(this);
            dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog1.setContentView(R.layout.checkstuall);

            listViewd = (ListView)dialog1.findViewById(R.id.listviewd);
            TextView  ex= (TextView) dialog1.findViewById(R.id.button7) ;
            ex.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog1.dismiss();

                }
            });


            checkstuall = new Processcheckstudenall();
            if (Build.VERSION.SDK_INT >= 11) {
                //--post GB use serial executor by default --
                checkstuall.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,strcourseid);
            }
            else
            {
                //--GB uses ThreadPoolExecutor by default--
                checkstuall.execute();
            }



        }else if(v == button18){


            d1 = new Dialog(this);
            d1.requestWindowFeature(Window.FEATURE_NO_TITLE);
            d1.setContentView(R.layout.leaveselect);

            d2 = new Dialog(this);
            d2.requestWindowFeature(Window.FEATURE_NO_TITLE);
            d2.setContentView(R.layout.leavetime);

            textView75 = (TextView)d2.findViewById(R.id.textView75);
            textView76 = (TextView)d2.findViewById(R.id.textView76);
            textView77 = (TextView)d2.findViewById(R.id.textView77);


            btnd3 = (TextView)d2.findViewById(R.id.btnCancel);
            btnd3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    d2.dismiss();
                }
            });



            btnconn = (TextView)d2.findViewById(R.id.btnconn);

            btnconn.setEnabled(false);
            btnconn.setBackgroundResource(R.drawable.custom_button_round_rect_border_diable);

            btnconn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String t1 = textView75.getText().toString();
                    String t2 = textView76.getText().toString();
                    String t3 = textView77.getText().toString();

                    if(t1.trim().equals("")){

                        Toast.makeText(getApplicationContext(), "กรุณาเลือกวันที่",
                                Toast.LENGTH_SHORT).show();
                        textView75.requestFocus();

                    }
                    if(t2.trim().equals("")){

                        Toast.makeText(getApplicationContext(),"กรุณาเลือกวัน",
                                Toast.LENGTH_SHORT).show();
                        textView76.requestFocus();

                    }
                    if(t3.trim().equals("")){

                        Toast.makeText(getApplicationContext(), "กรุณาเลือกวัน",
                                Toast.LENGTH_SHORT).show();
                        textView77.requestFocus();

                    }

                    if(!t1.trim().equals("") && !t2.trim().equals("") & !t3.trim().equals("")){


                        leavedate = new Processleavedate();
                        if (Build.VERSION.SDK_INT >= 11) {
                            //--post GB use serial executor by default --
                            leavedate.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,strcourseid,t1,t2,t3);
                        }
                        else
                        {
                            //--GB uses ThreadPoolExecutor by default--
                            leavedate.execute();
                        }



                    }


                    }
            });



            btndate = (Button)d2.findViewById(R.id.button22);
            btndate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    DialogFragment newFragment = new DatePickerFragment2();
                    newFragment.show(getFragmentManager(), "datePicker");
                }
            });

            btntime = (Button)d2.findViewById(R.id.button21);
            btntime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(textView75.getText().equals("")){

                        Toast.makeText(getApplicationContext(), "กรุณาเลือกวันก่อน",
                                Toast.LENGTH_LONG).show();
                    }else {
                        DialogFragment newFragment = new TimePickerFragment2();

                        newFragment.show(getFragmentManager(), "timePicker");

                    }

                }
            });



            d3 = new Dialog(this);
            d3.requestWindowFeature(Window.FEATURE_NO_TITLE);
            d3.setContentView(R.layout.key2);

            ee11 = (EditText) d3.findViewById(R.id.editText4) ;
            ee11.setHint("ระบุสาเหตุ");

            btnCancel2 = (TextView)d3.findViewById(R.id.btnCancel);
            btnCancel2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    d3.dismiss();
                }
            });

            btnkey2 = (TextView)d3.findViewById(R.id.btnkey);
            btnkey2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    String pass = ee11.getText().toString();

                    if(pass.trim().equals("")){

                        Toast.makeText(getApplicationContext(), getString(R.string.validate_le),
                                Toast.LENGTH_SHORT).show();
                        ee11.requestFocus();

                    }

                    if( !(pass.trim().equals(""))) {


                        btnkey2.setEnabled(false);



                        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

                        leavetutor = new Processleavetutor();
                if (Build.VERSION.SDK_INT >= 11) {
                    //--post GB use serial executor by default --
                    leavetutor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Initial.SharePref.getStringtutor(),strcourseid,ee11.getText().toString(),timeStamp);
                }
                else
                {
                    //--GB uses ThreadPoolExecutor by default--
                    leavetutor.execute();
                }








                    }



                }
            });




            button19 = (Button)d1.findViewById(R.id.button19);
            button19.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    d2.show();
                }
            });


            button20 = (Button)d1.findViewById(R.id.button20);
            button20.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    d3.show();
                }
            });




            btnCancel = (TextView)d1.findViewById(R.id.btnCancel);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    d1.dismiss();
                }
            });


            button23 = (Button) d1.findViewById(R.id.button23);
            button23.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    leavetuFind = new ProcessleavetutorFind();
                    if (Build.VERSION.SDK_INT >= 11) {
                        //--post GB use serial executor by default --
                        leavetuFind.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Initial.SharePref.getStringtutor(),strcourseid);
                    }
                    else
                    {
                        //--GB uses ThreadPoolExecutor by default--
                        leavetuFind.execute();
                    }

                }
            });




            findtime = new Processfindtime();
            if (Build.VERSION.SDK_INT >= 11) {
                //--post GB use serial executor by default --
                findtime.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,strcourseid);
            }
            else
            {
                //--GB uses ThreadPoolExecutor by default--
                findtime.execute();
            }









            d1.show();

        }



    }


    private class ProcessleavetutorFind extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idtable",params[1]);
            ph.add("idtutor",params[0]);



            Initial.httpConn = new ServerConnect(Coures_detail.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.find, ph);
            Initial.SharePref = new PreferenceClass(getApplication());


            ShowMe.log("RESULT="+jResult);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            try {

                JSONObject jObject = new JSONObject(jResult);
                if(jObject.getString("status").trim().equals("true")){



                d1.dismiss();



                }


            } catch (JSONException e) {
                e.printStackTrace();
            }









        }




    }
    private class Processle extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idtable",strcourseid);
            ph.add("idtutor",Initial.SharePref.getStringtutor());



            Initial.httpConn = new ServerConnect(Coures_detail.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.le, ph);
            Initial.SharePref = new PreferenceClass(getApplication());


            ShowMe.log("RESULT="+jResult);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            try {

                JSONObject jObject = new JSONObject(jResult);
                if(!jObject.getString("status").trim().equals("true")){



                }


            } catch (JSONException e) {
                e.printStackTrace();
            }









        }




    }
    private View.OnClickListener cancel2 = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            d.dismiss();
        }
    };


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
        textView76.setText(tt1+":"+tt2);




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
        textView77.setText(t1+":"+t2);

        btnconn.setEnabled(false);
        btnconn.setBackgroundResource(R.drawable.custom_button_round_rect_border_diable);


        String date =  textView75.getText().toString();


        String time1 =  textView76.getText().toString();
        String time2 =   textView77.getText().toString();

        timetable = new Processgettimetable();
        if (Build.VERSION.SDK_INT >= 11) {
            //--post GB use serial executor by default --
            timetable.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,strcourseid,date,time1,time2);
        }
        else
        {
            //--GB uses ThreadPoolExecutor by default--
            timetable.execute();
        }




    }
    public void populateSetDate(int year, int month, int day) {


        textView75.setText(month+"/"+day+"/"+year);

        textView76.setText("");
        textView77.setText("");

        btnconn.setEnabled(false);
        btnconn.setBackgroundResource(R.drawable.custom_button_round_rect_border_diable);


    }

    private class Processgettimetable extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idtable",params[0]);
            ph.add("date",params[1]);
            ph.add("time1",params[2]);
            ph.add("time2",params[3]);
            ph.add("idtutor",Initial.SharePref.getStringtutor());

            Initial.httpConn = new ServerConnect(Coures_detail.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.checktimetable_day, ph);



            ShowMe.log("RESULT="+jResult);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            try {
                Initial.jObject = new JSONObject(jResult);

                if(Initial.jObject.getString("status").trim().equals("true")){



                    btnconn.setEnabled(true);
                    btnconn.setBackgroundResource(R.drawable.custom_button_round_rect_border);

                    numberroom = Integer.parseInt(Initial.jObject.getString("desc"));
                    Toast.makeText(ctx, "สามารถเลือกช่วงเวลานี้ได้",
                            Toast.LENGTH_LONG).show();



                }else{

                    btnconn.setEnabled(false);
                    btnconn.setBackgroundResource(R.drawable.custom_button_round_rect_border_diable);

                    Toast.makeText(ctx, "กรุณาเลือกช่วงเวลาอื่น",
                            Toast.LENGTH_LONG).show();

                }


            } catch (Exception e) {
                e.printStackTrace();
            }





        }




    }


    private class Processfindtime extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idtable",params[0]);


            Initial.httpConn = new ServerConnect(Coures_detail.this);
            jResulttime = Initial.httpConn.getDataFromServer(keysv+PHPPath.findtime, ph);



            ShowMe.log("RESULT="+jResulttime);




            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            try {
                Initial.jObject = new JSONObject(jResulttime);

                if(Initial.jObject.getString("status").trim().equals("true")){



                    wheretime = Initial.jObject.getString("desc").toString();
                    if(wheretime.equals("2")){

                        button20.setVisibility(View.VISIBLE);
                        button19.setVisibility(View.VISIBLE);
                        button23.setVisibility(View.VISIBLE);

                    }else if(wheretime.equals("1")){
                        button20.setVisibility(View.VISIBLE);
                        button19.setVisibility(View.VISIBLE);
                        button23.setVisibility(View.INVISIBLE);

                    }else if(wheretime.equals("0")){
                        button20.setVisibility(View.INVISIBLE);
                        button19.setVisibility(View.INVISIBLE);
                        button23.setVisibility(View.INVISIBLE);

                    }else{


                    }

                }


            } catch (Exception e) {
                e.printStackTrace();
            }





        }




    }
}
