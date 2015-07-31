package com.example.syoung.fitsy.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.syoung.fitsy.R;
import com.example.syoung.fitsy.common.HorizontalListView;
import com.example.syoung.fitsy.main.adapter.ExerciseCourseListAdapter;
import com.example.syoung.fitsy.main.data.CourseItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExerciseActivity extends Activity {

    @Bind(R.id.exerciseFinishBtn) ImageButton exerciseFinishBtn;
    @Bind(R.id.main_now_exercise_course_list) HorizontalListView nowExerciseCourseHorizontalListView;

    private ExerciseCourseListAdapter nowExerciseCourseListAdapter;
    private List<CourseItem> nowExerciseCourseItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitsy_main_exercise);
        ButterKnife.bind(this);

        setExerciseCourseList();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    private void setExerciseCourseList() {

        nowExerciseCourseItemList = new ArrayList<CourseItem>();
        String exerciseName = "leg_press";
        int exerciseId = this.getResources().getIdentifier(exerciseName, "drawable", this.getPackageName());
        CourseItem rowItem = new CourseItem(exerciseId, exerciseName);
        nowExerciseCourseItemList.add(rowItem);
        nowExerciseCourseItemList.add(rowItem);
        nowExerciseCourseItemList.add(rowItem);
        nowExerciseCourseItemList.add(rowItem);
        nowExerciseCourseItemList.add(rowItem);
        nowExerciseCourseItemList.add(rowItem);
        nowExerciseCourseItemList.add(rowItem);
        nowExerciseCourseItemList.add(rowItem);

        nowExerciseCourseListAdapter = new ExerciseCourseListAdapter(this);
        nowExerciseCourseListAdapter.setData(nowExerciseCourseItemList);
        nowExerciseCourseHorizontalListView.setAdapter(nowExerciseCourseListAdapter);
    }

    @OnClick(R.id.exerciseFinishBtn)
    public void finishExercise() {
        finish();
    }
}
