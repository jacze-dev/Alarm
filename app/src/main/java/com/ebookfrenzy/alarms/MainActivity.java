package com.ebookfrenzy.alarms;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String txt = bundle.getString("msg");
        Log.d(TAG, "onReceive: " + txt);

        // Check if the received action is "set_alarm"
        if (intent.getAction().equalsIgnoreCase("set_alarm")) {
            Intent receive = new Intent(context, MainActivity.class);
            receive.putExtra("msg", txt);
            Toast.makeText(context, txt, Toast.LENGTH_LONG).show();
            Log.d(TAG, "onReceive: " + txt);
        }
        // Check if the received action is "android.intent.action.BOOT_COMPLETED"
        else if (intent.getAction().equalsIgnoreCase("android.intent.action.BOOT_COMPLETED")) {
            SaveAlarm saveAlarm = new SaveAlarm(context);
            saveAlarm.load();
        }
    }
}
