package com.example.syoung.fitsy.myinfo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by syoung on 2015-08-07.
 */
public interface MyInfoService {
    @GET("/getInbodyInfo.php")
    public void getInbodyInfo(Callback<List<MyInfoData>> cb);
}
