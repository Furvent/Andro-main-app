package com.mainapp.furvent.mainapp.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

public class LanguageBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = "Changement de langue : " + Locale.getDefault().getDisplayLanguage();
        Log.w("TAG_", message);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
