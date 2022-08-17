package com.application.pinstagram.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PostDTO implements Serializable
{
	@SerializedName("userId")
	private String UserId;
	@SerializedName("name")
	private String name;
	@SerializedName("postUrl")
	private String postUrl;
	@SerializedName("caption")
	private String caption;
	@SerializedName("date")
	private String date;
	@SerializedName("hashtag")
	private String hashtag;



	public String getPostUrl() {
		return postUrl;
	}

	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PostDTO{" +
				"UserId='" + UserId + '\'' +
				", name='" + name + '\'' +
				", postUrl='" + postUrl + '\'' +
				", caption='" + caption + '\'' +
				", date='" + date + '\'' +
				", hashtag='" + hashtag + '\'' +
				'}';
	}
}