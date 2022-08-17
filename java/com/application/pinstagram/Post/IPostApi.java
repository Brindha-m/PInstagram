package com.application.pinstagram.Post;

import com.application.pinstagram.Model.PostDTO;
import com.application.pinstagram.Model.ProfileDTO;
import com.application.pinstagram.Model.SecondSignUpDTO;
import com.application.pinstagram.Model.StoryDTO;
import com.application.pinstagram.Notification.NotificationModel1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IPostApi {

    @POST("post/add-post")
    Call<PostDTO> addPost(@Body PostDTO postDTO);

    @POST("story/add-story")
    Call<StoryDTO> addStory(@Body StoryDTO storyDTO);

    @GET("/post")
    Call<List<PostDTO>> getPosts();

    @GET("/inapp/get/{userId}")
    Call<List<NotificationModel1>> getNotification(@Path("userId")String id);


    @GET("/story")
    Call<List<StoryDTO>> getStories();

    @POST("/user/prof")
    Call<ProfileDTO> getProfile(@Body ProfileDTO profileDTO);

    @GET("/user/getUser/{email}")
    Call<SecondSignUpDTO> getUserId(@Path("email")String email);

}
