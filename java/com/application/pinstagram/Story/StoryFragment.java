package com.application.pinstagram.Story;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.application.pinstagram.Model.PostDTO;
import com.application.pinstagram.Model.SecondSignUpDTO;
import com.application.pinstagram.Model.StoryDTO;
import com.application.pinstagram.Post.IPostApi;
import com.application.pinstagram.Post.PostBuilder;
import com.application.pinstagram.R;
import com.application.pinstagram.Retrofit.MainBuilder;
import com.application.pinstagram.Retrofit.StoryBuilder;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StoryFragment extends Fragment {

    private static final int REQUEST_CODE = 22;
    private static final int PICK_IMAGE_REQUEST = 22;
    EditText userid;
    ImageView uploadImage;
    Uri imageUri;
    Button post;
    Uri downloadUri;
    StoryDTO storyDTO;



    public StoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_storyupload, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        post = getView().findViewById(R.id.post);
        uploadImage = getView().findViewById(R.id.image_upload);
      //  username = getView().findViewById(R.id.username);
        userid = getView().findViewById(R.id.userId);


        uploadImage.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            intent.setType("image/*");
//            String[] mimeTypes = {"image/jpeg", "image/png"};
//            intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
            startActivityForResult(intent, 22);
        });
        // email();
//
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // String musername = username.getText().toString();
                Date c = Calendar.getInstance().getTime();
                @SuppressLint
                        ("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String dateNow = dateFormat.format(date);
                String muserId = userid.getText().toString();

                storyDTO = new StoryDTO();
                storyDTO.setUserId(muserId);
                storyDTO.setUrl(downloadUri.toString());
                storyDTO.setCreatedTime(dateNow.toString());
                storyDTO.setExpiryTime(dateNow.toString());


                //postDTO.setName();


                Log.e("Image URL", String.valueOf(downloadUri));
                Log.e("date", String.valueOf(dateNow));


                Retrofit retrofit = StoryBuilder.getInstance();
                IPostApi iPostApi = retrofit.create(IPostApi.class);

                Call<StoryDTO> storyDTOCall = iPostApi.addStory(storyDTO);
                storyDTOCall.enqueue(new Callback<StoryDTO>() {
                    @Override
                    public void onResponse(Call<StoryDTO> call, Response<StoryDTO> response) {
                        Log.e("Add user detail..", String.valueOf(response.body()));
                        Toast.makeText(getContext(), "Story added successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<StoryDTO> call, Throwable t) {
                        Log.e("onFailure: ", t.getMessage());

                    }
                });
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == -1 && data != null && data.getData() != null) {
            imageUri = data.getData();
            uploadImage.setImageURI(imageUri);
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




}