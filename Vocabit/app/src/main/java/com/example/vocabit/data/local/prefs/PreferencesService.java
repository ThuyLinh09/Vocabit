package com.example.vocabit.data.local.prefs;

import android.content.SharedPreferences;

public interface PreferencesService {
    public static final String KEY_BEARER_TOKEN="KEY_BEARER_TOKEN";
    public static final String KEY_BASIC_AUTH="KEY_BASIC_AUTH";

    String getBasicAuth();
    String getToken();
    void setToken(String token);

    void removeKey(String key);
    void removeAllKeys();
    boolean containKey(String key);
    void registerChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener);
    void unregisterChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener);

    void setBoolean(String key, boolean val);
    boolean getBooleanVal(String key);

    void setString(String key, String val);
    String getStringVal(String key);

    void setInt(String key, int val);
    int getIntVal(String key);

    void setLong(String key, long val);
    long getLongVal(String key);

    void setFloat(String key, float val);
    float getFloatVal(String key);

    <T> T getObjectVal(String key, Class<T> mModelClass);

    void setRole(Long userRole);
    Long getRole();
    void setUserId(Long userId);
    Long getUserId();

}
