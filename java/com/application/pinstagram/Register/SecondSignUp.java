package com.application.pinstagram.Register;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.application.pinstagram.Feed.Dashboard;
import com.application.pinstagram.Login.LoginActivity;
import com.application.pinstagram.Model.SecondSignUpDTO;
import com.application.pinstagram.Model.UpdateRoleDTO;
import com.application.pinstagram.Post.IPostApi;
import com.application.pinstagram.R;
import com.application.pinstagram.Retrofit.ISignUP;
import com.application.pinstagram.Retrofit.MainBuilder;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SecondSignUp extends AppCompatActivity {

    private static final int REQUEST_CODE = 22;
    private final int PICK_IMAGE_REQUEST = 22;
    //  private StorageReference mStorageRef;
    private Uri imageUri, downloadUri;
    private ImageView imgageView;
    private ImageView dobImageView;
    private TextView dobTextView;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String authToken;
    private RadioGroup radioGroupGender;
    private RadioGroup radioGroupProfileType;
    private UpdateRoleDTO updateRoleDTO;
    private Button saveDetails;
    ArrayList<String> interests = new ArrayList<>();
    private CheckBox checkBoxGaming, checkBoxTechnology, checkBoxFood, checkBoxMusic, checkBoxSports;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_sign_up);
        //mStorageRef = FirebaseStorage.getInstance().getReference();
        updateRoleDTO = new UpdateRoleDTO();
        imgageView = findViewById(R.id.image_dp);
        dobImageView = findViewById(R.id.dob_image);
        dobTextView = findViewById(R.id.dob_textview);
        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
        radioGroupGender = findViewById(R.id.radio_group_gender);
        radioGroupProfileType = findViewById(R.id.radio_group_profile);
        saveDetails = findViewById(R.id.btsave);

        SecondSignUpDTO secondSignUpDTO = new SecondSignUpDTO();

        Bundle bundle = getIntent().getExtras();
        // String nid = bundle.getString(("id"));
        String nemail = bundle.getString("Email");
        String npassword = bundle.getString("Password");
        String nusername = bundle.getString("name");
        String nmobile = bundle.getString("Mobile");
        //Log.e("name..",bundle.getString("name"));


        checkBoxGaming = findViewById(R.id.checkBox);
        checkBoxTechnology = findViewById(R.id.checkBox2);
        checkBoxFood = findViewById(R.id.checkBox3);
        checkBoxMusic = findViewById(R.id.checkBox4);
        checkBoxSports = findViewById(R.id.checkBox5);
        //submit = findViewById(R.id.button);


        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
                if (null != radioButton && checkedId != -1) {
                    secondSignUpDTO.setGender(radioButton.getText().toString());
                }

            }
        });

        radioGroupProfileType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
                if (null != radioButton && checkedId != -1) {
                    secondSignUpDTO.setAccountType(radioButton.getText().toString());

                }

            }
        });


//        List<String> interests = new ArrayList<>();
//        interests.add("sports");
//        secondSignUpDTO.setInterests(interests);


        dobImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(SecondSignUp.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                dobTextView.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                //secondSignUpDTO.setDOB(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        saveDetails.setOnClickListener(view ->
        {

            if (checkBoxGaming.isChecked()) {
                interests.add("Gaming");
            }
            if (checkBoxTechnology.isChecked()) {
                interests.add("Technology");
            }
            if (checkBoxFood.isChecked()) {
                interests.add("Food");
            }
            if (checkBoxMusic.isChecked()) {
                interests.add("Music");
            }
            if (checkBoxSports.isChecked()) {
                interests.add("Sports");
            }

            // Toast.makeText(getApplicationContext(), result.add(result), Toast.LENGTH_SHORT).show();


            Retrofit retrofit = MainBuilder.getInstance();
            ISignUP iSignUp = retrofit.create(ISignUP.class);

            secondSignUpDTO.setName(nusername);
            secondSignUpDTO.setEmail(nemail);
            secondSignUpDTO.setInterests(interests);
            secondSignUpDTO.setPassword(npassword);
            secondSignUpDTO.setMobileNumber(nmobile);
            secondSignUpDTO.setSocialMediaId();
            secondSignUpDTO.setInterests(interests);

            //account and gender set early


            Log.e("output: ", secondSignUpDTO.toString());

            Call<SecondSignUpDTO> secondSignUpDTOCall = iSignUp.signUp(secondSignUpDTO);


            secondSignUpDTOCall.enqueue(new Callback<SecondSignUpDTO>() {
                @Override
                public void onResponse(Call<SecondSignUpDTO> call, Response<SecondSignUpDTO> response) {
                    Intent intent = new Intent(SecondSignUp.this, LoginActivity.class);
                    startActivity(intent);
                    Log.e("signup User Details..", String.valueOf(response.body()));

                    //userId
                    Retrofit retrofit1 = MainBuilder.getInstance();
                    IPostApi iPostApi1 = retrofit1.create(IPostApi.class);
                    sharedPreferences = getSharedPreferences("com.application.pinstagram", Context.MODE_PRIVATE);
                    editor = sharedPreferences.edit();

                    Call<SecondSignUpDTO> res = iPostApi1.getUserId(sharedPreferences.getString("email", "Default"));
                    res.enqueue(new Callback<SecondSignUpDTO>() {
                        @Override
                        public void onResponse(@NonNull Call<SecondSignUpDTO> call, @NonNull Response<SecondSignUpDTO> response) {
                            if (response.body() != null) {
                                editor.putString("userId", response.body().getUserId());
                                editor.apply();
                                editor.putString("username", response.body().getName());
                                editor.apply();

//                    postDTO.setUserId(response.body().getUserId());
                                String userId;
                                userId = response.body().getUserId();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<SecondSignUpDTO> call, @NonNull Throwable t) {
                            Log.d("userId", "Userid faillure");
                        }
                    });
                }


                @Override
                public void onFailure(Call<SecondSignUpDTO> call, Throwable t) {

                }
            });


            //startActivity(new Intent(SecondSignUp.this, Dashboard.class));

        });


    }


    public void uploadImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 22);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imgageView.setImageURI(imageUri);

            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            StorageReference Ref = storageRef.child("pinstagram/" + imageUri.getLastPathSegment());
            UploadTask uploadTask = Ref.putFile(imageUri);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                }
            });

            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    Log.d("url", Ref.getDownloadUrl().toString());
                    return Ref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        downloadUri = task.getResult();
                        Log.d("url", task.getResult().toString());
                    } else {
                        // Handle failures
                        // ...
                    }
                }
            });

        }
    }


    private String getExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }


}
