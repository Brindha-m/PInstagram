package com.application.pinstagram.Notification;

public class NotificationReqModel {

    int  profile;
    String username;


    public NotificationReqModel(int profile, String username) {
        this.profile = profile;
        this.username = username;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }
}
