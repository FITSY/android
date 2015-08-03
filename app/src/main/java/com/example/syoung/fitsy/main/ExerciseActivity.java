package com.example.syoung.fitsy.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.syoung.fitsy.R;
import com.example.syoung.fitsy.common.HorizontalListView;
import com.example.syoung.fitsy.main.adapter.ExerciseCourseListAdapter;
import com.example.syoung.fitsy.main.data.NowCourse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

//TODO : ExerciseActivity 와 NFCFeadActivity footer에 layout inflater하기
public class ExerciseActivity extends Activity {

    @Bind(R.id.exerciseFinishBtn) ImageButton exerciseFinishBtn;
    @Bind(R.id.main_now_exercise_course_list) HorizontalListView nowExerciseCourseHorizontalListView;

    private ExerciseCourseListAdapter nowExerciseCourseListAdapter;
    private List<NowCourse> nowExerciseCourseItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitsy_main_exercise);
        ButterKnife.bind(this);
        nowExerciseCourseItemList = (ArrayList<NowCourse>) this.getIntent().getSerializableExtra("userCourseList");
        setNowExerciseCourseHorizontalListView();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    private void setNowExerciseCourseHorizontalListView() {
        nowExerciseCourseListAdapter = new ExerciseCourseListAdapter(this);
        nowExerciseCourseListAdapter.setData(nowExerciseCourseItemList);
        nowExerciseCourseHorizontalListView.setAdapter(nowExerciseCourseListAdapter);
    }

    @OnClick(R.id.exerciseFinishBtn)
    public void finishExercise() {
        finish();
    }

    @OnItemClick(R.id.main_now_exercise_course_list)
    void OnItemClicked(int position){
        Intent exerciseIntent = new Intent(this, NFCReadActivity.class);
        exerciseIntent.putExtra("userCourseList", (Serializable) nowExerciseCourseItemList);
        startActivity(exerciseIntent);
        finish();
    }
}
