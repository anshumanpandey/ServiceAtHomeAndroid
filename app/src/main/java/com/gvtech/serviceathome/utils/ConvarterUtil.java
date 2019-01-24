package com.gvtech.serviceathome.utils;

import android.content.Context;

import com.gvtech.serviceathome.models.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

public class ConvarterUtil {

    public static UserModel getUserModel(Context context){
        try {
            JSONObject object = new JSONObject(SharedStore.getUserDetails(context));
            UserModel userModel = new UserModel(
                    object.getInt("ID"),
                    object.getString("Password"),
                    object.getString("AccountNumber"),
                    object.getString("FirstName"),
                    object.getString("LastName"),
                    object.getString("Phone"),
                    object.getString("Email"),
                    object.getInt("RoleID"),
                    object.getString("RoleName")
            );

            return userModel;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
