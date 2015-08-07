package com.example.syoung.fitsy.statistics;

import com.example.syoung.fitsy.main.server.UserCourse;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by sub_HeeJin on 2015-08-07.
 */
public interface StatisticsInterface {

    @GET("/getExerciseRatio.php")
    public void getExerciseRatio(Callback<List<UserData>> cb);

}
