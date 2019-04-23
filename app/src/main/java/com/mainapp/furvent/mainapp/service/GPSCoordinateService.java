package com.mainapp.furvent.mainapp.service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.mainapp.furvent.mainapp.application.MainApplication;

public class GPSCoordinateService extends Service implements LocationListener {

    private final String TAG = "TAG_";

    LocationManager locationMgr;

    public GPSCoordinateService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.w(TAG, "SERVICE GPS CREATED");
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.w(TAG, "PERMISSION TO GPS DENIED. SERVICE KILLED");
            stopSelf();

            return;
        }
        locationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(locationMgr.getAllProviders().contains(LocationManager.NETWORK_PROVIDER))
            locationMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, this);
        if(locationMgr.getAllProviders().contains(LocationManager.GPS_PROVIDER))
            locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.w(TAG, "SERVICE GPS REQUESTED FROM NEW USER");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        locationMgr.removeUpdates(this);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onLocationChanged(Location location) {
//        Log.w(TAG, "Localization updated");
//        Double latitude = location.getLatitude();
//        Double longitude = location.getLongitude();
//        String text = "Latitude: " + latitude + " Longitude: " + longitude;
//        Toast.makeText(this, text,
//                Toast.LENGTH_LONG).show();
        MainApplication.getBus().post(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        String text = "";
        switch (status) {
            case LocationProvider.AVAILABLE:
                text += "GPS AVAILABLE";
                break;
            case LocationProvider.OUT_OF_SERVICE:
                text += "GPS OUT_OF_SERVICE";
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                text += "GPS TEMPORARILY_UNAVAILABLE";
                break;
            }

        Log.w(TAG, "GPS onStatusChanged: " + text);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.w(TAG, "GPS STARTED ?");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.w(TAG, "GPS STOPPED ?");
    }
}
