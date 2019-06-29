package com.example.peem16.eakqlearning;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by octoboy on 22/11/2557.
 */
public class AlarmReceiver extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

       NotificationCompat.Builder notification = new NotificationCompat.Builder(AlarmReceiver.this);
        notification.setSmallIcon(R.mipmap.ic_launcher);
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        String name  = intent.getExtras().get("name").toString();
        String time  = intent.getExtras().get("time").toString();
        String type  = intent.getExtras().get("type").toString();


        if(type.equals("1")){

            inboxStyle.setBigContentTitle("แจ้งเตือน");
            inboxStyle.addLine("วิชา "+name+"ถึงเวลาเรียนแล้ว");
//            inboxStyle.addLine("เวลา "+time);
        }else{
            inboxStyle.setBigContentTitle("แจ้งเตือน");
            inboxStyle.addLine("วันนี้มีเรียนวิชา "+name);
            inboxStyle.addLine("เวลา "+time);
        }


//        for (String i:messge){
//
//
//        }
        notification.setStyle(inboxStyle);

        Intent i = new Intent(AlarmReceiver.this,MainActivity_Login.class);
        TaskStackBuilder builder = TaskStackBuilder.create(AlarmReceiver.this);
        builder.addParentStack(MainActivity_Login.class);
        builder.addNextIntent(i);
        PendingIntent pendingIntent = builder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);


        notification.setContentIntent(pendingIntent);
        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,notification.build());


        return super.onStartCommand(intent, flags, startId);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}