package com.mainapp.furvent.mainapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.mainapp.furvent.mainapp.NotificationUtils;

public class NotificationOnMobileStartService extends Service {
    public NotificationOnMobileStartService() {
    }

    @Override
    public void onCreate() {
        Log.w("TAG_", "service NotificationOnMobileStartService started");
        super.onCreate();
        NotificationUtils.createInstantNotification(this,"Don't forget my app !");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
