package com.example.syoung.fitsy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.syoung.fitsy.main.server.FITSYService;
import com.example.syoung.fitsy.main.server.Login;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class LoginActivity extends Activity {

    @Bind(R.id.loginBtn) Button loginBtn;

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

        Gson gson = new Gson();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://192.168.200.198:8080")
                .setConverter(new GsonConverter(gson))
                .build();

//        RestAdapter restAdapter = new RestAdapter.Builder()
//                .setEndpoint("http://192.168.200.198:8080/")
//                .build();

        FITSYService service = restAdapter.create(FITSYService.class);

        service.getResponse(new Callback<Login>() {
            @Override
            public void success(Login login, Response response) {
                Log.e("success", login.getId() + ", " + login.getPassword());
            }
            @Override
            public void failure(RetrofitError error) {
                Log.e("failure", error.getMessage());
            }
        });


        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
