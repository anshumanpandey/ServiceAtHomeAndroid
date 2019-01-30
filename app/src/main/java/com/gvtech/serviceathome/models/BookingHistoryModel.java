package com.gvtech.serviceathome.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingHistoryModel {

    @SerializedName("ResultCode")
    private String resultCode;
    @SerializedName("ResultMessage")
    private String resultMessage;
    @SerializedName("ResultObject")
    private List<BookingItem> bookings;


    public class BookingItem{

        @SerializedName("ID")
        private int id;
        @SerializedName("UserID")
        private int userID;
        @SerializedName("GrandTotal")
        private float grandTotal;
        @SerializedName("HandTotal")
        private float handTotal;
        @SerializedName("Instructions")
        private String instructions;
        @SerializedName("Discount")
        private float discount;
        @SerializedName("OrderNumber")
        private int orderNumber;
        @SerializedName("PaymentStatusID")
        private int paymentStatusID;
        @SerializedName("ShippingMethodID")
        private int shippingMethodID;
        @SerializedName("ShippingProviderID")
        private int shippingProviderID;
        @SerializedName("ShippingProviderServiceCode")
        private int shippingProviderServiceCode;
        @SerializedName("ShippingStatusID")
        private int shippingStatusID;
        @SerializedName("ShippingTotal")
        private float ShippingTotal;
        @SerializedName("ShippingDiscounts")
        private float shippingDiscounts;
        @SerializedName("SubTotal")
        private float subTotal;
        @SerializedName("TaxTotal")
        private float taxTotal;
        @SerializedName("TaxTotal2")
        private float taxTotal2;
        @SerializedName("TotalOrderBeforeDiscount")
        private float totalOrderBeforeDiscount;
        @SerializedName("StatusID")
        private int statusID;
        @SerializedName("CreatedDate")
        private String createdDate;
    }

}
