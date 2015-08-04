package com.example.syoung.fitsy.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.syoung.fitsy.R;
import com.example.syoung.fitsy.common.HorizontalListView;
import com.example.syoung.fitsy.main.adapter.ExerciseCourseListAdapter;
import com.example.syoung.fitsy.main.data.ExerciseData;
import com.example.syoung.fitsy.main.data.NowCourse;

import java.io.Serializable;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class ExerciseActivity extends Activity {

    @Bind(R.id.exerciseFinishBtn)
    ImageButton exerciseFinishBtn;
    @Bind(R.id.exercise_method_btn)
    ImageButton exerciseMethodBtn;
    @Bind(R.id.main_now_exercise_course_list)
    HorizontalListView nowExerciseCourseHorizontalListView;
    @Bind(R.id.exercise_name)
    TextView exerciseName;

    @Bind(R.id.exercise_option_one_key)
    TextView optionOneKey;
    @Bind(R.id.exercise_option_two_key)
    TextView optionTwoKey;
    @Bind(R.id.exercise_option_one_value)
    TextView optionOneValue;
    @Bind(R.id.exercise_option_two_value)
    TextView optionTwoValue;
    @Bind(R.id.nowNumber)
    TextView nowNumber;

    private ExerciseCourseListAdapter nowExerciseCourseListAdapter;
    private ExerciseData exerciseData;
    private List<NowCourse> nowExerciseCourseItemList;

    //TODO : 운동을 한 정보는 NowCourse check 에 했는지 안 했는지에 대한 정보와 NowCourse에 result에 무산소 운동 횟수와 유산소운동 시간을 입력
    //TODO : 한 운동은 list 에서 색칠되고 클릭 안되게 하기(처음 화면 킬 때NowCourse가 check인 것들 setting 해주기)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitsy_main_exercise);
        ButterKnife.bind(this);
        exerciseData = (ExerciseData) this.getIntent().getSerializableExtra("exerciseData");
        nowExerciseCourseItemList = exerciseData.getNowCourseList();

        for (NowCourse nowCourse : nowExerciseCourseItemList) {
            if (nowCourse.getUserCourse().getOdid().equals(exerciseData.getTagId())) {
                exerciseName.setText(nowCourse.getUserCourse().getEname());
                if (nowCourse.getUserCourse().getOtype() == 1) {
                    //유산소
                    optionOneKey.setText("speed");
                    optionTwoKey.setText("running Time");
                    optionOneValue.setText(String.valueOf(nowCourse.getUserCourse().getOoption1()) + " km/h");
                    optionTwoValue.setText(String.valueOf(nowCourse.getUserCourse().getOoption2()) + " min");
                } else {
                    //무산소
                    optionOneKey.setText("weight");
                    optionTwoKey.setText("count");
                    optionOneValue.setText(String.valueOf(nowCourse.getUserCourse().getOoption1()) + "kg");
                    optionTwoValue.setText(String.valueOf(nowCourse.getUserCourse().getOoption2()) + "count");
                }
                //TODO : nowNumber에 숫자가 올라감 (블루투스 연결한 숫자)
                nowNumber.setText(String.valueOf(0));
            }
        }

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

    @OnClick(R.id.exercise_method_btn)
    public void showExerciseMethod() {
        Intent exerciseIntent = new Intent(this, ExerciseMethodActivity.class);
        startActivity(exerciseIntent);
    }

    @OnItemClick(R.id.main_now_exercise_course_list)
    void OnItemClicked(int position) {
        Intent exerciseIntent = new Intent(this, NFCReadActivity.class);
        exerciseIntent.putExtra("nowExerciseCourseList", (Serializable) nowExerciseCourseItemList);
        startActivity(exerciseIntent);
        finish();
    }
}
