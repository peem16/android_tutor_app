package com.example.peem16.samms;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by May- on 9/12/2017.
 */

public class Initial extends Activity {

        public static ServerConnect httpConn = null;
        public static JSONObject jObject = null;
        public static JSONArray jArray = null;
        public static JSONObject jsonObject = null;
        public static JSONArray jsonArray = null;
        public static PreferenceClass SharePref = null;




        public static AlertDialog.Builder dialog;
        public static boolean isOnline(Context context) {
                ConnectivityManager cm =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                        return true;
                }
                return false;
        }


}

