package com.gvtech.serviceathome.models;

import com.google.gson.annotations.SerializedName;

public class ChangePasswordModel {

    @SerializedName("ResultCode")
    private String resultCode;
    @SerializedName("ResultMessage")
    private String resultMessage;

    public ChangePasswordModel(String resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
