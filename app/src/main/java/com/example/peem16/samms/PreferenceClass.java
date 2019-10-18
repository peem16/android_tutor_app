package com.example.peem16.samms;

/**
 * Created by May- on 9/12/2017.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PreferenceClass {

    public Context context;
    public SharedPreferences myPrefs;
    public static Editor ed;


    private static final String TAG_ID = "id" ,
            TAG_Username = "username" ,
            TAG_Password = "password" ,
            TAG_First_name = "first_name" ,
            TAG_Last_name = "last_name",
            TAG_Id_card = "id_card",
            TAG_Birth_date = "birth_date",
            TAG_Mobile_number = "mobile_number",
            TAG_Phone_number = "phone_number",
            TAG_Email = "email",
            TAG_Address = "address",
            TAG_Company_id = "company_id"  ,
            TAG_Is_active = "is_active" ,
            TAG_Is_deleted = "is_deleted",
            TAG_Created_date = "created_date",
            TAG_Updated_date= "updated_date";


    public static  ArrayList<HashMap<String, Object>> objArrayLists ;


    public PreferenceClass(Context c) {
        context = c;
    }



    public void setPrefsAll(String first_name , String last_name , String company_id  , String id){
        Prefs();
        ed = myPrefs.edit();
        ed.putString(TAG_First_name , first_name);
        ed.putString(TAG_Last_name , last_name);
        ed.putString(TAG_Company_id , company_id);
        ed.putString(TAG_ID , id);
        ed.commit();

    }

    public String getID(){
        Prefs();
        return myPrefs.getString(TAG_ID , "");
    }


    private void Prefs(){
        myPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }


    public void clearPrefs(){
        Prefs();
        ed = myPrefs.edit();
        ed.putString(TAG_First_name , null);
        ed.putString(TAG_Last_name , null);
        ed.putString(TAG_Company_id , null);
        ed.putString(TAG_ID , null);
        ed.commit();

    }
}

