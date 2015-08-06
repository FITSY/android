package com.example.syoung.fitsy.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syoung.fitsy.R;
import com.example.syoung.fitsy.common.HorizontalListView;
import com.example.syoung.fitsy.main.adapter.ExerciseCourseListAdapter;
import com.example.syoung.fitsy.main.data.ExerciseData;
import com.example.syoung.fitsy.main.data.NowCourse;

import java.io.Serializable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

//TODO : 운동중 운동 된거는 이미지 파란색으로 하고 선택하면 이미 한 운동입니다 토스트 띄우기
public class ExerciseActivity extends Activity {

    @Bind(R.id.exerciseFinishBtn) ImageButton exerciseFinishBtn;
    @Bind(R.id.exercise_method_btn) ImageButton exerciseMethodBtn;
    @Bind(R.id.main_now_exercise_course_list) HorizontalListView nowExerciseCourseHorizontalListView;
    @Bind(R.id.exercise_name) TextView exerciseName;

    @Bind(R.id.exercise_option_one_key) TextView optionOneKey;
    @Bind(R.id.exercise_option_two_key) TextView optionTwoKey;
    @Bind(R.id.exercise_option_one_value) TextView optionOneValue;
    @Bind(R.id.exercise_option_two_value) TextView optionTwoValue;
    @Bind(R.id.nowNumber1) TextView nowNumber1;
    @Bind(R.id.numDivider) TextView numDivider;
    @Bind(R.id.nowNumber2) TextView nowNumber2;

    private ExerciseCourseListAdapter nowExerciseCourseListAdapter;
    private ExerciseData exerciseData;
    private List<NowCourse> nowExerciseCourseItemList;
    private NowCourse nowExercise;
    private int exerciseType = 1;
    private int mainTime = 0;
    private int min = 0;
    private int sec = 0;
    private int boundMin = 0;
    private Timer mTimer;
    private int nowExerciseIndex = 0;

    //TODO : 운동을 한 정보는 NowCourse check 에 했는지 안 했는지에 대한 정보와 NowCourse에 result에 무산소 운동 횟수와 유산소운동 시간을 입력
    //TODO : 한 운동은 list 에서 색칠되고 클릭 안되게 하기(처음 화면 킬 때NowCourse가 check인 것들 setting 해주기)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitsy_main_exercise);
        ButterKnife.bind(this);
        exerciseData = (ExerciseData) this.getIntent().getSerializableExtra("exerciseData");
        nowExerciseCourseItemList = exerciseData.getNowCourseList();
        setViewComponent();
        setNowExerciseCourseHorizontalListView();

        if(exerciseType == 2){
            mTimer = new Timer();
            TimerTask task = new TimerTask(){
                public void run(){
                    mainTime++;
                    min = mainTime/60;
                    sec = mainTime % 60;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            nowNumber1.setText(String.valueOf(min));
                            nowNumber2.setText(String.valueOf(sec));
                        }
                    });
                    //TODO : sec => min으로 바꾸기
                    if(sec == boundMin){
                        intentNFCReadActivity();
                        return;
                    }
                }
            };
            mTimer.schedule(task, 1000, 1000);
        }else if(exerciseType == 1){
            //TODO : 무산소
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    nowNumber1.setText(String.valueOf(min));
                }
            });
        }
    }

    private void setViewComponent() {
        int i = 0;
        for (NowCourse nowCourse : nowExerciseCourseItemList) {
            if (nowCourse.getUserCourse().getOdid() != null && nowCourse.getUserCourse().getOdid().equals(exerciseData.getTagId())) {
                nowExercise = nowCourse;
                nowExerciseIndex = i;
                exerciseName.setText(nowCourse.getUserCourse().getEname().replace("_", " ").toUpperCase());
                if (nowCourse.getUserCourse().getOtype() == 1) {
                    //무산소
                    exerciseType = 1;
                    optionOneKey.setText("weight");
                    optionTwoKey.setText("count");
                    optionOneValue.setText(String.valueOf(nowCourse.getUserCourse().getOoption1()) + "kg");
                    optionTwoValue.setText(String.valueOf(nowCourse.getUserCourse().getOoption2()) + "count");
                    nowNumber1.setText(String.valueOf(0));
                    numDivider.setText("/");
                    nowNumber2.setText(String.valueOf(nowCourse.getUserCourse().getOoption2()));
                } else {
                    //유산소
                    exerciseType = 2;
                    optionOneKey.setText("speed");
                    optionTwoKey.setText("running Time");
                    optionOneValue.setText(String.valueOf(nowCourse.getUserCourse().getOoption1()) + " km/h");
                    boundMin = nowCourse.getUserCourse().getOoption2();
                    optionTwoValue.setText(String.valueOf(boundMin) + " min");
                    nowNumber1.setText("0");
                    numDivider.setText(":");
                    nowNumber2.setText("0");
                }
                return;
            }
            i++;
        }
        Toast.makeText(this, "운동코스에 없는 NFC ID 입니다.", Toast.LENGTH_SHORT).show();
        intentNFCReadActivity();
    }

    private void intentNFCReadActivity() {
        if(exerciseType == 2){
            //TODO : 운동 했다고 하기
            nowExerciseCourseItemList.get(nowExerciseIndex).setImageId(this.getResources().getIdentifier("camera", "drawable", this.getPackageName()));
            nowExerciseCourseItemList.get(nowExerciseIndex).setCheck(true);
            nowExerciseCourseItemList.get(nowExerciseIndex).setResult(min);
            mTimer.cancel();
        }
        Intent exerciseIntent = new Intent(this, NFCReadActivity.class);
        exerciseIntent.putExtra("nowExerciseCourseList", (Serializable) nowExerciseCourseItemList);
        startActivity(exerciseIntent);
        finish();
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
        exerciseIntent.putExtra("nowExercise", (Serializable) nowExercise);
        startActivity(exerciseIntent);
    }

    @OnItemClick(R.id.main_now_exercise_course_list)
    void OnItemClicked(int position) {
        intentNFCReadActivity();
    }
}
