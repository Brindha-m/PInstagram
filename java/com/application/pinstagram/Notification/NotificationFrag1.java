package com.application.pinstagram.Notification;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.pinstagram.CommonNotification.NotificationBuilder;
import com.application.pinstagram.HomePost.StoryAdapter;
import com.application.pinstagram.Model.StoryDTO;
import com.application.pinstagram.Post.IPostApi;
import com.application.pinstagram.R;
import com.application.pinstagram.Retrofit.StoryBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class NotificationFrag1 extends Fragment {

    RecyclerView notificationRV, rvnotification;
//    ArrayList<NotificationModel> list;
    ArrayList<NotificationReqModel> list1;
    List<NotificationModel1> list = new ArrayList<>();
    Button confirm;

    public NotificationFrag1() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification1, container, false);
        notificationRV = view.findViewById(R.id.notificationRV);

        Retrofit retrofit1 = NotificationBuilder.getInstance();
        IPostApi iPostApi1 = retrofit1.create(IPostApi.class);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("com.application.pinstagram", Context.MODE_PRIVATE);

        Call<List<NotificationModel1>> storyDTO = iPostApi1.getNotification(sharedPreferences.getString("email","DEFAULT"));
        //Call<List<PostDTO>> postDTO = iPostApi.getPosts(sharedPreferences.getString("email","DEFAULT"));

        storyDTO.enqueue(new Callback<List<NotificationModel1>>() {
            @Override
            public void onResponse(Call<List<NotificationModel1>> call, Response<List<NotificationModel1>> response) {

                list = response.body();
                Log.e("NOTIFICATION",list.toString());
                NotificationAdapter adapter = new NotificationAdapter(list, getContext());
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                notificationRV.setLayoutManager(layoutManager);
                notificationRV.setNestedScrollingEnabled(false);
                notificationRV.setAdapter(adapter);

                // Toast.makeText(getContext(), "Post added successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<NotificationModel1>> call, Throwable t) {
                Log.e("Story Details error..", t.getMessage());

            }
        });


//        list = new ArrayList<>();
//        list.add(new NotificationModel(R.drawable.logo, "<b>Alicia Addams</b> mention you in a comment: Nice Try", "just now"));
//        list.add(new NotificationModel(R.drawable.frozen, "<b>Janeeleona</b> Liked your picture.", "40 minutes ago"));
//        list.add(new NotificationModel(R.drawable.logo, "<b>Katherinn</b> Commented on your post.", "2 hours"));
//        list.add(new NotificationModel(R.drawable.logo, "<b>Alicia Addams</b> mention you in a comment: Nice Try", "3 hours"));
//        list.add(new NotificationModel(R.drawable.frozen, "<b>Janeeleona</b> Liked your picture.", "3 hours"));
//        list.add(new NotificationModel(R.drawable.ic_profile, "<b>Katherinn</b> Commented on your post.", "4 hours"));
//        list.add(new NotificationModel(R.drawable.frozen, "<b>Alicia Addams</b> mention you in a comment: Nice Try", "4 hours"));
//        list.add(new NotificationModel(R.drawable.logologin, "<b>Janeeleona</b> Liked your picture.", "4 hours"));
//        list.add(new NotificationModel(R.drawable.insta_logo, "<b>Katherinn</b> Commented on your post.", "4 hours"));
//
//
//        NotificationAdapter adapter = new NotificationAdapter(list, getContext());
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        notificationRV.setLayoutManager(layoutManager);
//        notificationRV.setNestedScrollingEnabled(false);
//        notificationRV.setAdapter(adapter);

        View view1 = inflater.inflate(R.layout.item_msgreq_likes, container, false);
        rvnotification = view.findViewById(R.id.rvnotification);
        list1 = new ArrayList<>();
        list1.add(new NotificationReqModel(R.drawable.frozen, "Brindha"));
        list1.add(new NotificationReqModel(R.drawable.logo, "Aishwarya"));
        list1.add(new NotificationReqModel(R.drawable.frozen, "Pramila"));
        list1.add(new NotificationReqModel(R.drawable.img_1, "Renu"));
        list1.add(new NotificationReqModel(R.drawable.img_2, "Harleen"));
        list1.add(new NotificationReqModel(R.drawable.img_4, "Harman"));



        NotificationReqAdapter adapter1 = new NotificationReqAdapter(list1, getContext());
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvnotification.setLayoutManager(layoutManager1);
        rvnotification.setNestedScrollingEnabled(false);
        rvnotification.setAdapter(adapter1);




        return view;
    }
}