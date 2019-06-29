package com.example.peem16.eakqlearning;

/**
 * Created by May- on 9/12/2017.
 */

import android.util.Log;

public  class ShowMe
{
    private static boolean showLog = true;
    private static String TAG_LOG = "Peem";
    public  static void log(String message){
        if(showLog) Log.i(TAG_LOG,message);
    }
}
