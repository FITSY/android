package com.example.syoung.fitsy.main.server;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface FITSYService {
    @GET("/getUserMain.php")
    public void getResponse(Callback<List<UserCourse>> cb);
}
