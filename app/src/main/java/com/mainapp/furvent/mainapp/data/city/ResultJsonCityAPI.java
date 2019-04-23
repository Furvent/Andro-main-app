package com.mainapp.furvent.mainapp.data.city;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultJsonCityAPI {
    @SerializedName("results")
    private ArrayList<CityBean> cityList;

    @SerializedName("nbr")
    private int numberOfCities;

    @SerializedName("errors")
    private ErrorCityAPIResponse errorCityAPIResponse;

    public ArrayList<CityBean> getCityList() {
        return cityList;
    }

    public void setCityList(ArrayList<CityBean> cityList) {
        this.cityList = cityList;
    }

    public int getNumberOfCities() {
        return numberOfCities;
    }

    public void setNumberOfCities(int numberOfCities) {
        this.numberOfCities = numberOfCities;
    }

    public ErrorCityAPIResponse getErrorCityAPIResponse() {
        return errorCityAPIResponse;
    }

    public void setErrorCityAPIResponse(ErrorCityAPIResponse errorCityAPIResponse) {
        this.errorCityAPIResponse = errorCityAPIResponse;
    }
}
