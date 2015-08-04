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
        userCourse.setOtype(2);
        userCourse.setEname("leg_curl");
        userCourse.setOdid("04526E52863680");
        userCourse.setOoption1(8);
        userCourse.setOoption2(10);
        List<String> method = new ArrayList<String>();
        method.add("앞으로");
        method.add("뒤로");
        method.add("끝");
        userCourse.setEintro(method);
        userCourseList.add(userCourse);

        UserCourse userCourse2 = new UserCourse();
        userCourse2.setOtype(2);
        userCourse2.setEname("leg_press");
        userCourse2.setOdid("0000000");
        userCourse2.setOoption1(3);
        userCourse2.setOoption2(4);

        userCourseList.add(userCourse2);
        userCourseList.add(userCourse2);
        userCourseList.add(userCourse2);
        userCourseList.add(userCourse2);
        userCourseList.add(userCourse2);
        userCourseList.add(userCourse2);

        mainIntent.putExtra("userCourseList", (Serializable) userCourseList);
        startActivity(mainIntent);
        finish();
    }
}
