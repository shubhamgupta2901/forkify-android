package com.sg.template.components.sample;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Post {

    @SerializedName("userId")
    @Expose
    private int userId;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;

    public int getUserId() {
    return userId;
    }

    public void setUserId(int userId) {
    this.userId = userId;
    }

    public int getId() {
    return id;
    }

    public void setId(int id) {
    this.id = id;
    }

    public String getTitle() {
    return title;
    }

    public void setTitle(String title) {
    this.title = title;
    }

    public String getBody() {
    return body;
    }

    public void setBody(String body) {
    this.body = body;
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}