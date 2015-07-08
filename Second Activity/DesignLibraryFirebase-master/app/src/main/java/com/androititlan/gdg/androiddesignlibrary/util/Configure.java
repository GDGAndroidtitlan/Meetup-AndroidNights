package com.androititlan.gdg.androiddesignlibrary.util;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Jhordan on 05/07/15.
 */
public class Configure {

    // Configuración de tu TWITTER_KEY Y TWITTER_SECRET que hacen posible el uso de la API twitter.


    private static final String TWITTER_KEY = "Ft9qGv4ow1ApODff7R9ERMZrL";
    private static final String TWITTER_SECRET = "LPfDdqpiMtcGmb5KftTXv7R9nA6yyEtP45M0uOsNgKTAnWeEbx";

    public static void getTwitterAuthConfig(Context context){
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, Configure.TWITTER_SECRET);
        Fabric.with(context, new Twitter(authConfig));
    }

    public static String getTwitterKey() {
        return TWITTER_KEY;
    }

    public static String getTwitterSecret() {
        return TWITTER_SECRET;
    }


    // Twitter Profile

    public static final String AVATAR_PROFILE = "avatar_profile";
    public static final String AVATAR_PHOTO_PROFILE = "avatar_photo";
    public static final String AVATAR_BACKGROUND_PHOTO_PROFILE = "avatar_background";
    public static final String AVATAR_NAME_PROFILE = "avatar_name";
    public static final String AVATAR_NICK_NAME_PROFILE = "avatar_nick_name";

    // Contruimos un bundle para pasar los datos del perfil medianten un Intent

    /**
     *
     * @param name nombre de usuario
     * @param nick nick name de usuario
     * @param photo foto del perfil
     * @param background fondo del perfil
     * @return regresa un bundle compuesto por nuestras variables
     */
    public static Bundle getProfile(String name,String nick,String photo, String background){
        Bundle bundle = new Bundle();
        bundle.putString(Configure.AVATAR_NAME_PROFILE,name);
        bundle.putString(Configure.AVATAR_NICK_NAME_PROFILE,nick);
        bundle.putString(Configure.AVATAR_PHOTO_PROFILE,photo);
        bundle.putString(Configure.AVATAR_BACKGROUND_PHOTO_PROFILE,background);
        return  bundle;
    }



    // Otros

    public static String  JPG_EXTENSION = ".jpg";
    public static String  JPEG_EXTENSION = ".jpeg";
    public static String  SUFIX = "_normal.jpg";
    public static String  SUFIXE = "_normal.jpeg";

    public static void setStatusBarColor(Window window, int primaryDark){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.setStatusBarColor(primaryDark);
    }



    // firebase


    public static final String FIREBASE_URL = "https://chat-androidnight.firebaseio.com/";
    public static final String FIREBASE_CHILD = "chat";

}
