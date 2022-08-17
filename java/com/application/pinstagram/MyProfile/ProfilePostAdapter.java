package com.application.pinstagram.MyProfile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.pinstagram.Model.PostingDTO;
import com.application.pinstagram.Model.SecondSignUpDTO;
import com.application.pinstagram.R;

import java.util.ArrayList;
import java.util.List;

public class ProfilePostAdapter extends RecyclerView.Adapter<ProfilePostAdapter.viewHolder> {

    List<SecondSignUpDTO> list = new ArrayList<>();
    Context context;


    public ProfilePostAdapter(List<SecondSignUpDTO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_profile, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        SecondSignUpDTO model = list.get(position);
        //holder.postimg.setImageResource(model.getImageUrl());
        holder.username.setText(model.getName());
        holder.bio.setText(model.getEmail());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView noOfFollowing, noOfFollowers, follower, following, username, bio;
        ImageView post, profile_img;
        Button editProfile;
        TextView followers, followings;
        RecyclerView rvPost, recycle_following;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            bio = itemView.findViewById(R.id.bio);
            profile_img = itemView.findViewById(R.id.user_pic);
        }
    }
}
