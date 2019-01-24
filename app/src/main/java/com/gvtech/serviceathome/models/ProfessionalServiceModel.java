package com.gvtech.serviceathome.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfessionalServiceModel {

    @SerializedName("ResultCode")
    private String resultCode;
    @SerializedName("ResultMessage")
    private String resultMessage;
    @SerializedName("ResultObject")
    private List<Professional> professionals;

    public ProfessionalServiceModel(String resultCode, String resultMessage, List<Professional> professional) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.professionals = professional;
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

    public List<Professional> getProfessionals() {
        return professionals;
    }

    public void setProfessionals(List<Professional> professionals) {
        this.professionals = professionals;
    }



    public class Professional{
        @SerializedName("ID")
        private int ID;
        @SerializedName("Email")
        private String email;
        @SerializedName("ProfessionalID")
        private int professionalID;
        @SerializedName("BusinessName")
        private String businessName;
        @SerializedName("ProfileImage")
        private String profileImage;
        @SerializedName("CoverImage")
        private String coverImage;
        @SerializedName("WhatYouAre")
        private String whatYouAre;
        @SerializedName("Rating")
        private int rating;
        @SerializedName("RatingCount")
        private int ratingCount;

        public Professional(int ID, String email, int professionalID, String businessName, String profileImage, String coverImage, String whatYouAre, int rating, int ratingCount) {
            this.ID = ID;
            this.email = email;
            this.professionalID = professionalID;
            this.businessName = businessName;
            this.profileImage = profileImage;
            this.coverImage = coverImage;
            this.whatYouAre = whatYouAre;
            this.rating = rating;
            this.ratingCount = ratingCount;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getProfessionalID() {
            return professionalID;
        }

        public void setProfessionalID(int professionalID) {
            this.professionalID = professionalID;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public String getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
        }

        public String getCoverImage() {
            return coverImage;
        }

        public void setCoverImage(String coverImage) {
            this.coverImage = coverImage;
        }

        public String getWhatYouAre() {
            return whatYouAre;
        }

        public void setWhatYouAre(String whatYouAre) {
            this.whatYouAre = whatYouAre;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public int getRatingCount() {
            return ratingCount;
        }

        public void setRatingCount(int ratingCount) {
            this.ratingCount = ratingCount;
        }
    }
}
