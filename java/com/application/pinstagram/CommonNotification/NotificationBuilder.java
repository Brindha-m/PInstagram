package com.application.pinstagram.CommonNotification;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NotificationBuilder {

    public NotificationBuilder() {
    }
    private static Retrofit instance;
    public static Retrofit getInstance(){
        if (instance ==null){
            synchronized (NotificationBuilder.class){
                if(instance==null){
                    instance = new Retrofit.Builder().baseUrl("http://10.20.4.72:8084").addConverterFactory(ScalarsConverterFactory.create()).
                            addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient()).build();
                }
            }
        }
        return instance;
    }
}
