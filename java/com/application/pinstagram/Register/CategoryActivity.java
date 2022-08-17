package com.application.pinstagram.Register;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.application.pinstagram.Feed.Dashboard;
import com.application.pinstagram.R;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    ArrayList<String> result = new ArrayList<>();

    private CheckBox checkBoxGaming, checkBoxTechnology, checkBoxFood,checkBoxMusic,checkBoxSports;

    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Button button = findViewById(R.id.button);

        checkBoxGaming = findViewById(R.id.checkBox);
        checkBoxTechnology = findViewById(R.id.checkBox2);
        checkBoxFood = findViewById(R.id.checkBox3);
        checkBoxMusic = findViewById(R.id.checkBox4);
        checkBoxSports = findViewById(R.id.checkBox5);
        //submit = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> result = new ArrayList<>();
                if(checkBoxGaming.isChecked()){
                    result.add("Gaming");

                }

                if(checkBoxTechnology.isChecked()){
                    result.add("Technology");
                }
                if(checkBoxFood.isChecked()){
                    result.add("Food");
                }
                if(checkBoxMusic.isChecked()){
                    result.add("Music");
                }
                if(checkBoxSports.isChecked()){
                    result.add("Sports");
                }

               // Toast.makeText(getApplicationContext(), result.add(result), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CategoryActivity.this, Dashboard.class));

            }

        });



    }

} 
