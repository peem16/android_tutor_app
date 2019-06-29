package com.example.peem16.eakqlearning;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Chronometer;
import android.widget.Toast;

public class Servicealert extends Service {



    private IBinder mBinder = new MyBinder();
    private Chronometer chronometer;
    long second;
    long millis;

        public class MyBinder extends Binder{

        Servicealert getService(){

            return Servicealert.this;
        }
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        chronometer = new Chronometer(this);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();



    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {



        Toast.makeText(this, "กรุณาเลือกหลัง 08.00",
                Toast.LENGTH_SHORT).show();

            return START_STICKY;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void onCount(){
            millis = SystemClock.elapsedRealtime() - chronometer.getBase();
            second = millis/1000;

        Toast.makeText(this, ""+second,
                Toast.LENGTH_SHORT).show();

    }


}
