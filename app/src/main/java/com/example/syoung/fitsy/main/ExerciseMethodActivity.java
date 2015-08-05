package com.example.syoung.fitsy.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.syoung.fitsy.R;
import com.example.syoung.fitsy.main.adapter.ExerciseMethodListAdapter;
import com.example.syoung.fitsy.main.data.NowCourse;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExerciseMethodActivity extends Activity {

    @Bind(R.id.exercise_method_btn_off) ImageButton exerciseMethodOffBtn;
    @Bind(R.id.exercise_method_list_view) ListView exerciseMethodListView;
    @Bind(R.id.exercise_name) TextView exerciseName;

    private ExerciseMethodListAdapter exerciseMethodListAdapter;
    private List<String> exerciseMethodItemList;
    private NowCourse nowExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitsy_main_exercise_method);
        ButterKnife.bind(this);

        nowExercise = (NowCourse) this.getIntent().getSerializableExtra("nowExercise");
        exerciseMethodItemList = nowExercise.getUserCourse().getEintro();
        exerciseName.setText(nowExercise.getUserCourse().getEname().replace("_"," ").toUpperCase());
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
