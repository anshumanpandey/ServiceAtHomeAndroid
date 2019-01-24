package com.gvtech.serviceathome.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationAddress {
    private static final String TAG = "LocationAddress";

    public static void getAddressFromLocation(final double latitude, final double longitude,
                                              final Context context, final Handler handler) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                JSONObject object = new JSONObject();
                try {
                    List<Address> addressList = geocoder.getFromLocation(
                            latitude, longitude, 1);
                    if (addressList != null && addressList.size() > 0) {
                        Address address = addressList.get(0);
                        try {
//                            int n = address.getMaxAddressLineIndex();
//                            for (int i = 0; i < n; i++) {
//                                    object.put("address"+i, address.getAddressLine(i));
//                            }
                            object.put("locality", address.getLocality());
                            object.put("admin", address.getAdminArea());
                            object.put("postcode", address.getPostalCode());
                            object.put("countryName", address.getCountryName());
                            object.put("address", address.getAddressLine(0));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                } catch (IOException e) {
                    Log.e(TAG, "Unable connect to Geocoder", e);
                } finally {
                    Message message = Message.obtain();
                    message.setTarget(handler);
                    if (object != null) {
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        try {
                            object.put("Latitude",latitude);
                            object.put("Longitude",longitude);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        bundle.putString("addressObj", object.toString());
                        message.setData(bundle);
                    } else {
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        try {
                            object.put("Latitude",latitude);
                            object.put("Longitude",longitude);
                            object.put("msg","Unable to get address for this lat-long.");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        bundle.putString("addressObj", object.toString());
                        message.setData(bundle);
                    }
                    message.sendToTarget();
                }
            }
        };
        thread.start();
    }
}