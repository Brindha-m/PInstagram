package com.application.pinstagram.Feed;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.pinstagram.HomePost.PostAdapter;
import com.application.pinstagram.HomePost.StoryAdapter;
import com.application.pinstagram.Model.PostDTO;
import com.application.pinstagram.Model.SecondSignUpDTO;
import com.application.pinstagram.Model.StoryDTO;
import com.application.pinstagram.Post.IPostApi;
import com.application.pinstagram.Post.PostBuilder;
import com.application.pinstagram.R;
import com.application.pinstagram.Register.SecondSignUp;
import com.application.pinstagram.Retrofit.MainBuilder;
import com.application.pinstagram.Retrofit.StoryBuilder;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FeedFragment extends Fragment {

    RecyclerView rv1, rv2, rvprofPost;
    List<PostDTO> plist = new ArrayList<>();
    List<StoryDTO> slist = new ArrayList<>();
    List<SecondSignUpDTO> proflist = new ArrayList<>();

    CircleImageView mystory;
    ImageView profileimg;
    TextView userName;


    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fraugment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mystory = getView().findViewById(R.id.ivPersonalProfile);
        profileimg = getView().findViewById(R.id.profileimg);
//        userName = getView().findViewById(R.id.idTVAuthorName_feed);


        mystory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog postMedia = new Dialog(getContext());
                postMedia.setTitle("Story");
                postMedia.setContentView(R.layout.activity_story);
                postMedia.show();


                ImageView delete = (ImageView) postMedia.findViewById(R.id.story_delete);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        postMedia.cancel();
                        // postMedia.hide();
                    }
                });
            }

//            @Override
//            public void onClick(View view) {
//
//            }
        });



        //For stories


//        rv1 = view.findViewById(R.id.rv1);
//        list = new ArrayList<>();
//        list.add(new StoryDTO(R.drawable.frozen, "Smriti"));
//        list.add(new StoryDTO(R.drawable.img_5, "Jemi"));
//        list.add(new StoryDTO(R.drawable.logo, "Rohit"));
//        list.add(new StoryDTO(R.drawable.fire, "Mahi"));
//        list.add(new StoryDTO(R.drawable.angry_face, "Virat"));

        rv1 = view.findViewById(R.id.rv1);

        Retrofit retrofit1 = StoryBuilder.getInstance();
        IPostApi iPostApi1 = retrofit1.create(IPostApi.class);
        Call<List<StoryDTO>> storyDTO = iPostApi1.getStories();
        //Call<List<PostDTO>> postDTO = iPostApi.getPosts(sharedPreferences.getString("userId","DEFAULT"));

        storyDTO.enqueue(new Callback<List<StoryDTO>>() {
            @Override
            public void onResponse(Call<List<StoryDTO>> call, Response<List<StoryDTO>> response) {

                slist = response.body();
                StoryAdapter adapter = new StoryAdapter(slist, getContext());
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                rv1.setLayoutManager(layoutManager);
                rv1.setNestedScrollingEnabled(false);
                rv1.setAdapter(adapter);


                Log.e("Story User Details..", String.valueOf(response.body()));
                // Toast.makeText(getContext(), "Post added successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<StoryDTO>> call, Throwable t) {
                Log.e("Story Details error..", t.getMessage());

            }
        });


//        //for posts
//        rv2 = view.findViewById(R.id.rv2);
//        plist = new ArrayList<>();
//        plist.add(new PostingDTO(R.drawable.img_6, "Smriti", "Hi.. hello✋ð"));
//        plist.add(new PostingDTO(R.drawable.frozen, "Aishwarya", "supereh!"));
//        plist.add(new PostingDTO(R.drawable.img_5, "Pramila", "uhhh..!"));
//        plist.add(new PostingDTO(R.drawable.img_2, "Renu", "wowoo!"));

        rv2 = view.findViewById(R.id.rv2);
        //SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.application.pinstagram", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("com.application.pinstagram", Context.MODE_PRIVATE);

        Retrofit retrofit = PostBuilder.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        Call<List<PostDTO>> postDTO = iPostApi.getPosts();
        //Call<List<PostDTO>> postDTO = iPostApi.getPosts(sharedPreferences.getString("userId","DEFAULT"));

        postDTO.enqueue(new Callback<List<PostDTO>>() {
            @Override
            public void onResponse(Call<List<PostDTO>> call, Response<List<PostDTO>> response) {

                plist = response.body();
                PostAdapter padapter = new PostAdapter(plist, getContext());
                LinearLayoutManager playoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                rv2.setLayoutManager(playoutManager);
                rv2.setNestedScrollingEnabled(false);
                rv2.setAdapter(padapter);

                Log.e("Feed User Details..", String.valueOf(response.body()));
                // Toast.makeText(getContext(), "Post added successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<PostDTO>> call, Throwable t) {
                Log.e("FUser Details error..", t.getMessage());

            }
        });


//  Navigation to profile
        rvprofPost = view.findViewById(R.id.precycler_view);


    }

}