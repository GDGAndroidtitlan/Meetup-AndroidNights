package com.androititlan.gdg.androiddesignlibrary.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jhordan on 07/07/15.
 */
public class ConfigPreferences {

    private  static final String PREFERENCE_PROFILE = "img_url";
    private  static final String PREFERENCE_PHOTO = "photo_url";
    private  static final String PREFERENCE_ID = "perfil_id";
    private  static final String PREFERENCE_USER_ID = "user_id";

    public static void setUrl(Context context ,String urlPhoto){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_PROFILE, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(PREFERENCE_PHOTO, urlPhoto).apply();
    }

    public static String getUrl(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_PROFILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PREFERENCE_PHOTO,PREFERENCE_PHOTO);
    }


    public static void setId(Context context ,Long id){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_ID, Context.MODE_PRIVATE);
        sharedPreferences.edit().putLong(PREFERENCE_USER_ID, id).apply();
    }

    public static Long getPreferenceId(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_ID, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(PREFERENCE_USER_ID,111111L);
    }

}
