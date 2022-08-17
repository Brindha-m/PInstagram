package com.application.pinstagram.Notification;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.pinstagram.R;

import java.util.ArrayList;

public class NotificationReqAdapter extends RecyclerView.Adapter<NotificationReqAdapter.viewHolder> {

    ArrayList<NotificationReqModel> list;
    Context context;
    Button confirm;


    public NotificationReqAdapter(ArrayList<NotificationReqModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_msgreq_likes, parent, false);
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
        Button confirm,delete;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            profile = itemView.findViewById(R.id.ivrequestedUser);
            username = itemView.findViewById(R.id.tvrequestedUser);
            confirm = itemView.findViewById(R.id.btconfirm);
            delete = itemView.findViewById(R.id.btdelete);
            confirm.setOnClickListener(view -> {
                confirm.setText("Following");
                delete.setText("");
                delete.setBackgroundColor(Color.TRANSPARENT);
            });

        }
    }
}
