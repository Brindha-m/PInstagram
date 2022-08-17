package com.application.pinstagram.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.application.pinstagram.Feed.Dashboard;
import com.application.pinstagram.Model.LoginRequestDTO;
import com.application.pinstagram.R;
import com.application.pinstagram.Register.RegisterActivity;

import com.application.pinstagram.Retrofit.IApi;
import com.application.pinstagram.Retrofit.LoginBuilder;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    // private Button login;
    private TextView txt_signup;
    //    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        // login = findViewById(R.id.login);
        txt_signup = findViewById(R.id.txt_signup);


        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


    }

    public void login(View view) {
        if (!(email.getText().toString().equals("") || password.getText().toString().equals(""))) {

            Retrofit retrofit = LoginBuilder.getInstance();
            IApi iApi = retrofit.create(IApi.class);

            LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
            loginRequestDTO.setEmail(email.getText().toString());
            loginRequestDTO.setPassword(password.getText().toString());
            loginRequestDTO.setSocialMediaId();
            Log.e("login: ", email.getText().toString());
            Log.e("login: ", password.getText().toString());


            Call<LoginRequestDTO> call = iApi.logIn(loginRequestDTO);
            Log.e("logindto", loginRequestDTO.toString());
            call.enqueue(new Callback<LoginRequestDTO>() {

                @Override
                public void onResponse(@NonNull Call<LoginRequestDTO> call, @NonNull Response<LoginRequestDTO> response1) {
                    Log.e("login", String.valueOf(response1.body()));
                    editor.putString("email", response1.body().getEmail());
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                    startActivity(intent);

                }

                @Override
                public void onFailure(@NonNull Call<LoginRequestDTO> call, @NonNull Throwable t) {

                }
            });
        } else {
            Toast.makeText(LoginActivity.this, "Login or Password can't be blank!", Toast.LENGTH_SHORT).show();
        }


    }
}
