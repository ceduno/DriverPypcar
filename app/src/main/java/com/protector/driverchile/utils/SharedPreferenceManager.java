package com.protector.driverchile.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import com.protector.driverchile.utils.DataModelJson.DriverPojo;

/**
 * @author Marlon Viana on 27/03/2019
 * @email 92marlonViana@gmail.com
 */
public class SharedPreferenceManager {
    private static final String KEY_USER = "key_user";
    private static final String KEY_LOGIN= "key_login";
    private static final String KEY_FIREBASE= "key_firebase";
    private static final String KEY_SCREEN= "key_screen";
    private static final String KEY_TUTORIAL= "key_tutorial";
    private static final String KEY_THEME= "key_theme";

    public static void setUser(Context context, DriverPojo user){

        SharedPreferences preferences= context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String jsonString = gson.toJson(user);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USER,jsonString);
        editor.commit();
    }

    public static DriverPojo getUser(Context context){

        SharedPreferences preferences= context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);

        String json_user= preferences.getString(KEY_USER, null);
        if(json_user==null)
            return null;
        else {
            Gson gson = new Gson();
            DriverPojo usuario = gson.fromJson(json_user, DriverPojo.class);
            return usuario;
        }
    }

    public static void deletUser(Context context){
        SharedPreferences preferences= context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().commit();
    }

    public static void setLogin(Context context, boolean ban){
        SharedPreferences preferences= context.getSharedPreferences(KEY_LOGIN, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_LOGIN,ban);
        editor.commit();
    }

    public static boolean getLogin(Context context){
        SharedPreferences preferences= context.getSharedPreferences(KEY_LOGIN, Context.MODE_PRIVATE);
        return  preferences.getBoolean(KEY_LOGIN,false);
    }

    public static void setTokenFirebase(Context context, String token){
        SharedPreferences preferences= context.getSharedPreferences(KEY_FIREBASE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_FIREBASE,token);
        editor.commit();
    }

    public static String getTokenFirebase(Context context){
        SharedPreferences preferences= context.getSharedPreferences(KEY_FIREBASE, Context.MODE_PRIVATE);
        return  preferences.getString(KEY_FIREBASE,"");
    }


    public static void setScreen(Context context, boolean ban){
        SharedPreferences preferences= context.getSharedPreferences(KEY_SCREEN, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_SCREEN,ban);
        editor.commit();
    }

    public static void setTheme(Context context, boolean ban){
        SharedPreferences preferences= context.getSharedPreferences(KEY_THEME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_THEME,ban);
        editor.commit();
    }
    public static boolean getTheme(Context context){
        SharedPreferences preferences= context.getSharedPreferences(KEY_THEME, Context.MODE_PRIVATE);
        return  preferences.getBoolean(KEY_THEME,false);
    }
    public static boolean getScreen(Context context){
        SharedPreferences preferences= context.getSharedPreferences(KEY_SCREEN, Context.MODE_PRIVATE);
        return  preferences.getBoolean(KEY_SCREEN,true);
    }

    public static void setTutorial(Context context, boolean ban){
        SharedPreferences preferences= context.getSharedPreferences(KEY_TUTORIAL, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_TUTORIAL,ban);
        editor.commit();
    }

    public static boolean getTutorial(Context context){
        SharedPreferences preferences= context.getSharedPreferences(KEY_TUTORIAL, Context.MODE_PRIVATE);
        return  preferences.getBoolean(KEY_TUTORIAL,true);
    }

}
