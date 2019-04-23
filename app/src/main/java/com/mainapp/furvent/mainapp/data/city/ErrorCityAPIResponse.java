package com.mainapp.furvent.mainapp.data.city;

import com.google.gson.annotations.SerializedName;

public class ErrorCityAPIResponse {
    @SerializedName("message")
    private String errorMessage;

    @SerializedName("code")
    private int errorCode;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
