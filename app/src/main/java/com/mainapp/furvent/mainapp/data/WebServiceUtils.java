package com.mainapp.furvent.mainapp.data;

import android.os.SystemClock;
import android.util.Log;

import com.google.gson.Gson;
import com.mainapp.furvent.mainapp.data.city.CityBean;
import com.mainapp.furvent.mainapp.data.city.ResultJsonCityAPI;

import java.util.ArrayList;

public class WebServiceUtils {
    public static EleveBean loadEleveFromWeb() throws Exception {
        SystemClock.sleep(2000);
        return new EleveBean("Triton", "Blorrwgwgwgw");
    }

    public static ArrayList<CityBean> loadCityListFromAPI(String postalCode) throws Exception {
        String login = "login=webserviceexemple";
        String apiKey = "apikey=sof940dd26cf107eabf8bf6827f87c3ca8e8d82546";
        String url = "http://www.citysearch-api.com/fr/city?" + login + "&" + apiKey + "&cp=" + postalCode;
        Gson gson = new Gson();

        String resultRequestJSON = OkHttpUtils.SendGetOkHttpRequestAPICitySearch(url);

        Log.d("TAG_", resultRequestJSON);

        ResultJsonCityAPI result = gson.fromJson(resultRequestJSON, ResultJsonCityAPI.class);

        if (result.getErrorCityAPIResponse() != null) {
            throw new Exception((result.getErrorCityAPIResponse().getErrorMessage()));
        }
        ArrayList<CityBean> cityListToSend = result.getCityList();
        debugCityList(cityListToSend);
        return cityListToSend;
    }

    private static void debugCityList(ArrayList<CityBean> cityListToSend) {
        for (CityBean city : cityListToSend) {
            Log.d("TAG_CITY_DEBUG", "City name:" + city.getCityName() + " and postal code: " + city.getPostalCode());
        }
    }
}
