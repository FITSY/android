package com.example.syoung.fitsy;

/**
 * Created by HyunJoo on 2015. 8. 6..
 */


import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.app.Activity;
import android.content.Intent;

public class Welcome extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2500;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.welcome);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(Welcome.this, LoginActivity.class);
                startActivity(i);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}