package com.example.linux.muscleapp.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by paco on 6/02/18.
 */

public class ApiAdapter {
    private static ApiService API_SERVICE;

    public static final String BASE_URL = "https://muscleapp.club/apislim/public/";

    public static synchronized ApiService getInstance() {

        if (API_SERVICE == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(5, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build();

            Gson gson = new GsonBuilder()
                    .setDateFormat("dd-MM-yyyy")
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();

            API_SERVICE = retrofit.create(ApiService.class);
        }
        return  API_SERVICE;
    }

}
