package com.example.syoung.fitsy.main.server;

import retrofit.Callback;
import retrofit.http.GET;

public interface FITSYService {
    @GET("/")
    public void getResponse(Callback<Login> cb);
}
