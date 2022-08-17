package com.application.pinstagram.Register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.application.pinstagram.Login.LoginActivity;
import com.application.pinstagram.R;


public class RegisterActivity extends AppCompatActivity {

    private EditText nusername;
    private EditText ncpassword;
    private EditText nemail;
    private EditText npassword,nmobileNumber;
    private TextView txt_login;
    private LinearLayout progressBarLinearLayout;


    private static int usernameFlag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nusername = findViewById(R.id.nusername);
        nemail = findViewById(R.id.nemail);
        npassword = findViewById(R.id.npassword);
        ncpassword = findViewById(R.id.ncpassword);
        nmobileNumber = findViewById(R.id.nmobile);
        //register = findViewById(R.id.register);
        txt_login = findViewById(R.id.txt_login);
        progressBarLinearLayout = findViewById(R.id.progressbar_layout);


        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    public void register(View view) {

        Intent intent = new Intent(RegisterActivity.this, SecondSignUp.class);
        Bundle bundle = new Bundle();
        bundle.putString("Email", nemail.getText().toString());
        bundle.putString("name", nusername.getText().toString());
        bundle.putString("Password", npassword.getText().toString());
        bundle.putString("Confirm Password", ncpassword.getText().toString());
        bundle.putString("Mobile", nmobileNumber.getText().toString());

        SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("USERNAME", String.valueOf(nusername));
        editor.apply();


        intent.putExtras(bundle);
        startActivity(intent);


    }
}
