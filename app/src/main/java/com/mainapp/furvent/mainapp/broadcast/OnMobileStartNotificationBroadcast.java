package com.mainapp.furvent.mainapp.broadcast;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.mainapp.furvent.mainapp.NotificationUtils;
import com.mainapp.furvent.mainapp.service.GPSCoordinateService;
import com.mainapp.furvent.mainapp.service.NotificationOnMobileStartService;

public class OnMobileStartNotificationBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentService = new Intent(context,NotificationOnMobileStartService.class);
        Log.w("TAG_", "Je suis dans onReceive de OnMobileStartNotificationBroadcast");
        context.startService(intentService);

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
