package com.application.pinstagram.Model;

import com.google.gson.annotations.SerializedName;

public class ProfileDTO {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String Username;
    @SerializedName("bio")
    private String bio;
    @SerializedName("image")
    private String profile_image;

    public String getUserId() {
        return id;
    }

    public void setUserId(String userId) {
        id = userId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    @Override
    public String toString() {
        return "ProfileDTO{" +
                "UserId='" + id + '\'' +
                ", Username='" + Username + '\'' +
                ", bio='" + bio + '\'' +
                ", profile_image='" + profile_image + '\'' +
                '}';
    }
}
