package com.application.pinstagram.MyProfile;

import android.app.Dialog;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.application.pinstagram.HomePost.PostAdapter;
import com.application.pinstagram.Login.LoginActivity;
import com.application.pinstagram.Model.PostDTO;
import com.application.pinstagram.Model.PostingDTO;
import com.application.pinstagram.Model.ProfileDTO;
import com.application.pinstagram.Notification.NotificationReqModel;
import com.application.pinstagram.Post.IPostApi;
import com.application.pinstagram.Post.PostBuilder;
import com.application.pinstagram.R;
import com.application.pinstagram.Retrofit.MainBuilder;
import com.application.pinstagram.Retrofit.ProfileBuilder;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MyProfileFragment extends Fragment {

    TextView noOfFollowing, noOfFollowers, follower, following;
    ImageView post, options;
    Button editProfile;
    TextView followers, followings;
    RecyclerView rvPost, recycle_following;
    List<ProfileDTO> proflist = new ArrayList<>();
    ArrayList<NotificationReqModel> flist;
    ProfileDTO profileDTO;

    Uri imageUrii,downloadUrii;
    ImageView image_profile;

    public MyProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("com.example.pinstagram", Context.MODE_PRIVATE);

        TextView username = getView().findViewById(R.id.username);
        TextView bio = getView().findViewById(R.id.bio);

//
//        SharedPreferences sharedPreferences = getContext().getSharedPreferences("myKey",Context.MODE_PRIVATE);
//        String value = sharedPreferences.getString("USERNAME","");
//        username.setText(value);

//        username.setText(username.getText());
//        editor.putString("targetId", userId);
//        editor.apply();


        follower = getView().findViewById(R.id.tv_followers);
        following = getView().findViewById(R.id.tv_following);
        post = getView().findViewById(R.id.my_posts);
        noOfFollowing = getView().findViewById(R.id.followers);
        noOfFollowers = getView().findViewById(R.id.following);
        editProfile = getView().findViewById(R.id.edit_profile);
        followers = getView().findViewById(R.id.followers);
        followings = getView().findViewById(R.id.followings);
        options = getView().findViewById(R.id.options);
        image_profile = (CircleImageView) editProfile.findViewById(R.id.pimage_profile);

//    story = getView().findViewById(R.id.button_story);


//        follower.setOnClickListener(view1 -> {
//            editor.putString("targetId", userId);
//            editor.apply();
////            startActivity(new Intent(getContext(), Followers.class));
//        });
//
//        following.setOnClickListener(view1 -> {
//            editor.putString("targetId", userId);
//            editor.apply();
////            startActivity(new Intent(getContext(), Following.class));
//        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog editProfile = new Dialog(getContext());
//                postMedia.setTitle("Post");
                editProfile.setContentView(R.layout.activity_edit_profile);
                EditText etusername = (EditText) editProfile.findViewById(R.id.pusername);
                EditText bio = (EditText) editProfile.findViewById(R.id.bio1);
                EditText userId = (EditText) editProfile.findViewById(R.id.userid);
               // image_profile = (CircleImageView) editProfile.findViewById(R.id.image_profile);
                TextView changeimage = (TextView) editProfile.findViewById(R.id.tv_change);
                Button save = (Button) editProfile.findViewById(R.id.save);
                changeimage.setOnClickListener(view2 ->
                {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 22);
                });

                save.setOnClickListener(view1 -> {


                    String pusername = etusername.getText().toString();
                    String pbio = bio.getText().toString();
                    String puserId = userId.getText().toString();

                    Log.e("username",pusername);



                    profileDTO = new ProfileDTO();
                    profileDTO.setUserId(puserId);
//                    profileDTO.setProfile_image(downloadUrii.toString());
                    profileDTO.setUsername(pusername);
                    profileDTO.setBio(pbio);

//                    bio.setText(profileDTO.getBio());
//                    username.setText(profileDTO.getUsername());

                    Retrofit retrofit1 = ProfileBuilder.getInstance();
                    IPostApi iApi = retrofit1.create(IPostApi.class);
                    Call<ProfileDTO> profilecallDTO = iApi.getProfile(profileDTO);

                    profilecallDTO.enqueue(new Callback<ProfileDTO>() {

                        @Override
                        public void onResponse(Call<ProfileDTO> call, Response<ProfileDTO> response) {
                            Toast.makeText(getContext(), "Profile edited successfully", Toast.LENGTH_SHORT).show();

                            Log.e("Profile..", String.valueOf(response.body()));

                        }

                        @Override
                        public void onFailure(Call<ProfileDTO> call, Throwable t) {
                            Log.e("profile error", t.getMessage());
                        }
                    });

                    ImageView close = (ImageView) editProfile.findViewById(R.id.close);
                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            editProfile.cancel();
                            // editProfile.closeOptionsMenu();
                        }
                    });



                });
                editProfile.show();


            }
        });


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog postMedia = new Dialog(getContext());
//                postMedia.setTitle("Post");
                postMedia.setContentView(R.layout.posting_option);
                Button image = (Button) postMedia.findViewById(R.id.button_image);
                Button video = (Button) postMedia.findViewById(R.id.button_video);
                image.setOnClickListener(view1 -> {
//                    startActivity(new Intent(getContext(),PostImage.class));
                });
                video.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Intent intent = new Intent(getContext() , PostVideo.class);
//                        startActivity(intent);

                    }
                });
                postMedia.show();
            }
        });

        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog postMedia = new Dialog(getContext());
//                postMedia.setTitle("Post");
                postMedia.setContentView(R.layout.activity_logout);
                Button logout = (Button) postMedia.findViewById(R.id.btlogout);

                logout.setOnClickListener(view1 -> {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                });
                postMedia.show();
            }
        });




//        posts -- profile




        // FOLLOWERS

        followers.setOnClickListener(view1 -> {
            startActivity(new Intent(getContext(), FollowerActivity.class));

        });

        followings.setOnClickListener(view1 -> {
            startActivity(new Intent(getContext(), FollowingActivity.class));

        });


//        logout = getView().findViewById(R.id.logout);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 22 && resultCode == -1 && data != null && data.getData() != null) {
            imageUrii = data.getData();
            image_profile.setImageURI(imageUrii);
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            StorageReference Ref = storageRef.child("pinstagram/" + imageUrii.getLastPathSegment());
            UploadTask uploadTask = Ref.putFile(imageUrii);
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
                        downloadUrii = task.getResult();
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