package com.application.pinstagram.Retrofit;

import com.application.pinstagram.Model.SecondSignUpDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ISignUP {
    @POST("user/signup")
    Call<SecondSignUpDTO> signUp(@Body SecondSignUpDTO secondSignUpDTO);

}
