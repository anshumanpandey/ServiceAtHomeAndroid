package com.gvtech.serviceathome.models.postModel;

import com.google.gson.annotations.SerializedName;

public class UserLoginResponseModel {

    @SerializedName("ResultCode")
    private String ResultCode;
    @SerializedName("ResultMessage")
    private String resultMessage;
    @SerializedName("ResultObject")
    private ResultObject ResultObject;

    public UserLoginResponseModel(String resultCode,String resultMessage, UserLoginResponseModel.ResultObject resultObject) {
        ResultCode = resultCode;
        ResultObject = resultObject;
        this.resultMessage = resultMessage;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getResultCode() {
        return ResultCode;
    }

    public void setResultCode(String resultCode) {
        ResultCode = resultCode;
    }

    public UserLoginResponseModel.ResultObject getResultObject() {
        return ResultObject;
    }

    public void setResultObject(UserLoginResponseModel.ResultObject resultObject) {
        ResultObject = resultObject;
    }

    public class ResultObject{
        @SerializedName("User")
        private User user;

        public ResultObject(User user) {
            this.user = user;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }

    public class User {

        @SerializedName("ID")
        private int ID;
        @SerializedName("Email")
        private String email;
        @SerializedName("AccountNumber")
        private String accountNumber;
        @SerializedName("TaxExempt")
        private String taxExempt;
        @SerializedName("PriceGroupID")
        private int priceGroupID;
        @SerializedName("IsActive")
        private boolean isActive;
        @SerializedName("CreatedDate")
        private String createdDate;
        @SerializedName("CreatedBy")
        private String createdBy;
        @SerializedName("UpdatedDate")
        private String updatedDate;
        @SerializedName("UpdatedBy")
        private String updatedBy;
        @SerializedName("ResetPasswordKey")
        private String resetPasswordKey;
        @SerializedName("ResetKeyExpiration")
        private String resetKeyExpiration;
        @SerializedName("FirstName")
        private String firstName;
        @SerializedName("LastName")
        private String lastName;
        @SerializedName("Phone")
        private String phone;
        @SerializedName("TotalRecords")
        private int totalRecords;
        @SerializedName("FullName")
        private String fullName;
        @SerializedName("ProfileImage")
        private String profileImage;
        @SerializedName("Services")
        private String services;
        @SerializedName("UserGmailID")
        private String userGmailID;
        @SerializedName("StripeCustomerID")
        private String stripeCustomerID;
        @SerializedName("RoleID")
        private int roleID;
        @SerializedName("RoleName")
        private String roleName;

        public User(int ID, String email, String accountNumber, String taxExempt, int priceGroupID, boolean isActive, String createdDate, String createdBy, String updatedDate, String updatedBy, String resetPasswordKey, String resetKeyExpiration, String firstName, String lastName, String phone, int totalRecords, String fullName, String profileImage, String services, String userGmailID, String stripeCustomerID, int roleID, String roleName) {
            this.ID = ID;
            this.email = email;
            this.accountNumber = accountNumber;
            this.taxExempt = taxExempt;
            this.priceGroupID = priceGroupID;
            this.isActive = isActive;
            this.createdDate = createdDate;
            this.createdBy = createdBy;
            this.updatedDate = updatedDate;
            this.updatedBy = updatedBy;
            this.resetPasswordKey = resetPasswordKey;
            this.resetKeyExpiration = resetKeyExpiration;
            this.firstName = firstName;
            this.lastName = lastName;
            this.phone = phone;
            this.totalRecords = totalRecords;
            this.fullName = fullName;
            this.profileImage = profileImage;
            this.services = services;
            this.userGmailID = userGmailID;
            this.stripeCustomerID = stripeCustomerID;
            this.roleID = roleID;
            this.roleName = roleName;
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

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getTaxExempt() {
            return taxExempt;
        }

        public void setTaxExempt(String taxExempt) {
            this.taxExempt = taxExempt;
        }

        public int getPriceGroupID() {
            return priceGroupID;
        }

        public void setPriceGroupID(int priceGroupID) {
            this.priceGroupID = priceGroupID;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
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

        public String getResetPasswordKey() {
            return resetPasswordKey;
        }

        public void setResetPasswordKey(String resetPasswordKey) {
            this.resetPasswordKey = resetPasswordKey;
        }

        public String getResetKeyExpiration() {
            return resetKeyExpiration;
        }

        public void setResetKeyExpiration(String resetKeyExpiration) {
            this.resetKeyExpiration = resetKeyExpiration;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getTotalRecords() {
            return totalRecords;
        }

        public void setTotalRecords(int totalRecords) {
            this.totalRecords = totalRecords;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
        }

        public String getServices() {
            return services;
        }

        public void setServices(String services) {
            this.services = services;
        }

        public String getUserGmailID() {
            return userGmailID;
        }

        public void setUserGmailID(String userGmailID) {
            this.userGmailID = userGmailID;
        }

        public String getStripeCustomerID() {
            return stripeCustomerID;
        }

        public void setStripeCustomerID(String stripeCustomerID) {
            this.stripeCustomerID = stripeCustomerID;
        }

        public int getRoleID() {
            return roleID;
        }

        public void setRoleID(int roleID) {
            this.roleID = roleID;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }
    }

}
