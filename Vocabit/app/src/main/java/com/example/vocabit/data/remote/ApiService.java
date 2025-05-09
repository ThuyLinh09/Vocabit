package com.example.vocabit.data.remote;

import com.example.vocabit.data.model.api.request.login.LoginRequest;
import com.example.vocabit.data.model.api.request.login.ReLoginRequest;
import com.example.vocabit.data.model.api.request.register.RegisterRequest;
import com.example.vocabit.data.model.api.response.ResponseWrapper;
import com.example.vocabit.data.model.api.response.login.LoginResponse;
import com.example.vocabit.data.model.api.response.login.ReLoginResponse;
import com.example.vocabit.data.model.api.response.register.RegisterResponse;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {
    //Login
    @POST("auth/log-in")
    Observable<LoginResponse> login(@Body LoginRequest request);


    @POST("auth/log-in")
    Observable<ReLoginResponse> reLogin(@Body ReLoginRequest request);

    @POST("users")
    Observable<RegisterResponse> register(@Body RegisterRequest request);

}
