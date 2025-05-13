package com.example.vocabit.data.remote;

import com.example.vocabit.data.model.api.request.changepassword.ChangePasswordRequest;
import com.example.vocabit.data.model.api.request.login.LoginRequest;
<<<<<<< HEAD
import com.example.vocabit.data.model.api.response.extraLetter.ExtraLetterQuestionResponse;
import com.example.vocabit.data.model.api.response.fillQuestion.FillQuestionResponse;
import com.example.vocabit.data.model.api.response.imageQuestion.ImageQuestionResponse;
import com.example.vocabit.data.model.api.response.matchQuestion.MatchQuestionResponse;
import com.example.vocabit.data.model.api.response.practice.PracticeResponse;
=======
import com.example.vocabit.data.model.api.request.login.ReLoginRequest;
import com.example.vocabit.data.model.api.request.register.RegisterRequest;
import com.example.vocabit.data.model.api.response.ApiResponse;
>>>>>>> 489eea1e23638349db860b054d6bc663c7666990
import com.example.vocabit.data.model.api.response.ResponseWrapper;
import com.example.vocabit.data.model.api.response.info.UserResponse;
import com.example.vocabit.data.model.api.response.login.LoginResponse;

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
    Observable<ApiResponse<LoginResponse>> login(@Body LoginRequest request);

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

    @PUT("users/{username}")
    Completable changePassword(@Path("username") String username, @Body ChangePasswordRequest request);

    @GET("users/{username}")
    Observable<UserResponse> getInfoUser(@Path("username") String username);

}
