package com.clickandeat.finalproject5.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class locationHelper {
    private final String APP_NAME="ClickToEat";
    private final String LOCATION="location";
    public final String DALIA="dalia";
    public final String YARKA="yarka";
    private static locationHelper instance=null;

    private locationHelper(){}

    public static locationHelper getInstance(){
        if (instance == null)
            instance = new locationHelper();
        return instance;
    }
    public void setLocationToDalia(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LOCATION, DALIA);
        editor.commit();
    }
    public void setLocationToYarka(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LOCATION, YARKA);
        editor.commit();
    }
    public String getLocation(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(LOCATION, null);
    }
}
