package com.example.syoung.fitsy.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.syoung.fitsy.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExerciseMethodActivity extends Activity {

    @Bind(R.id.exercise_method_btn_off) ImageButton exerciseMethodOffBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitsy_main_exercise_method);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.exercise_method_btn_off)
    public void offExerciseMethod() {
        finish();
    }
}
