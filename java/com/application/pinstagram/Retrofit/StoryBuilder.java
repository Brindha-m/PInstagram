package com.application.pinstagram.Retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StoryBuilder {

    private static Retrofit instance;

    private StoryBuilder() {

    }

    public static Retrofit getInstance() {
        if (instance == null) {
            synchronized (StoryBuilder.class) {
                if (instance == null) {
                    instance = new Retrofit.Builder().baseUrl("http://10.20.4.157:9011")
                            .addConverterFactory(GsonConverterFactory.create())
                            .client((new OkHttpClient())).build();
                }
            }
        }
        return instance;
    }

}