package com.example.vocabit.data.remote;

import android.app.Application;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.vocabit.constant.Constants;
import com.example.vocabit.data.local.prefs.PreferencesService;
import com.example.vocabit.utils.LogService;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;


import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private final PreferencesService appPreferences;
    private final Application application;

    public AuthInterceptor(PreferencesService appPreferences, Application application) {
        this.appPreferences = appPreferences;
        this.application = application;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        Request originalRequest = chain.request();

        String isIgnore = originalRequest.header("IgnoreAuth");
        if (isIgnore != null && isIgnore.equals("1")) {
            Request newRequest = originalRequest.newBuilder()
                    .removeHeader("IgnoreAuth")
                    .addHeader("Content-Type", "application/json")
                    .build();
            return chain.proceed(newRequest);
        }

        Request.Builder newRequestBuilder = originalRequest.newBuilder()
                .addHeader("Content-Type", "application/json");

        String url = originalRequest.url().toString();
        String token;
        if(url.contains("api/token")) {
            token = appPreferences.getBasicAuth();
            newRequestBuilder.addHeader("Authorization", "Basic " + token);
        }else {
            token = appPreferences.getToken();
            if (token != null && !token.isEmpty()) {
                newRequestBuilder.addHeader("Authorization", "Bearer " + token);
            }
        }

        Request newRequest = newRequestBuilder.build();
        Response origResponse = chain.proceed(newRequest);
        if (origResponse.code() == 403 || origResponse.code() == 401) {
            LogService.i("Error http =====================> code: " + origResponse.code());
            appPreferences.removeKey(PreferencesService.KEY_BEARER_TOKEN);
            Intent intent = new Intent();
            intent.setAction(Constants.ACTION_EXPIRED_TOKEN);
            LocalBroadcastManager.getInstance(application.getApplicationContext()).sendBroadcast(intent);
        }
        return origResponse;
    }
}
