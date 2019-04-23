package com.mainapp.furvent.mainapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class NotificationActivity extends AppCompatActivity {

    private Button btScheduleNotification;
    private Button btInstantNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        btScheduleNotification = findViewById(R.id.btScheduleNotification);
        btInstantNotification = findViewById(R.id.btInstantNotification);

        btInstantNotification.setOnClickListener(v -> {
            NotificationUtils.createInstantNotification(this, "This is my first notification");
        });

        btScheduleNotification.setOnClickListener(v -> {
            NotificationUtils.createScheduleNotification(this, "This is my first delayed notification", 1);
        });
    }
}
