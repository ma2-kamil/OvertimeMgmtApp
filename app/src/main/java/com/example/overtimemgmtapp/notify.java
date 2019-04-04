package com.example.overtimemgmtapp;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class notify extends Application {

    public static final String CHANNEL_ID = "shiftfilled";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
    }

    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel ch = new NotificationChannel(
                    CHANNEL_ID,
                    "Check if shift has been filled",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            ch.setDescription("This is description");

            NotificationManager mg = getSystemService(NotificationManager.class);
            mg.createNotificationChannel(ch);

        }

    }



}
