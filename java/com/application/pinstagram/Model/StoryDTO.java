package com.application.pinstagram.Model;

import com.google.gson.annotations.SerializedName;

public class StoryDTO {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("userId")
    private String userId;
    @SerializedName("url")
    private String url;
    @SerializedName("createdTime")
    private String createdTime;
    @SerializedName("expiryTime")
    private String expiryTime;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StoryDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", url='" + url + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", expiryTime='" + expiryTime + '\'' +
                '}';
    }
}
