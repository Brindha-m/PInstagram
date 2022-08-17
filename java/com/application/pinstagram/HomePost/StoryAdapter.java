package com.application.pinstagram.HomePost;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.pinstagram.Model.StoryDTO;
import com.application.pinstagram.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.viewHolder> {

    List<StoryDTO> list;
    Context context;

    public StoryAdapter(List<StoryDTO> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_story_home, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        StoryDTO model = list.get(position);
        Glide.with(holder.profile.getContext()).load(model.getUrl()).placeholder(R.drawable.post_upload).into(holder.profile);

        //holder.username.setText(model.getUserId());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView profile;
        TextView username;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            profile = itemView.findViewById(R.id.ivProfile);
            username = itemView.findViewById(R.id.tvUsername);

//
        }
    }
}
