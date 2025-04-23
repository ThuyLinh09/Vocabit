package com.example.vocabit.data;


import com.example.vocabit.data.local.prefs.PreferencesService;
import com.example.vocabit.data.remote.ApiService;

public interface Repository {

    /**
     * ################################## Preference section ##################################
     */
    String getToken();
    void setToken(String token);


    PreferencesService getSharedPreferences();


    /**
     *  ################################## Remote api ##################################
     */
    ApiService getApiService();

}
