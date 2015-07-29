package com.example.syoung.fitsy.main.server;

import retrofit.Callback;
import retrofit.http.GET;

public interface FITSYService {
    @GET("/sample_user.html")
    public void getResponse(Callback<Login> cb);
}
