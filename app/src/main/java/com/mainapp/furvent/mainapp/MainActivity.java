package com.mainapp.furvent.mainapp;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private final int ID_MENU_ALERT_DIALOG = 1;
    private final int ID_MENU_TIME_PICKER = 2;
    private final int ID_MENU_DATE_PICKER = 3;
    private final int ID_MENU_SERVICE = 4;
    private final int ID_MENU_NOTIFICATION = 5;
    private final int ID_MENU_TP_ELEVE_LIST = 6;
    private final int ID_MENU_WEB_EXEMPLE = 7;
    private final int ID_MENU_TP_WEB_SERVICE = 8;

    private final int REQUEST_CODE_ACCESS_FINE_LOCATION = 1;

    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        calendar = Calendar.getInstance();
        Log.w("TAG_", "I'm in onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.w("TAG_", "I'm in onCreateOptionsMenu");
        menu.add(0, ID_MENU_ALERT_DIALOG, 0, "Alert Dialog");
        menu.add(0, ID_MENU_TIME_PICKER, 1, "Time Picker");
        menu.add(0, ID_MENU_DATE_PICKER, 1, "Date Picker");
        menu.add(0, ID_MENU_SERVICE, 1, "Service Exemple");
        menu.add(0, ID_MENU_NOTIFICATION, 1, "Notification Exemple");
        menu.add(0, ID_MENU_TP_ELEVE_LIST, 1, "TP Eleve list");
        menu.add(0, ID_MENU_WEB_EXEMPLE, 1, "Web Exemple");
        menu.add(0, ID_MENU_TP_WEB_SERVICE, 1, "TP Web Service");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.w("TAG_", "I'm in onOptionsItemSelected");
        switch (item.getItemId()) {
            case ID_MENU_ALERT_DIALOG:
                createDialogAlert();
                break;
            case ID_MENU_TIME_PICKER:
                createTimePicker();
                break;
            case ID_MENU_DATE_PICKER:
                createDatePicker();
                break;
            case ID_MENU_SERVICE:
                launchServiceActivity();
                break;
            case ID_MENU_NOTIFICATION:
                launchNotoficationActivity();
                break;
            case ID_MENU_TP_ELEVE_LIST:
                launchTPEleveListActivity();
            case ID_MENU_WEB_EXEMPLE:
                launchWebExempleActivity();
            case ID_MENU_TP_WEB_SERVICE:
                launchTPWebService();
        }
        return super.onOptionsItemSelected(item);
    }

    private void launchTPWebService() {
        Intent intent = new Intent(this, TPWebServiceActivity.class);
        startActivity(intent);
    }

    private void launchWebExempleActivity() {
        Intent intent = new Intent(this, WebExempleActivity.class);
        startActivity(intent);
    }

    private void launchTPEleveListActivity() {
        Intent intent = new Intent(this, TPElevesListActivity.class);
        startActivity(intent);
    }

    private void launchNotoficationActivity() {
        startNotificationActivy();
    }

    private void createDialogAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        String text = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(calendar.getTime());
        alertDialogBuilder.setMessage("Date et heure sélectionnées : " + text)
                .setTitle("Bravo ! Appuyer sur 'Toast' pour afficher un... Toast.")
                .setPositiveButton("Toast",
                        (dialog, which) -> {
                            Toast.makeText(MainActivity.this, "Retour dans quelque secondes...",
                                    Toast.LENGTH_SHORT).show();
                        }
                );
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
        alertDialogBuilder.show();
    }

    private void createTimePicker() {

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, minute) -> {
                    calendar.set(Calendar.MINUTE, minute);
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    String time = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime());
                    Toast.makeText(MainActivity.this, "Heure choisie : " + time,
                            Toast.LENGTH_SHORT).show();
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), false);
        timePickerDialog.show();
    }

    private void createDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    String date = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());
                    Toast.makeText(MainActivity.this, "Date choisie : " + date,
                            Toast.LENGTH_SHORT).show();
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
                );
        datePickerDialog.show();
    }

    private void launchServiceActivity() {
        checkIfPermissionGrantedAndAskIfNot();
    }

    private void checkIfPermissionGrantedAndAskIfNot() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            startServiceActivity();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_ACCESS_FINE_LOCATION:
                getPermissionAnswerAboutAccessFineLocation();
        }
    }

    private void getPermissionAnswerAboutAccessFineLocation() {
        Log.w("TAG_", "mokeFunction");
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            startServiceActivity();
        } else {
            Toast.makeText(this, "Besoin de pouvoir accéder à votre localisation pour lancer le service.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void startServiceActivity() {
        Intent intent = new Intent(this, ServiceExempleActivity.class);
        startActivity(intent);
    }

    private void startNotificationActivy() {
        Intent intent = new Intent(this, NotificationActivity.class);
        startActivity(intent);
    }
}
