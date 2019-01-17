package com.gvtech.serviceathome.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerHomeModel {

    @SerializedName("ResultCode")
    private String resultCode;
    @SerializedName("ResultMessage")
    private String resultMessage;
    @SerializedName("ResultObject")
    private ResultObject resultObject;

    public CustomerHomeModel(String resultCode, String resultMessage, ResultObject resultObject) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.resultObject = resultObject;
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

    public ResultObject getResultObject() {
        return resultObject;
    }

    public void setResultObject(ResultObject resultObject) {
        this.resultObject = resultObject;
    }



    public class ResultObject{

        @SerializedName("Categories")
        private List<Categorie> categories;
        @SerializedName("HomeSliders")
        private List<Sliders> homeSliders;

        public ResultObject(List<Categorie> categories, List<Sliders> homeSliders) {
            this.categories = categories;
            this.homeSliders = homeSliders;
        }

        public List<Categorie> getCategories() {
            return categories;
        }

        public void setCategories(List<Categorie> categories) {
            this.categories = categories;
        }

        public List<Sliders> getHomeSliders() {
            return homeSliders;
        }

        public void setHomeSliders(List<Sliders> homeSliders) {
            this.homeSliders = homeSliders;
        }
    }

    public class Categorie{

        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("pagename")
        private String pagename;
        @SerializedName("imageUrl")
        private String imageUrl;
        @SerializedName("mobilebannerurl")
        private String mobilebannerurl;

        public Categorie(int id, String name, String pagename, String imageUrl, String mobilebannerurl) {
            this.id = id;
            this.name = name;
            this.pagename = pagename;
            this.imageUrl = imageUrl;
            this.mobilebannerurl = mobilebannerurl;
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

        public String getPagename() {
            return pagename;
        }

        public void setPagename(String pagename) {
            this.pagename = pagename;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getMobilebannerurl() {
            return mobilebannerurl;
        }

        public void setMobilebannerurl(String mobilebannerurl) {
            this.mobilebannerurl = mobilebannerurl;
        }
    }

    public class Sliders{

        @SerializedName("ID")
        private int id;
        @SerializedName("Name")
        private String name;
        @SerializedName("FileName")
        private String fileName;
        @SerializedName("OverlayText")
        private String overlayText;
        @SerializedName("IsActive")
        private boolean isActive;
        @SerializedName("SortOrder")
        private int sortOrder;
        @SerializedName("FileType")
        private int fileType;
        @SerializedName("CreatedDate")
        private String createdDate;
        @SerializedName("CreatedBy")
        private String createdBy;
        @SerializedName("UpdatedDate")
        private String updatedDate;
        @SerializedName("UpdatedBy")
        private String updatedBy;

        public Sliders(int id, String name, String fileName, String overlayText, boolean isActive, int sortOrder, int fileType, String createdDate, String createdBy, String updatedDate, String updatedBy) {
            this.id = id;
            this.name = name;
            this.fileName = fileName;
            this.overlayText = overlayText;
            this.isActive = isActive;
            this.sortOrder = sortOrder;
            this.fileType = fileType;
            this.createdDate = createdDate;
            this.createdBy = createdBy;
            this.updatedDate = updatedDate;
            this.updatedBy = updatedBy;
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

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getOverlayText() {
            return overlayText;
        }

        public void setOverlayText(String overlayText) {
            this.overlayText = overlayText;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }

        public int getSortOrder() {
            return sortOrder;
        }

        public void setSortOrder(int sortOrder) {
            this.sortOrder = sortOrder;
        }

        public int getFileType() {
            return fileType;
        }

        public void setFileType(int fileType) {
            this.fileType = fileType;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getUpdatedDate() {
            return updatedDate;
        }

        public void setUpdatedDate(String updatedDate) {
            this.updatedDate = updatedDate;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }
    }

}
