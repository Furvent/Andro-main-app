package com.mainapp.furvent.mainapp.data.city;

import com.google.gson.annotations.SerializedName;

public class CityBean {
    @SerializedName("ville")
    private String postalCode;

    @SerializedName("cp")
    private String cityName;

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
