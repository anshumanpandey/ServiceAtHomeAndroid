package com.gvtech.serviceathome.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfessionalDetailsModel {

    @SerializedName("ResultCode")
    private String resultCode;
    @SerializedName("ResultMessage")
    private String resultMessage;
    @SerializedName("ResultObject")
    private ResultObject resultObject;

    public ProfessionalDetailsModel(String resultCode, String resultMessage, ResultObject resultObject) {
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

        @SerializedName("ProfessionalDetail")
        private ProfessionalDetail professionalDetail;
        @SerializedName("Postcodes")
        private List<Postcodes> postcodes;
        @SerializedName("Services")
        private List<Services> services;
        @SerializedName("Availability")
        private List<Availability> availability;
        @SerializedName("Speciality")
        private List<Speciality> speciality;
        @SerializedName("Account")
        private Account account;
        @SerializedName("Gallery")
        private List<Gallery> gallery;
        @SerializedName("Review")
        private List<Review> review;


        public ResultObject(ProfessionalDetail professionalDetail, List<Postcodes> postcodes, List<Services> services, List<Availability> availability, List<Speciality> speciality, Account account, List<Gallery> gallery, List<Review> review) {
            this.professionalDetail = professionalDetail;
            this.postcodes = postcodes;
            this.services = services;
            this.availability = availability;
            this.speciality = speciality;
            this.account = account;
            this.gallery = gallery;
            this.review = review;
        }

        public ProfessionalDetail getProfessionalDetail() {
            return professionalDetail;
        }

        public void setProfessionalDetail(ProfessionalDetail professionalDetail) {
            this.professionalDetail = professionalDetail;
        }

        public List<Postcodes> getPostcodes() {
            return postcodes;
        }

        public void setPostcodes(List<Postcodes> postcodes) {
            this.postcodes = postcodes;
        }

        public List<Services> getServices() {
            return services;
        }

        public void setServices(List<Services> services) {
            this.services = services;
        }

        public List<Availability> getAvailability() {
            return availability;
        }

        public void setAvailability(List<Availability> availability) {
            this.availability = availability;
        }

        public List<Speciality> getSpeciality() {
            return speciality;
        }

        public void setSpeciality(List<Speciality> speciality) {
            this.speciality = speciality;
        }

        public Account getAccount() {
            return account;
        }

        public void setAccount(Account account) {
            this.account = account;
        }

        public List<Gallery> getGallery() {
            return gallery;
        }

        public void setGallery(List<Gallery> gallery) {
            this.gallery = gallery;
        }

        public List<Review> getReview() {
            return review;
        }

        public void setReview(List<Review> review) {
            this.review = review;
        }
    }

    public class ProfessionalDetail{

        @SerializedName("ID")
        private int id;
        @SerializedName("Email")
        private String email;
        @SerializedName("BusinessName")
        private String businessName;
        @SerializedName("FirstName")
        private String firstName;
        @SerializedName("LastName")
        private String lastName;
        @SerializedName("Phone")
        private String phone;
        @SerializedName("Address1")
        private String address1;
        @SerializedName("Address2")
        private String address2;
        @SerializedName("Address3")
        private String address3;
        @SerializedName("City")
        private String city;
        @SerializedName("ProfileImage")
        private String profileImage;
        @SerializedName("CoverImage")
        private String coverImage;
        @SerializedName("WhatYouAre")
        private String whatYouAre;
        @SerializedName("ServiceInformation")
        private String serviceInformation;
        @SerializedName("Rating")
        private float rating;
        @SerializedName("RatingCount")
        private int ratingCount;

        public ProfessionalDetail(int id, String email, String businessName, String firstName, String lastName, String phone, String address1, String address2, String address3, String city, String profileImage, String coverImage, String whatYouAre, String serviceInformation, float rating, int ratingCount) {
            this.id = id;
            this.email = email;
            this.businessName = businessName;
            this.firstName = firstName;
            this.lastName = lastName;
            this.phone = phone;
            this.address1 = address1;
            this.address2 = address2;
            this.address3 = address3;
            this.city = city;
            this.profileImage = profileImage;
            this.coverImage = coverImage;
            this.whatYouAre = whatYouAre;
            this.serviceInformation = serviceInformation;
            this.rating = rating;
            this.ratingCount = ratingCount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
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

        public String getServiceInformation() {
            return serviceInformation;
        }

        public void setServiceInformation(String serviceInformation) {
            this.serviceInformation = serviceInformation;
        }

        public float getRating() {
            return rating;
        }

        public void setRating(float rating) {
            this.rating = rating;
        }

        public int getRatingCount() {
            return ratingCount;
        }

        public void setRatingCount(int ratingCount) {
            this.ratingCount = ratingCount;
        }
    }

    public class Postcodes{
        @SerializedName("ID")
        private int id;
        @SerializedName("ProfessionalID")
        private int professionalID;
        @SerializedName("Postcode")
        private String postcode;
        @SerializedName("Latitude")
        private double latitude;
        @SerializedName("Longitude")
        private double longitude;

        public Postcodes(int id, int professionalID, String postcode, double latitude, double longitude) {
            this.id = id;
            this.professionalID = professionalID;
            this.postcode = postcode;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProfessionalID() {
            return professionalID;
        }

        public void setProfessionalID(int professionalID) {
            this.professionalID = professionalID;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }

    public class Services{

        @SerializedName("ID")
        private int id;
        @SerializedName("ProfessionalID")
        private int professionalID;
        @SerializedName("ServiceID")
        private int serviceID;
        @SerializedName("ServiceName")
        private String serviceName;
        @SerializedName("Duration")
        private int duration;
        @SerializedName("Price")
        private float price;

        public Services(int id, int professionalID, int serviceID, String serviceName, int duration, float price) {
            this.id = id;
            this.professionalID = professionalID;
            this.serviceID = serviceID;
            this.serviceName = serviceName;
            this.duration = duration;
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProfessionalID() {
            return professionalID;
        }

        public void setProfessionalID(int professionalID) {
            this.professionalID = professionalID;
        }

        public int getServiceID() {
            return serviceID;
        }

        public void setServiceID(int serviceID) {
            this.serviceID = serviceID;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }
    }

    public class Availability{

        @SerializedName("ProfessionalID")
        private int professionalID;
        @SerializedName("Day")
        private String day;
        @SerializedName("StartTime")
        private String startTime;
        @SerializedName("EndTime")
        private String endTime;
        @SerializedName("IsAvailable")
        private boolean isAvailable;
        @SerializedName("AvailabilityStartTimeString")
        private String availabilityStartTimeString;
        @SerializedName("AvailabilityEndTimeString")
        private String availabilityEndTimeString;

        public Availability(int professionalID, String day, String startTime, String endTime, boolean isAvailable, String availabilityStartTimeString, String availabilityEndTimeString) {
            this.professionalID = professionalID;
            this.day = day;
            this.startTime = startTime;
            this.endTime = endTime;
            this.isAvailable = isAvailable;
            this.availabilityStartTimeString = availabilityStartTimeString;
            this.availabilityEndTimeString = availabilityEndTimeString;
        }

        public int getProfessionalID() {
            return professionalID;
        }

        public void setProfessionalID(int professionalID) {
            this.professionalID = professionalID;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void setAvailable(boolean available) {
            isAvailable = available;
        }

        public String getAvailabilityStartTimeString() {
            return availabilityStartTimeString;
        }

        public void setAvailabilityStartTimeString(String availabilityStartTimeString) {
            this.availabilityStartTimeString = availabilityStartTimeString;
        }

        public String getAvailabilityEndTimeString() {
            return availabilityEndTimeString;
        }

        public void setAvailabilityEndTimeString(String availabilityEndTimeString) {
            this.availabilityEndTimeString = availabilityEndTimeString;
        }
    }

    public class Speciality{
        @SerializedName("ID")
        private int id;
        @SerializedName("ProfessionalID")
        private int professionalID;
        @SerializedName("SpecialityID")
        private int specialityID;
        @SerializedName("Speciality")
        private String speciality;

        public Speciality(int id, int professionalID, int specialityID, String speciality) {
            this.id = id;
            this.professionalID = professionalID;
            this.specialityID = specialityID;
            this.speciality = speciality;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProfessionalID() {
            return professionalID;
        }

        public void setProfessionalID(int professionalID) {
            this.professionalID = professionalID;
        }

        public int getSpecialityID() {
            return specialityID;
        }

        public void setSpecialityID(int specialityID) {
            this.specialityID = specialityID;
        }

        public String getSpeciality() {
            return speciality;
        }

        public void setSpeciality(String speciality) {
            this.speciality = speciality;
        }
    }

    public class Account{
        @SerializedName("UserID")
        private int userID;
        @SerializedName("AccountName")
        private String accountName;
        @SerializedName("SortCode")
        private String sortCode;
        @SerializedName("AccountNumber")
        private int AccountNumber;
        @SerializedName("PaypalEmail")
        private String paypalEmail;

        public Account(int userID, String accountName, String sortCode, int accountNumber, String paypalEmail) {
            this.userID = userID;
            this.accountName = accountName;
            this.sortCode = sortCode;
            AccountNumber = accountNumber;
            this.paypalEmail = paypalEmail;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getSortCode() {
            return sortCode;
        }

        public void setSortCode(String sortCode) {
            this.sortCode = sortCode;
        }

        public int getAccountNumber() {
            return AccountNumber;
        }

        public void setAccountNumber(int accountNumber) {
            AccountNumber = accountNumber;
        }

        public String getPaypalEmail() {
            return paypalEmail;
        }

        public void setPaypalEmail(String paypalEmail) {
            this.paypalEmail = paypalEmail;
        }
    }

    public class Gallery{

        @SerializedName("ID")
        private int id;
        @SerializedName("ProfessionalID")
        private int professionalID;
        @SerializedName("FileName")
        private String fileName;

        public Gallery(int id, int professionalID, String fileName) {
            this.id = id;
            this.professionalID = professionalID;
            this.fileName = fileName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProfessionalID() {
            return professionalID;
        }

        public void setProfessionalID(int professionalID) {
            this.professionalID = professionalID;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
    }

    public class Review{

        @SerializedName("ID")
        private int id;
        @SerializedName("ProfessionalID")
        private int professionalID;
        @SerializedName("CustomerID")
        private int customerID;
        @SerializedName("Comment")
        private String comment;
        @SerializedName("Rating")
        private float rating;
        @SerializedName("IsVisible")
        private boolean isVisible;
        @SerializedName("CustomerFirstName")
        private String CustomerFirstName;
        @SerializedName("CustomerLastName")
        private String customerLastName;
        @SerializedName("CustomerImage")
        private String customerImage;
        @SerializedName("ReviewDate")
        private String reviewDate;

        public Review(int id, int professionalID, int customerID, String comment, float rating, boolean isVisible, String customerFirstName, String customerLastName, String customerImage, String reviewDate) {
            this.id = id;
            this.professionalID = professionalID;
            this.customerID = customerID;
            this.comment = comment;
            this.rating = rating;
            this.isVisible = isVisible;
            CustomerFirstName = customerFirstName;
            this.customerLastName = customerLastName;
            this.customerImage = customerImage;
            this.reviewDate = reviewDate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProfessionalID() {
            return professionalID;
        }

        public void setProfessionalID(int professionalID) {
            this.professionalID = professionalID;
        }

        public int getCustomerID() {
            return customerID;
        }

        public void setCustomerID(int customerID) {
            this.customerID = customerID;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public float getRating() {
            return rating;
        }

        public void setRating(float rating) {
            this.rating = rating;
        }

        public boolean isVisible() {
            return isVisible;
        }

        public void setVisible(boolean visible) {
            isVisible = visible;
        }

        public String getCustomerFirstName() {
            return CustomerFirstName;
        }

        public void setCustomerFirstName(String customerFirstName) {
            CustomerFirstName = customerFirstName;
        }

        public String getCustomerLastName() {
            return customerLastName;
        }

        public void setCustomerLastName(String customerLastName) {
            this.customerLastName = customerLastName;
        }

        public String getCustomerImage() {
            return customerImage;
        }

        public void setCustomerImage(String customerImage) {
            this.customerImage = customerImage;
        }

        public String getReviewDate() {
            return reviewDate;
        }

        public void setReviewDate(String reviewDate) {
            this.reviewDate = reviewDate;
        }
    }


}
