package com.example.peem16.eakqlearning;

/**
 * Created by May- on 9/12/2017.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.HashMap;

public class PreferenceClass {

    public Context context;
    public SharedPreferences myPrefs;
    public static Editor ed;


    private static final String TAG_IDUserAccount = "IDUserAccount" ,
            TAG_idtutor = "idtutor" ,
            TAG_idemp = "idemp" ,
            TAG_idstudent = "idstudent" ,
            TAG_firstname = "firstname",
            TAG_lastname = "lastname",
            TAG_timetable = "id_timetable",
            TAG_QR = "QR_code", TAG_Coures = "id_coures", TAG_news1 = "TAG_news1", TAG_news2 = "TAG_news2", TAG_news3 = "TAG_news3"  , TAG_IDS = "TAG_IDS" , TAG_profile = "TAG_profile" ;


    private static final String TAG_position = "TAG_position";


    public static  ArrayList<HashMap<String, Object>> objArrayLists = null ;


    public static  ArrayList<HashMap<String, Object>> objArrayLists3 = null ;


    public PreferenceClass(Context c) {
        context = c;
    }


    public void setodj( ArrayList<HashMap<String, Object>>  strcourseid ){
        objArrayLists = strcourseid;

    }
    public void setodjstuden( ArrayList<HashMap<String, Object>>  strcourseid ){
        objArrayLists3 = strcourseid;

    }

    public void setposition(String strcourseid ){
        Prefs();
        ed = myPrefs.edit();
        ed.putString(TAG_position , strcourseid);

        ed.commit();
    }






    public void setIDS(String strcourseid ){
        Prefs();
        ed = myPrefs.edit();
        ed.putString(TAG_IDS , strcourseid);

        ed.commit();

    }
    public void setCoures(String strcourseid ){
        Prefs();
        ed = myPrefs.edit();
        ed.putString(TAG_Coures , strcourseid);

        ed.commit();

    }

    public void setnews1(String news ){
        Prefs();
        ed = myPrefs.edit();
        ed.putString(TAG_news1 , news);

        ed.commit();

    }
    public void setnews2(String news ){
        Prefs();
        ed = myPrefs.edit();
        ed.putString(TAG_news2 , news);

        ed.commit();

    }
    public void setnews3(String news ){
        Prefs();
        ed = myPrefs.edit();
        ed.putString(TAG_news3 , news);

        ed.commit();

    }

    public void settimetable(String assess ){
        Prefs();
        ed = myPrefs.edit();
        ed.putString(TAG_timetable, assess);

        ed.commit();

    }


    public void setPrefsAll(String IDUserAccount , String idtutor , String idemp  , String idstudent, String firstname,String lastname,String qr,String Profile){
        Prefs();
        ed = myPrefs.edit();
        ed.putString(TAG_IDUserAccount , IDUserAccount);
        ed.putString(TAG_idtutor , idtutor);
        ed.putString(TAG_idemp , idemp);
        ed.putString(TAG_idstudent , idstudent);

        ed.putString(TAG_firstname , firstname);
        ed.putString(TAG_lastname, lastname);
        ed.putString(TAG_QR, qr);
        ed.putString(TAG_profile, Profile);
        ed.commit();

    }

    public String getprofile(){
        Prefs();
        return myPrefs.getString(TAG_profile , "");
    }


    public String getStringtutor(){
        Prefs();
        return myPrefs.getString(TAG_idtutor , "");
    }
    public String getStringtimetable(){
        Prefs();
        return myPrefs.getString(TAG_timetable , "");
    }
    public String getStringposition(){
        Prefs();
        return myPrefs.getString(TAG_position , "");
    }

    public String getNews1(){
        Prefs();
        return myPrefs.getString(TAG_news1 , "");
    }

    public String getNews2(){
        Prefs();
        return myPrefs.getString(TAG_news2 , "");
    }

    public String getNews3(){
        Prefs();
        return myPrefs.getString(TAG_news3 , "");
    }

    public String getIDS(){
        Prefs();
        return myPrefs.getString(TAG_IDS , "");
    }


    public String getCoures(){
        Prefs();
        return myPrefs.getString(TAG_Coures , "");
    }




    public String getStringFirstName(){
        Prefs();
        return myPrefs.getString(TAG_firstname , "");
    }
    public String getidstudent(){
        Prefs();
        return myPrefs.getString(TAG_idstudent , "");
    }
    public String getStringIDUserAccount(){
        Prefs();
        return myPrefs.getString(TAG_IDUserAccount , "");
    }

    public String getStringQr_code(){
        Prefs();
        return myPrefs.getString(TAG_QR , "");
    }

    public String getStringLastname(){
        Prefs();
        return myPrefs.getString(TAG_lastname , "");
    }


    private void Prefs(){
        myPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }


    public void clearPrefs(){
        Prefs();
        ed = myPrefs.edit();
        ed.putString(TAG_IDUserAccount , null);
        ed.putString(TAG_idtutor , null);
        ed.putString(TAG_idemp , null);
        ed.putString(TAG_idstudent , null);
        ed.putString(TAG_firstname , null);
        ed.putString(TAG_lastname , null);
        ed.putString(TAG_QR , null);

        ed.commit();

    }
}

