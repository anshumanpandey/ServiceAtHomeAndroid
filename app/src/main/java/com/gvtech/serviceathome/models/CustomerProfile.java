package com.gvtech.serviceathome.models;

import com.google.gson.annotations.SerializedName;

public class CustomerProfile {

    @SerializedName("ResultCode")
    private String resultCode;
    @SerializedName("ResultMessage")
    private String resultMessage;
    @SerializedName("ResultObject")
    private ResultObject resultObject;

    public CustomerProfile(String resultCode, String resultMessage, ResultObject resultObject) {
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

        @SerializedName("UserID")
        private int userID;
        @SerializedName("FirstName")
        private String firstName;
        @SerializedName("LastName")
        private String lastName;
        @SerializedName("PostCode")
        private String postCode;
        @SerializedName("Email")
        private String email;
        @SerializedName("Address1")
        private String address1;
        @SerializedName("Address2")
        private String address2;
        @SerializedName("Address3")
        private String address3;
        @SerializedName("City")
        private String city;
        @SerializedName("Phone")
        private String phone;
        @SerializedName("ProfileImage")
        private String profileImage;
        @SerializedName("CreatedDate")
        private String createdDate;

        public ResultObject(int userID, String firstName, String lastName, String postCode, String email, String address1, String address2, String address3, String city, String phone, String profileImage, String createdDate) {
            this.userID = userID;
            this.firstName = firstName;
            this.lastName = lastName;
            this.postCode = postCode;
            this.email = email;
            this.address1 = address1;
            this.address2 = address2;
            this.address3 = address3;
            this.city = city;
            this.phone = phone;
            this.profileImage = profileImage;
            this.createdDate = createdDate;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getPostCode() {
            return postCode;
        }

        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public String getAddress2() {
            return address2;
        }

        public void setAddress2(String address2) {
            this.address2 = address2;
        }

        public String getAddress3() {
            return address3;
        }

        public void setAddress3(String address3) {
            this.address3 = address3;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }
    }
}
