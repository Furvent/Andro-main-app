package com.mainapp.furvent.mainapp;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.mainapp.furvent.mainapp.broadcast.NotificationScheduledBroadcast;

public class NotificationUtils {
    private static String CHANNEL_ID = "NOTIFICATION_TEST_CHANNEL";
    private static final CharSequence CHANNEL_NAME= "Commandes";

    private static void initChannel(Context context) {
        // A partir d'Oreo
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // Réglages du channel
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, notificationManager.IMPORTANCE_HIGH);
        channel.setDescription("Commandes");
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setVibrationPattern(new long[] {100, 200, 300, 400, 500, 400, 300, 200, 400});

        notificationManager.createNotificationChannel(channel);
    }

    public static void createInstantNotification(Context context, String message) {
        initChannel(context);
        Log.w("TAG_", "I'm in createInstantNotification");
        Intent intent = new Intent(context, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        //La notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, CHANNEL_ID);
        notificationBuilder.setSmallIcon(R.drawable.ic_notif_first)
                .setContentTitle("Le titre")
                .setContentText(message)
                .setContentIntent(pendingIntent)//le clic dessus
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setDefaults(Notification.DEFAULT_ALL);//Son + afficher la notification

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(29, notificationBuilder.build());
    }


    public static void createScheduleNotification(Context context, String message, long delay) {
        //Initialisation du chanel
        initChannel(context);
        Log.w("TAG_", "I'm in createScheduleNotification");
        //La notification
        NotificationCompat.Builder builder= new NotificationCompat.Builder(context, CHANNEL_ID);
        builder.setContentTitle("ScheduledNotification");
        builder.setContentText(message);
        builder.setSmallIcon(R.drawable.ic_notif_first);
        //Redirection vers le broadcast
        Intent intent= new Intent(context, NotificationScheduledBroadcast.class);
        intent.putExtra("Delay", builder.build());
        PendingIntent pendingIntent= PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //La dans le futur
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager= (AlarmManager)
                context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }
}
