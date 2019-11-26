package com.sg.template.network;

import com.sg.template.components.sample.Post;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetworkService {

    @GET("/posts/{post_id}")
    Call<Post> getPost(@Path("post_id") int postId);

}
