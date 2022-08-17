package com.application.pinstagram.MyProfile;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.pinstagram.Notification.NotificationReqModel;
import com.application.pinstagram.R;

import java.util.ArrayList;

public class FollowerActivity extends AppCompatActivity {
    ArrayList<NotificationReqModel> flist;
    RecyclerView recycle_following;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rvactivity_following);

        recycle_following = findViewById(R.id.recycle_following);

        flist = new ArrayList<>();
        flist.add(new NotificationReqModel(R.drawable.frozen, "Brindha"));
        flist.add(new NotificationReqModel(R.drawable.logo, "Aishwarya"));
        flist.add(new NotificationReqModel(R.drawable.frozen, "Pramila"));
        flist.add(new NotificationReqModel(R.drawable.img_1, "Renu"));
        flist.add(new NotificationReqModel(R.drawable.img_2, "Harleen"));
        flist.add(new NotificationReqModel(R.drawable.img_4, "Harman"));


        FollowersAdapter adapter1 = new FollowersAdapter(flist, FollowerActivity.this);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(FollowerActivity.this, LinearLayoutManager.VERTICAL, false);
        recycle_following.setLayoutManager(layoutManager1);
        recycle_following.setNestedScrollingEnabled(false);
        recycle_following.setAdapter(adapter1);

    }
}
