package com.gvtech.serviceathome.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Service {

    @SerializedName("ResultCode")
    private String resultCode;
    @SerializedName("ResultMessage")
    private String resultMessage;
    @SerializedName("ResultObject")
    private List<ServiceItem> serviceItems;

    public Service(String resultCode, String resultMessage, List<ServiceItem> serviceItems) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.serviceItems = serviceItems;
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

    public List<ServiceItem> getServiceItems() {
        return serviceItems;
    }

    public void setServiceItems(List<ServiceItem> serviceItems) {
        this.serviceItems = serviceItems;
    }

    public class ServiceItem{

        @SerializedName("ID")
        private int id;
        @SerializedName("Name")
        private String name;
        @SerializedName("ExtraInfo")
        private String extraInfo;
        @SerializedName("IsDefault")
        private boolean isDefault;

        public ServiceItem(int id, String name, String extraInfo, boolean isDefault) {
            this.id = id;
            this.name = name;
            this.extraInfo = extraInfo;
            this.isDefault = isDefault;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getExtraInfo() {
            return extraInfo;
        }

        public void setExtraInfo(String extraInfo) {
            this.extraInfo = extraInfo;
        }

        public boolean isDefault() {
            return isDefault;
        }

        public void setDefault(boolean aDefault) {
            isDefault = aDefault;
        }
    }
}
