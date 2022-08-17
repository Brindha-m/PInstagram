package com.application.pinstagram.Retrofit;

import com.application.pinstagram.CommonNotification.Response;
import com.application.pinstagram.Model.LoginRequestDTO;
import com.application.pinstagram.Model.UserData;
import com.application.pinstagram.SearchList.AddSearchDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IApi {

//    login
    @POST("user/login")
    Call<LoginRequestDTO> logIn(@Body LoginRequestDTO loginRequestDTO);


    //common infra

    @POST("/token/save")
    Call<Response> sendToken(@Body Response response);


}
