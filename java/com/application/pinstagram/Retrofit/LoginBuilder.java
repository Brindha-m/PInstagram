package com.application.pinstagram.Retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginBuilder {

    private static Retrofit instance;

    private LoginBuilder() {

    }

    public static Retrofit getInstance() {
        if (instance == null) {
            synchronized (LoginBuilder.class) {
                if (instance == null) {
                    instance = new Retrofit.Builder().baseUrl("http://10.20.3.120:8111/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .client((new OkHttpClient())).build();
                }
            }
        }
        return instance;
    }

}