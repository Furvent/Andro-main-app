package com.mainapp.furvent.mainapp.data;

import android.util.Log;

import com.mainapp.furvent.mainapp.data.city.ResultJsonCityAPI;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpUtils {
    public static String SendGetOkHttpRequest (String url) throws Exception {
        Log.w ("TAG_", "url : " + url);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (response.code() < 200 || response.code() >= 300) {
            throw new Exception("Réponse du serveur incorrect : " + response.code());
        } else {
            return response.body().string();
        }
    }

    public static String SendGetOkHttpRequestAPICitySearch(String url) throws Exception{
        Log.w("TAG_", "url send to API_CITY_SEARCH: " + url);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (response.code() < 200 || response.code() >= 300) {
            throw new Exception("Réponse du serveur incorrect : " + response.code());
        } else {
            return response.body().string();
        }
    }
}
