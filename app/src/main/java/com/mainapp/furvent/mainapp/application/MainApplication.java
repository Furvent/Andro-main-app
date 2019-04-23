package com.mainapp.furvent.mainapp.application;

import android.app.Application;

import com.squareup.otto.Bus;

public class MainApplication extends Application {

    private static Bus bus;
    public static Bus getBus() {
        return bus;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        bus = new Bus();
    }
}
