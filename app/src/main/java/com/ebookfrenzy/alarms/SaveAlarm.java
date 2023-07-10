package com.ebookfrenzy.alarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.Calendar;

public class SaveAlarm {
    SharedPreferences sharedPreferences;
    Context context;

    public SaveAlarm(Context context){
        // Initialize SharedPreferences with a unique name "branaAlarm"
        sharedPreferences = context.getSharedPreferences("branaAlarm", context.MODE_PRIVATE);
        this.context = context;
    }

    public void store(int hour, int minute){
        // Store the selected hour and minute values in SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("hour", hour);
        editor.putInt("minute", minute);
        editor.commit();
    }

    public void load(){
        // Load the stored hour and minute values from SharedPreferences
        int hour = sharedPreferences.getInt("hour", 0);
        int minute = sharedPreferences.getInt("minute", 0);
        alarmSet(hour, minute);
    }

    public void alarmSet(int hour, int minute){
        // Set the alarm using the provided hour and minute values

        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.HOUR_OF_DAY, hour);
        calendar.set(calendar.MINUTE, minute);
        calendar.set(calendar.SECOND, 0);

        AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);

        // Create an intent to be triggered when the alarm fires
        Intent intent = new Intent(context, MyReceiver.class);
        intent.setAction("set_alarm");
        intent.putExtra("msg", "start doing your task");

        // Create a PendingIntent for the alarm
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Set the alarm using AlarmManager with the specified time and PendingIntent
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
    }
}
