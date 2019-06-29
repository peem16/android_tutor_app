package com.example.peem16.eakqlearning;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static com.example.peem16.eakqlearning.Initial.SharePref;
import static com.example.peem16.eakqlearning.ServerConnect.KEY_SERVER;

public class MainActivity_Login extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    private boolean mBound = false;
    private Servicealert service;
    private String idrequest,idrequesttutor;
    private MenuItem item9;
    private  String keysv = ServerConnect.KEY_SERVER;
    private String jResult;
    public static final int REQUESTCODE_ITEMVIEW = 994;
    private  Processgetreq getreq;
    private String jResult98;

    private Processgetreqtutor getreqtutor;
    private String jResult32,jResult2;
    private Dialog d , d2;

    private  Processsend2 send2;
    private Processgetdate getdate;

    private TextView textView80,textView83,textView85,textView88;

    private Context context = this;
    private TextView btnkey,btnCancel,btnkey2,btnCancel2;
    String URL ;
    NavigationView navigationView;
    private TextView Badge,name,count,l1,l2,l3,l4;
    private ImageView imgProfile;
    private String str ;
    private Processgetcountass getcountass;
    private  Processgetcounmess getcounmess;
    private  TextView notifCount;
    private Processgetcountasstutor getcountasstutor;
    private Processgetcounmess_stu getcounmess_stu;
    private  Processsend send;
    private static Context ctx;
    public static void  close(){


        ((Activity)ctx).finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        ctx = this;

        d = new Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.leaddd);


        d2 = new Dialog(this);
        d2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d2.setContentView(R.layout.leaddd);

        textView80 = (TextView) d.findViewById(R.id.textView80) ;
        textView83 = (TextView) d.findViewById(R.id.textView83) ;
        textView85 = (TextView) d.findViewById(R.id.textView85) ;
        textView88 = (TextView) d.findViewById(R.id.textView88) ;

        l1 = (TextView) d2.findViewById(R.id.textView80) ;
        l2 = (TextView) d2.findViewById(R.id.textView83) ;
        l3 = (TextView) d2.findViewById(R.id.textView85) ;
        l4 = (TextView) d2.findViewById(R.id.textView88) ;


        btnkey2 = (TextView) d2.findViewById(R.id.btnkey);
        btnCancel2 = (TextView) d2.findViewById(R.id.btnCancel);

        btnkey2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                send2 = new Processsend2();
                if (Build.VERSION.SDK_INT >= 11) {
                    //--post GB use serial executor by default --
                    send2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,idrequesttutor,Initial.SharePref.getStringtutor(),"1");
                }
                else
                {
                    //--GB uses ThreadPoolExecutor by default--
                    send2.execute();
                }
            }
        });



        btnCancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send2 = new Processsend2();
                if (Build.VERSION.SDK_INT >= 11) {
                    //--post GB use serial executor by default --
                    send2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,idrequesttutor,Initial.SharePref.getStringtutor(),"2");
                }
                else
                {
                    //--GB uses ThreadPoolExecutor by default--
                    send2.execute();
                }

            }
        });

        btnkey = (TextView) d.findViewById(R.id.btnkey);
        btnkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                send = new Processsend();
                if (Build.VERSION.SDK_INT >= 11) {
                    //--post GB use serial executor by default --
                    send.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,idrequest,Initial.SharePref.getidstudent(),"1");
                }
                else
                {
                    //--GB uses ThreadPoolExecutor by default--
                    send.execute();
                }

            }
        });


        btnCancel = (TextView) d.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                send = new Processsend();
                if (Build.VERSION.SDK_INT >= 11) {
                    //--post GB use serial executor by default --
                    send.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,idrequest,Initial.SharePref.getidstudent(),"2");


                }
                else
                {
                    //--GB uses ThreadPoolExecutor by default--
                    send.execute();
                }

            }
        });
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//
//String ss = "2018-06-14 16:02:00";
//
//
//        try {
//
//
//
//            Date  eighteen = parser.parse(ss);
//
//
//            calendar.setTime(eighteen);
//
//            ShowMe.log("PPPPPPPPP"+eighteen);
//
//            setAlarm(calendar.getTimeInMillis());
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null); //set color
        navigationView.setNavigationItemSelectedListener(this);
        View v = navigationView.getHeaderView(0);

        imgProfile = (ImageView) v.findViewById(R.id.imgProfile);


        count = (TextView) findViewById(R.id.count);


        name = (TextView) v.findViewById(R.id.txtNameUser);

        name.setText(Initial.SharePref.getStringFirstName()+" "+Initial.SharePref.getStringLastname());

        URL = KEY_SERVER+"tutor3/public/storage/Pic/"+SharePref.getprofile()+"";
        Glide.with(getApplicationContext()).load(URL).into(imgProfile);
        imgProfile.getLayoutParams().height = 300;
        imgProfile.getLayoutParams().width = 300;
        imgProfile.setVisibility(View.VISIBLE);

        Initial.SharePref = new PreferenceClass(this);



        String sss = Initial.SharePref.getidstudent().toString();

        String sss2 = Initial.SharePref.getStringtutor().toString();

        getdate = new Processgetdate();
        if (Build.VERSION.SDK_INT >= 11) {
            //--post GB use serial executor by default --
            getdate.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        else
        {
            //--GB uses ThreadPoolExecutor by default--
            getdate.execute();
        }


        if(sss.equals("null")){
            hideItemtutor();

            getcountasstutor = new Processgetcountasstutor();
            if (Build.VERSION.SDK_INT >= 11) {
                //--post GB use serial executor by default --
                getcountasstutor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
            else
            {
                //--GB uses ThreadPoolExecutor by default--
                getcountasstutor.execute();
            }

            count.setY(-120);


            getreqtutor = new Processgetreqtutor();
            if (Build.VERSION.SDK_INT >= 11) {
                //--post GB use serial executor by default --
                getreqtutor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
            else
            {
                //--GB uses ThreadPoolExecutor by default--
                getreqtutor.execute();
            }

        }else if(sss2.equals("null")){


            hideItemstudent();

            getcountass = new Processgetcountass();
            if (Build.VERSION.SDK_INT >= 11) {
                //--post GB use serial executor by default --
                getcountass.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
            else
            {
                //--GB uses ThreadPoolExecutor by default--
                getcountass.execute();
            }


            getreq = new Processgetreq();
            if (Build.VERSION.SDK_INT >= 11) {
                //--post GB use serial executor by default --
                getreq.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
            else
            {
                //--GB uses ThreadPoolExecutor by default--
                getreq.execute();
            }




        }






        goToHome();
    }



    private void hideItemtutor()
    {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_pay).setVisible(false);
        nav_Menu.findItem(R.id.nav_qr).setVisible(false);
        nav_Menu.findItem(R.id.nav_ListCoures).setVisible(false);

        nav_Menu.findItem(R.id.nav_test).setVisible(false);

    }
    private void hideItemstudent()
    {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_Rollcall).setVisible(false);
        nav_Menu.findItem(R.id.nav_testtutor).setVisible(false);

    }

    private void goToHome(){
        home f = new home();

        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content,f);

        fragmentTransaction.addToBackStack(null);
        //fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        //manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);


         item9 = menu.findItem(R.id.menunotification);
        MenuItemCompat.setActionView(item9, R.layout.notificationicon);

        View view = MenuItemCompat.getActionView(item9);
         notifCount = (TextView)view.findViewById(R.id.actionbar_notifcation_textview);

        ImageButton  menunotification2 = (ImageButton)view.findViewById(R.id.menunotification2);

        menunotification2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Messge.class);
                startActivity(i);

            }
        });


        String sss = Initial.SharePref.getidstudent().toString();

        String sss2 = Initial.SharePref.getStringtutor().toString();


        if(sss.equals("null")){

            getcounmess = new Processgetcounmess();
            if (Build.VERSION.SDK_INT >= 11) {
                //--post GB use serial executor by default --
                getcounmess.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
            else
            {
                //--GB uses ThreadPoolExecutor by default--
                getcounmess.execute();
            }


        }else if(sss2.equals("null")){



            getcounmess_stu = new Processgetcounmess_stu();
            if (Build.VERSION.SDK_INT >= 11) {
                //--post GB use serial executor by default --
                getcounmess_stu.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
            else
            {
                //--GB uses ThreadPoolExecutor by default--
                getcounmess_stu.execute();
            }


        }







        return true;

    }

    @Override
        public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.menuhome) {


            Intent i = new Intent(this, MainActivity_Login.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);

            // Handle the camera action
        }else  if (id == R.id.menunotification){


            Intent i = new Intent(this, Messge.class);
            startActivity(i);




        }



        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_qr) {

            Intent i = new Intent(this,showqr.class);
            startActivity(i);

            // Handle the camera action
        }else if (id == R.id.nav_ListCoures) {


            Intent i = new Intent(this,List_Coures.class);
            startActivity(i);



        }else if (id == R.id.nav_pay) {


            Intent i = new Intent(this,Paymentbuy.class);
            startActivity(i);



        }else if (id == R.id.nav_edit) {


            Intent i = new Intent(this,UserEdit.class);
            startActivity(i);



        }
        else if (id == R.id.nav_Rollcall) {


            Intent i = new Intent(this,Rollcall.class);
            startActivity(i);



        }  else if (id == R.id.nav_contact) {
            Contact f = new Contact();
            Func.replaceFragment(f, this.getSupportFragmentManager());

        }else if (id == R.id.nav_Logout) {
            Initial.SharePref.clearPrefs();

            Intent i = new Intent(this,Login.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);

        }else if (id == R.id.nav_test) {

            Intent i = new Intent(this,asslist.class);
            startActivity(i);

        }else if (id == R.id.nav_home) {

            Intent i = new Intent(this,MainActivity_Login.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);

        }else if (id == R.id.nav_chat) {

            Intent i = new Intent(this,Chatmessage.class);
            startActivity(i);

        }else if (id == R.id.nav_testtutor) {

            Intent i = new Intent(this,asslisttutor.class);
            startActivity(i);

        }









        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private class Processgetreq extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("ids",Initial.SharePref.getidstudent());



            Initial.httpConn = new ServerConnect(MainActivity_Login.this);
            jResult2 = Initial.httpConn.getDataFromServer(keysv+PHPPath.getreq, ph);



            ShowMe.log("RESULT="+jResult2);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            try {

                JSONObject jObject = new JSONObject(jResult2);
                if(jObject.getString("status").trim().equals("true")){
                    JSONArray jArray = new JSONArray(jObject.getString("result"));
                    int size = jArray.length();
                    for (int i = 0; i < size; i++) {


                        JSONObject jsonObject = jArray.getJSONObject(i);


                    textView80.setText(jsonObject.getString(FieldConstants.name));
                    textView83.setText(jsonObject.getString(FieldConstants.date));
                    textView85.setText(jsonObject.getString(FieldConstants.Start_time)+"-"+jsonObject.getString(FieldConstants.End_time));
                    textView88.setText(jsonObject.getString(FieldConstants.number));

                        idrequest = jsonObject.getString(FieldConstants.id_timetable_request_stu);

                    d.show();



                    }

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }









        }




    }



    private class Processgetreqtutor extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idtutor",Initial.SharePref.getStringtutor());



            Initial.httpConn = new ServerConnect(MainActivity_Login.this);
            jResult2 = Initial.httpConn.getDataFromServer(keysv+PHPPath.getreqtutor, ph);



            ShowMe.log("RESULT="+jResult2);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            try {

                JSONObject jObject = new JSONObject(jResult2);
                if(jObject.getString("status").trim().equals("true")){
                    JSONArray jArray = new JSONArray(jObject.getString("result"));
                    int size = jArray.length();
                    for (int i = 0; i < size; i++) {


                        JSONObject jsonObject = jArray.getJSONObject(i);


                        l1.setText(jsonObject.getString(FieldConstants.name));
                        l2.setText(jsonObject.getString(FieldConstants.date));
                        l3.setText(jsonObject.getString(FieldConstants.Start_time)+"-"+jsonObject.getString(FieldConstants.End_time));
                        l4.setText(jsonObject.getString(FieldConstants.number));

                        idrequesttutor = jsonObject.getString(FieldConstants.id_timetable_request_tutor);


                        d2.show();



                    }

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }









        }




    }

    private class Processgetcountass extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("ids",Initial.SharePref.getidstudent());



            Initial.httpConn = new ServerConnect(MainActivity_Login.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.getcountass, ph);



            ShowMe.log("RESULT="+jResult);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            try {

                JSONObject jObject = new JSONObject(jResult);
                if(jObject.getString("status").trim().equals("true")){

                    count.setText(jObject.getString("desc").toString());





                }


            } catch (JSONException e) {
                e.printStackTrace();
            }









        }




    }

    private class Processsend extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idreq",params[0]);
            ph.add("idstu",params[1]);
            ph.add("send",params[2]);



            Initial.httpConn = new ServerConnect(MainActivity_Login.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.send, ph);



            ShowMe.log("RESULT="+jResult);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            try {

                JSONObject jObject = new JSONObject(jResult);
                if(jObject.getString("status").trim().equals("true")){


                    d.dismiss();
                    getreq = new Processgetreq();
                    if (Build.VERSION.SDK_INT >= 11) {
                        //--post GB use serial executor by default --
                        getreq.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    }
                    else
                    {
                        //--GB uses ThreadPoolExecutor by default--
                        getreq.execute();
                    }

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }









        }




    }

    private class Processsend2 extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idreq",params[0]);
            ph.add("idtutor",params[1]);
            ph.add("send",params[2]);



            Initial.httpConn = new ServerConnect(MainActivity_Login.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.sendtutor, ph);



            ShowMe.log("RESULT="+jResult);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            try {

                JSONObject jObject = new JSONObject(jResult);
                if(jObject.getString("status").trim().equals("true")){


                    d2.dismiss();
                    getreqtutor = new Processgetreqtutor();
                    if (Build.VERSION.SDK_INT >= 11) {
                        //--post GB use serial executor by default --
                        getreqtutor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    }
                    else
                    {
                        //--GB uses ThreadPoolExecutor by default--
                        getreqtutor.execute();
                    }

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }









        }




    }
    private class Processgetcountasstutor extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idt",Initial.SharePref.getStringtutor());



            Initial.httpConn = new ServerConnect(MainActivity_Login.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.getcountasstutor, ph);



            ShowMe.log("RESULT="+jResult);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            try {

                JSONObject jObject = new JSONObject(jResult);
                if(jObject.getString("status").trim().equals("true")){

                    count.setText(jObject.getString("desc").toString());





                }


            } catch (JSONException e) {
                e.printStackTrace();
            }









        }




    }


    private class Processgetcounmess extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("idt",Initial.SharePref.getStringtutor());



            Initial.httpConn = new ServerConnect(MainActivity_Login.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.getcounmess, ph);



            ShowMe.log("RESULT="+jResult);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            try {

                JSONObject jObject = new JSONObject(jResult);
                if(jObject.getString("status").trim().equals("true")){

                    notifCount.setText(jObject.getString("desc").toString());





                }


            } catch (JSONException e) {
                e.printStackTrace();
            }









        }




    }









    private class Processgetcounmess_stu extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("ids",Initial.SharePref.getidstudent());



            Initial.httpConn = new ServerConnect(MainActivity_Login.this);
            jResult = Initial.httpConn.getDataFromServer(keysv+PHPPath.getcounmess_stu, ph);



            ShowMe.log("RESULT="+jResult);



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            try {

                JSONObject jObject = new JSONObject(jResult);
                if(jObject.getString("status").trim().equals("true")){

                    notifCount.setText(jObject.getString("desc").toString());





                }


            } catch (JSONException e) {
                e.printStackTrace();
            }









        }




    }
    private class Processgetdate extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {



            ParamHolder ph = new ParamHolder();
            ph.add("iduac",Initial.SharePref.getStringIDUserAccount());



            Initial.httpConn = new ServerConnect(MainActivity_Login.this);
            jResult32 = Initial.httpConn.getDataFromServer(keysv+PHPPath.getDATE, ph);



            ShowMe.log("RESULT="+jResult32);

            try {
                Initial.jObject = new JSONObject(jResult32);

                if(Initial.jObject.getString("status").trim().equals("true")){
                    JSONArray jArray = new JSONArray(Initial.jObject.getString("result"));
                    int size = jArray.length();
                    for(int i=0; i<size ; i++){
                        JSONObject jsonObject = jArray.getJSONObject(i);


                     int amount = Integer.parseInt(jsonObject.getString("amount"));

                            for (int x = 0; x<amount; x++){

                              String  day =  jsonObject.getString(x+"day");

                              String  time =  jsonObject.getString("time");


                                String Date = day +" "+time;
                                SimpleDateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Date date2 = null;
                                try {
                                    date2 = dateFormat.parse(Date);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }



                                Calendar calendar = Calendar.getInstance();
//                                calendar.setTimeInMillis(System.currentTimeMillis());
                                calendar.setTime(date2);





                                final int _id = (int) System.currentTimeMillis();

                                Intent intent = new Intent(MainActivity_Login.this, AlarmReceiver.class);
                                intent.putExtra("name", jsonObject.getString("name"));
                                intent.putExtra("time", jsonObject.getString("time"));
                                intent.putExtra("type", "1");
                                PendingIntent pendingIntent = PendingIntent.getService(MainActivity_Login.this, _id, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                                AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar
                                            .getTimeInMillis(), pendingIntent);






                                SimpleDateFormat  dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                                Date date3 = null;
                                try {
                                    date3 = dateFormat2.parse(day);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                Calendar calendar2 = Calendar.getInstance();
//                                calendar.setTimeInMillis(System.currentTimeMillis());
                                calendar2.setTime(date3);

                                final int _id2 = (int) System.currentTimeMillis();

                                Intent intent2 = new Intent(MainActivity_Login.this, AlarmReceiver.class);
                                intent2.putExtra("name", jsonObject.getString("name"));
                                intent2.putExtra("time", jsonObject.getString("time"));
                                intent2.putExtra("type", "2");

                                PendingIntent pendingIntent2 = PendingIntent.getService(MainActivity_Login.this, _id2, intent2, PendingIntent.FLAG_CANCEL_CURRENT);
                                AlarmManager alarmManager2 = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                                alarmManager2.set(AlarmManager.RTC_WAKEUP, calendar2
                                        .getTimeInMillis(), pendingIntent2);



                        }



                    }




                }


            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);








        }




    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent(this,Servicealert.class);
        bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);
    }


    private ServiceConnection serviceConnection = new ServiceConnection(){


        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
          Servicealert.MyBinder myBinder = (Servicealert.MyBinder) iBinder;
            service = myBinder.getService();
          mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound= false;
        }
    };
}
