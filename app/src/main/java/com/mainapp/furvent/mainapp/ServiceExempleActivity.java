package com.mainapp.furvent.mainapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mainapp.furvent.mainapp.application.MainApplication;
import com.mainapp.furvent.mainapp.service.GPSCoordinateService;
import com.squareup.otto.Subscribe;

public class ServiceExempleActivity extends AppCompatActivity {

    private Button btStop;
    private Button btStart;
    private TextView tvCoordinates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_exemple);
//        MainApplication.getBus().register(this);

        btStart = findViewById(R.id.btStart);
        btStop = findViewById(R.id.btStop);
        tvCoordinates = findViewById(R.id.textViewCoordinate);

        btStart.setOnClickListener(v -> {
            startService(new Intent(this, GPSCoordinateService.class));
        });

        btStop.setOnClickListener(v -> {
            Log.w("TAG_", "BUTTON STOP CLICKED");
            stopService(new Intent(this, GPSCoordinateService.class));
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        MainApplication.getBus().register(this);
    }

    @Subscribe
    public void showCoordinate(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        String text = "Latitude: " + latitude + " Longitude: " + longitude;
        tvCoordinates.setText(text);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onStop() {
        MainApplication.getBus().unregister(this);
        super.onStop();
    }
}