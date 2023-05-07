package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    private static final String LOGIN_PREF = "LoginPref";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    public static void saveLoginInfo(Context context, String username, String password) {
        SharedPreferences preferences = context.getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public static String getUsername(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE);
        return preferences.getString(KEY_USERNAME, null);
    }

    public static String getPassword(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE);
        return preferences.getString(KEY_PASSWORD, null);
    }

    public static void clearLoginInfo(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(KEY_USERNAME);
        editor.remove(KEY_PASSWORD);
        editor.apply();
    }
}
