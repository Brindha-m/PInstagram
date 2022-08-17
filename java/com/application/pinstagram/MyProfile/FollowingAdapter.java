package com.application.pinstagram.MyProfile;

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

import com.application.pinstagram.Notification.NotificationReqModel;
import com.application.pinstagram.R;

import java.util.ArrayList;

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.viewHolder> {

    ArrayList<NotificationReqModel> list;
    Context context;


    public FollowingAdapter(ArrayList<NotificationReqModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_followingitem, parent, false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        NotificationReqModel model = list.get(position);
        holder.profile.setImageResource(model.getProfile());
        holder.username.setText(model.getUsername());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView profile;
        TextView username;
        Button following;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            profile = itemView.findViewById(R.id.ivrequestedUser);
            username = itemView.findViewById(R.id.tvrequestedUser);
            following = itemView.findViewById(R.id.btfollow);

            following.setOnClickListener(view -> {
                following.setText("Following");
                following.setBackgroundColor(Color.GRAY);
            });

        }
    }
}
