package com.example.syoung.fitsy.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.syoung.fitsy.R;
import com.example.syoung.fitsy.main.adapter.ExerciseCourseListAdapter;
import com.example.syoung.fitsy.main.adapter.ExerciseMethodListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//TODO : ExerciseActiviy에서 정보 받아와서 setting 하기
public class ExerciseMethodActivity extends Activity {

    @Bind(R.id.exercise_method_btn_off) ImageButton exerciseMethodOffBtn;
    @Bind(R.id.exercise_method_list_view) ListView exerciseMethodListView;

    private ExerciseMethodListAdapter exerciseMethodListAdapter;
    private List<String> exerciseMethodItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitsy_main_exercise_method);
        ButterKnife.bind(this);

        exerciseMethodItemList = new ArrayList<String>();
        exerciseMethodItemList.add("앞으로");
        exerciseMethodItemList.add("앞으로");
        exerciseMethodItemList.add("앞으로");
        exerciseMethodItemList.add("앞으로");

        setNowExerciseCourseHorizontalListView();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @OnClick(R.id.exercise_method_btn_off)
    public void offExerciseMethod() {
        finish();
    }

    private void setNowExerciseCourseHorizontalListView() {
        exerciseMethodListAdapter = new ExerciseMethodListAdapter(this);
        exerciseMethodListAdapter.setData(exerciseMethodItemList);
        exerciseMethodListView.setAdapter(exerciseMethodListAdapter);
    }
}
