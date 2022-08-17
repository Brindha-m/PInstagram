package com.application.pinstagram.Post;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PostBuilder {
    public PostBuilder() {
    }
    private static Retrofit instance;
    public static Retrofit getInstance(){
        if (instance ==null){
            synchronized (PostBuilder.class){
                if(instance==null){
                    instance = new Retrofit.Builder().baseUrl("http://10.20.4.157:9014").addConverterFactory(ScalarsConverterFactory.create()).
                            addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient()).build();
                }
            }
        }
        return instance;
    }
}
