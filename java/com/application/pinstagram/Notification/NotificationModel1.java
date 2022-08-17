package com.application.pinstagram.Notification;

import com.google.gson.annotations.SerializedName;

public class NotificationModel1 {
    @SerializedName("id")
    Integer id;
    @SerializedName("userId")
    String userId;
    @SerializedName("socialMediaId")
    Integer socialMediaId;
    @SerializedName("content")
    String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getSocialMediaId() {
        return socialMediaId;
    }

    public void setSocialMediaId(Integer socialMediaId) {
        this.socialMediaId = socialMediaId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NotificationModel1{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", socialMediaId=" + socialMediaId +
                ", content='" + content + '\'' +
                '}';
    }
}
