package com.application.pinstagram.Feed;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.application.pinstagram.CommonNotification.NotificationBuilder;
import com.application.pinstagram.CommonNotification.Response;
import com.application.pinstagram.Notification.NotificationFrag;
import com.application.pinstagram.Notification.NotificationFrag1;
import com.application.pinstagram.R;
import com.application.pinstagram.MyProfile.MyProfileFragment;
import com.application.pinstagram.Retrofit.IApi;
import com.application.pinstagram.SearchList.SearchFragment;
import com.application.pinstagram.Story.StoryFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class Dashboard extends AppCompatActivity {
//    SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
//    SharedPreferences.Editor editor = sharedPreferences.edit();

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        FeedFragment feedFragment = new FeedFragment();

//        RecommendationFragment recommendationFragment = new RecommendationFragment();
        MyProfileFragment myProfileFragment = new MyProfileFragment();
        NotificationFrag notificationFrag = new NotificationFrag();
        NotificationFrag1 notificationFrag1 = new NotificationFrag1();

        AddFragment addFragment = new AddFragment();
        SearchFragment searchFragment = new SearchFragment();
        StoryFragment storyFragment = new StoryFragment();
        setCurrentFragment(feedFragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.nav_home:
                    setCurrentFragment(feedFragment);
                    break;

                case R.id.nav_profile:
                    setCurrentFragment(myProfileFragment);
                    break;

                case R.id.nav_story:
                    setCurrentFragment(storyFragment);
                    break;

                case R.id.nav_heart:
                    setCurrentFragment(notificationFrag1);
                    break;

                case R.id.nav_add:
                    setCurrentFragment(addFragment);
                    break;

//                case R.id.nav_search:
//                    setCurrentFragment(searchFragment);
//                    break;

                default:
                    setCurrentFragment(feedFragment);
                    break;
            }
            return true;
        });



        FirebaseMessaging.getInstance().subscribeToTopic("quiz")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribed";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }
                        Log.d("subscribe", msg);
                        Toast.makeText(Dashboard.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        String token = task.getResult();
                        sendRegistrationToServer(token);
                        Log.d("firstToken", token);
                    }

                });

    }

    private void setCurrentFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    public void storyview(View view) {

        TextView username = findViewById(R.id.tvUsername);
        CircleImageView profile = findViewById(R.id.ivProfile);
        final Dialog postMedia = new Dialog(Dashboard.this);
        postMedia.setTitle("Story");
        postMedia.setContentView(R.layout.activity_story);

        postMedia.show();

    }


    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
        SharedPreferences sharedPreferences=getSharedPreferences("com.application.pinstagram", Context.MODE_PRIVATE);
        Retrofit retrofit= NotificationBuilder.getInstance();
        IApi iPostApi=retrofit.create(IApi.class);
        Response response=new Response();
        response.setToken(token);
        response.setUserId(sharedPreferences.getString("email","DEFAULT"));
        response.setPlatformId("3");
        response.setSocialMediaId(2);
        Call<Response> responseCall=iPostApi.sendToken(response);
        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                System.out.println("Success");
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG);
            }
        });

    }



}

