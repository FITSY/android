package com.example.syoung.fitsy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.syoung.fitsy.main.data.NowCourse;
import com.example.syoung.fitsy.main.server.FITSYService;
import com.example.syoung.fitsy.main.server.UserCourse;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class LoginActivity extends Activity {

    @Bind(R.id.loginBtn) ImageButton loginBtn;

    RestAdapter restAdapter;
    FITSYService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.loginBtn)
    public void login() {

        final Intent mainIntent = new Intent(this, MainActivity.class);
        Gson gson = new Gson();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://ebsud89.iptime.org:8022")
                .setConverter(new GsonConverter(gson))
                .build();

        FITSYService service = restAdapter.create(FITSYService.class);

//        service.getResponse(new Callback<List<UserCourse>>() {
//            @Override
//            public void success(List<UserCourse> userCourseList, Response response) {
//                Log.e("success", userCourseList.size() + "");
//                mainIntent.putExtra("userCourseList", (Serializable) userCourseList);
//                startActivity(mainIntent);
//                finish();
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Log.e("fail", "");
//            }
//        });

        List<UserCourse> userCourseList = new ArrayList<UserCourse>();
        UserCourse userCourse = new UserCourse();
        userCourse.setEname("leg_curl");
        userCourse.setCid("1");
        userCourse.setCorder(1);
        userCourse.setEintro("1");
        userCourse.setId("1");
        userCourse.setOdid("1");
        userCourse.setOoption1(1);

        userCourseList.add(userCourse);
        userCourseList.add(userCourse);
        userCourseList.add(userCourse);
        userCourseList.add(userCourse);
        userCourseList.add(userCourse);
        userCourseList.add(userCourse);
        userCourseList.add(userCourse);
        userCourseList.add(userCourse);
        userCourseList.add(userCourse);


        mainIntent.putExtra("userCourseList", (Serializable) userCourseList);
        startActivity(mainIntent);
        finish();
    }
}
