package com.mainapp.furvent.mainapp.broadcast;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.mainapp.furvent.mainapp.NotificationUtils;

public class NotificationScheduledBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.w("TAG_", "Je suis dans onReceive de NotificationScheduledBroadcast");
        //On récupère la notification reçu
        Notification notification = intent.getParcelableExtra("Delay");
        //on l'affiche
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(29, notification);
    }
}
