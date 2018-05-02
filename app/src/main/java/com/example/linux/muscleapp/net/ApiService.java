package com.example.linux.muscleapp.net;

import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.SessionDate;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;



public interface ApiService {
    @SerializedName("users")
    @POST("users/{email}")
    Call<Result> getUsers(@Body User current, @Path("email") String email);

    @SerializedName("currentUser")
    @GET("users/current/{email}")
    Call<Result> getCurrentUser(@Path("email") String email);

    @SerializedName("insertUser")
    @POST("user")
    Call<Result> insertUser(@Body User current);

    @SerializedName("sendConfirm")
    @POST("ass/{email}")
    Call<Result> sendConfirm(@Path("email") String email);

    @SerializedName("sendRecovery")
    @POST("recovery/{email}")
    Call<Result> sendRecovery(@Path("email") String email);

    @SerializedName("userName")
    @GET("users/name/{id}")
    Call<Result> getUserName(@Path("id") int id);

    @SerializedName("sessions")
    @GET("sessions")
    Call<Result> getSessions();

    @SerializedName("insertSession")
    @POST("session")
    Call<Result> insertSession(@Body Session current);

    @SerializedName("sessionId")
    @POST("sessions/creation/id")
    Call<Result> getSessionId(@Body Session current);

    @SerializedName("addSessionDates")
    @POST("sessionDates/add")
    Call<Result> insertSessionDates(@Body SessionDate current);

    @SerializedName("addExcersice")
    @POST("excersices/add")
    Call<Result> insertExcersices(@Body Excersice current);
}

