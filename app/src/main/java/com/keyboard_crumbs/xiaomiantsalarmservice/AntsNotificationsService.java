package com.keyboard_crumbs.xiaomiantsalarmservice;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v7.app.NotificationCompat;
import android.telephony.PhoneNumberUtils;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AntsNotificationsService extends NotificationListenerService {

    private String TAG = this.getClass().getSimpleName();
    private SharedPreferences settings;

    @Override
    public void onCreate() {
        super.onCreate();
        settings = getApplicationContext().getSharedPreferences("settings", 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        if (settings.getBoolean("enable", false)) {
            if (sbn.getPackageName().equals("com.ants360.yicamera")) {
                //Log.i(TAG, sbn.getNotification().extras.getString("android.title"));
                Intent dialogIntent = new Intent(this, AlarmActivity.class);
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle b = new Bundle();
                b.putString("text", sbn.getNotification().extras.getString("android.title"));
                dialogIntent.putExtras(b);
                if (settings.getBoolean("removeNotification", false)) {
                    cancelNotification(sbn.getKey());
                }
                startActivity(dialogIntent);
            }
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
       /* Log.i(TAG,"********** onNOtificationRemoved");
        Log.i(TAG, "ID :" + sbn.getId() + "\t" + sbn.getNotification().tickerText + "\t" + sbn.getPackageName());
        Intent i = new  Intent("com.keyboard_crumbs.xiaomiantsalarmservice.NOTIFICATION_LISTENER_SERVICE");
        i.putExtra("notification_event", "onNotificationRemoved :" + sbn.getPackageName() + "\n");*/

        //sendBroadcast(i);
    }

}