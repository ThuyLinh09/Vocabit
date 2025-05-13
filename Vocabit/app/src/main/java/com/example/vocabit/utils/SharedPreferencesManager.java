package com.example.vocabit.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.auth0.android.jwt.JWT;

public class SharedPreferencesManager {

    private static final String PREF_NAME = "vocabit_prefs";
    private static final String KEY_TOKEN = "TOKEN";

    private final SharedPreferences sharedPreferences;

    public SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setToken(String token) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply();
    }

    public String getToken() {
        return sharedPreferences.getString(KEY_TOKEN, null);
    }

    public void clearToken() {
        sharedPreferences.edit().remove(KEY_TOKEN).apply();
    }

    public String getUsernameFromToken() {
        String token = getToken();
        if (token == null) return null;

        try {
            JWT jwt = new JWT(token);
            return jwt.getClaim("username").asString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
