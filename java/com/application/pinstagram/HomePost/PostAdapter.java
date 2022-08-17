package com.application.pinstagram.HomePost;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.pinstagram.Model.PostDTO;
import com.application.pinstagram.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.viewHolder> {

    List<PostDTO> list;
    Context context;
    RecyclerView rvPost, recycle_following;

    public PostAdapter(List<PostDTO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.insta_feed_rv_item, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        PostDTO model = list.get(position);
       // holder.postimg.setImageResource(model.getPostUrl());

        holder.hashtag.setText(model.getHashtag());
        holder.caption.setText(model.getCaption());

        holder.username.setText(model.getName());
        //holder.date.setText(model.getDate());
        holder.date.setText(model.getDate());
        Glide.with(holder.postimg.getContext()).load(model.getPostUrl()).placeholder(R.drawable.frozen).into(holder.postimg);
        Glide.with(holder.profileimg.getContext()).load(model.getPostUrl()).placeholder(R.drawable.frozen).into(holder.profileimg);



    }

    @Override
    public int getItemCount() {
        if(list!=null) {
            return list.size();
        }
        else {
            return 0;
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView postimg,profileimg;
        TextView hashtag,username,like,tvlikes,caption,date;
        Button thumbsUp_feed,thumbsDown_feed,heart,notnice,angry,smile;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            caption = itemView.findViewById(R.id.tv_desc);
            postimg = itemView.findViewById(R.id.idIVPost_feed);
            hashtag = itemView.findViewById(R.id.commentedby);
            date = itemView.findViewById(R.id.date);
            profileimg = itemView.findViewById(R.id.profileimg);
            username = itemView.findViewById(R.id.idTVAuthorName_feed);
            thumbsDown_feed = itemView.findViewById(R.id.thumbsDown_feed);
            thumbsUp_feed = itemView.findViewById(R.id.thumbsUp_feed);
            angry = itemView.findViewById(R.id.angry);
            notnice = itemView.findViewById(R.id.notnice);
            smile = itemView.findViewById(R.id.smile);
            heart = itemView.findViewById(R.id.heart);
            tvlikes = itemView.findViewById(R.id.tvlikes);


            like = itemView.findViewById(R.id.idTVLikes);

            thumbsUp_feed.setOnClickListener(view -> {

//                int count= Integer.parseInt(String.valueOf(like.getText()));
//                count++;
                like.setText("1");
               // userInterface.onAdd(postData,position,1);
                smile.setText("");
                notnice.setText("");
                angry.setText("");
                heart.setText("");
                thumbsDown_feed.setText("");

                smile.setBackgroundColor(Color.TRANSPARENT);
                notnice.setBackgroundColor(Color.TRANSPARENT);
                angry.setBackgroundColor(Color.TRANSPARENT);
                heart.setBackgroundColor(Color.TRANSPARENT);
                thumbsDown_feed.setBackgroundColor(Color.TRANSPARENT);
            });

            thumbsDown_feed.setOnClickListener(view -> {
//                int count= Integer.parseInt(String.valueOf(like.getText()));
//                count++;
                like.setText("1");
                tvlikes.setText("dislikes");

                smile.setText("");
                notnice.setText("");
                angry.setText("");
                heart.setText("");
                thumbsDown_feed.setText("");
                thumbsUp_feed.setText("\uD83D\uDC4E");

                smile.setBackgroundColor(Color.TRANSPARENT);
                notnice.setBackgroundColor(Color.TRANSPARENT);
                angry.setBackgroundColor(Color.TRANSPARENT);
                heart.setBackgroundColor(Color.TRANSPARENT);
                thumbsDown_feed.setBackgroundColor(Color.TRANSPARENT);
            });

            heart.setOnClickListener(view -> {
//                int count= Integer.parseInt(String.valueOf(like.getText()));
//                count++;
                like.setText("1");
                tvlikes.setText("❤ Hearts");

                smile.setText("");
                notnice.setText("");
                angry.setText("");
                heart.setText("");
                thumbsDown_feed.setText("");
                thumbsUp_feed.setText("❤");

                smile.setBackgroundColor(Color.TRANSPARENT);
                notnice.setBackgroundColor(Color.TRANSPARENT);
                angry.setBackgroundColor(Color.TRANSPARENT);
                thumbsUp_feed.setBackgroundColor(Color.TRANSPARENT);
                thumbsDown_feed.setBackgroundColor(Color.TRANSPARENT);
            });



        }
    }
}
