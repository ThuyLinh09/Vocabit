package com.example.vocabit.data.remote;

import com.example.vocabit.data.model.api.request.changepassword.ChangePasswordRequest;
import com.example.vocabit.data.model.api.request.login.LoginRequest;
import com.example.vocabit.data.model.api.request.register.RegisterRequest;
import com.example.vocabit.data.model.api.response.extraLetter.ExtraLetterQuestionResponse;
import com.example.vocabit.data.model.api.response.fillQuestion.FillQuestionResponse;
import com.example.vocabit.data.model.api.response.imageQuestion.ImageQuestionResponse;
import com.example.vocabit.data.model.api.response.matchQuestion.MatchQuestionResponse;
import com.example.vocabit.data.model.api.response.practice.PracticeResponse;
import com.example.vocabit.data.model.api.response.ResponseWrapper;
import com.example.vocabit.data.model.api.response.login.LoginResponse;
import com.example.vocabit.data.model.api.response.register.RegisterResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiService {
    //Login
    @POST("auth/log-in")
    Observable<ResponseWrapper<LoginResponse>> login(@Body LoginRequest request);

    @GET("practices")
    Observable<ResponseWrapper<List<PracticeResponse>>> getPractices();

    @GET("questions/image-to-text")
    Observable<ResponseWrapper<List<ImageQuestionResponse>>> getImageQuestions(
            @Query("unit") int unit
    );
    @GET("questions/fill-in-blank")
    Observable<ResponseWrapper<List<FillQuestionResponse>>> getFillQuestions(
            @Query("unit") int unit
    );
    @GET("questions/extra-letter")
    Observable<ResponseWrapper<List<ExtraLetterQuestionResponse>>> getExtraLetterQuestions(
            @Query("unit") int unit
    );
    //
    @GET("questions/match")
    Observable<ResponseWrapper<List<MatchQuestionResponse>>> getMatchQuestions(
            @Query("unit") int unit
    );
    @POST("users")
    Observable<RegisterResponse> register(@Body RegisterRequest request);

    @POST("users/{id}")
    Completable changePassword(@Body ChangePasswordRequest request);

}
