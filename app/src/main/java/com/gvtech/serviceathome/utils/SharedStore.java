package com.gvtech.serviceathome.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedStore {

    private static String MyPREFERENCES = "SharedStore";

    public static int getCurrentTheme(Context context){
        SharedPreferences preferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return preferences.getInt("currentTheme", 0); // return white theme if theme not found
    }
    public static void setCurrentTheme(Context context,int theme){
        SharedPreferences preferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("currentTheme",theme);
        editor.apply();
    }

    public static String getUserDetails(Context context){
        SharedPreferences preferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return preferences.getString("userDetails", null); // return white theme if theme not found
    }
    public static void setUserDetails(Context context,String user){
        SharedPreferences preferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userDetails",user);
        editor.apply();
    }

    public static int getOpenState(Context context){
        SharedPreferences preferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return preferences.getInt("openState", Constants.OPEN_THEME); // return white theme if theme not found
    }
    public static void setOpenState(Context context,int screen){
        SharedPreferences preferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("openState",screen);
        editor.apply();
    }

}
