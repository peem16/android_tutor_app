package com.example.peem16.samms;

/**
 * Created by May- on 9/12/2017.
 */

import android.util.Log;

public  class ShowMe
{
    private static boolean showLog = true;
    private static String TAG_LOG = "SAMMS";
    public  static void log(String message){
        if(showLog) Log.i(TAG_LOG,message);
    }
}
