package com.mainapp.furvent.mainapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
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
        menu.add(0, 1, 0, "Alert Dialog");
        menu.add(0, 2, 1, "Time Picker");
        menu.add(0, 3, 1, "Date Picker");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.w("TAG_", "I'm in onOptionsItemSelected");
        switch (item.getItemId()) {
            case 1:
                createDialogAlert();
                break;
            case 2:
                createTimePicker();
                break;
            case 3:
                createDatePicker();
                break;
        }
        return super.onOptionsItemSelected(item);
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
}
