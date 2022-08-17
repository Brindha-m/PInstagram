package com.application.pinstagram.Notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.pinstagram.R;

import java.util.ArrayList;
import java.util.List;


public class NotificationFrag extends Fragment {

    RecyclerView notificationRV, rvnotification;
    ArrayList<NotificationModel1> list;
    ArrayList<NotificationReqModel> list1;
    Button confirm;

    public NotificationFrag() {
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

        NotificationAdapter adapter = new NotificationAdapter(list, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        notificationRV.setLayoutManager(layoutManager);
        notificationRV.setNestedScrollingEnabled(false);
        notificationRV.setAdapter(adapter);

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