package com.example.syoung.fitsy.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.syoung.fitsy.R;
import com.example.syoung.fitsy.common.HorizontalListView;
import com.example.syoung.fitsy.main.adapter.ExerciseCourseListAdapter;
import com.example.syoung.fitsy.main.data.NowCourse;
import com.example.syoung.fitsy.main.server.UserCourse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class MainFragment extends Fragment {

    private View rootView;
    private static MainFragment instance;

    @Bind(R.id.startBtn) ImageButton startBtn;
    @Bind(R.id.main_exercise_course_list) HorizontalListView exerciseCourseHorizontalListView;

    private ExerciseCourseListAdapter exerciseCourseListAdapter;
    private List<NowCourse> exerciseCourseList;
    private List<NowCourse> nowExerciseCourseList;

    public MainFragment() {

    }

    public static MainFragment getInstance() {
        if (instance == null) {
            instance = new MainFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_fitsy_main, container, false);
        ButterKnife.bind(this, rootView);

        List<UserCourse> userCourseList = (ArrayList<UserCourse>) getActivity().getIntent().getSerializableExtra("userCourseList");
        setCourseList(userCourseList);
        setExerciseCourseHorizontalListView();
        return rootView;
    }

    private void setCourseList(List<UserCourse> userCourseList) {
        exerciseCourseList = new ArrayList<NowCourse>();
        nowExerciseCourseList = new ArrayList<NowCourse>();
        for(UserCourse userCourse : userCourseList){
            NowCourse nowCourse = new NowCourse();
            nowCourse.setUserCourse(userCourse);
            nowCourse.setCheck(false);
            nowCourse.setResult(0);
            nowCourse.setImageId(getActivity().getResources().getIdentifier(userCourse.getEname(), "drawable", getActivity().getPackageName()));
            exerciseCourseList.add(nowCourse);
        }
    }

    private void setExerciseCourseHorizontalListView() {
        exerciseCourseListAdapter = new ExerciseCourseListAdapter(getActivity());
        exerciseCourseListAdapter.setData(exerciseCourseList);
        exerciseCourseHorizontalListView.setAdapter(exerciseCourseListAdapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }

    @OnClick(R.id.startBtn)
    public void exerciseStart() {
        Intent exerciseIntent = new Intent(this.getActivity(), NFCReadActivity.class);
        exerciseIntent.putExtra("userCourseList", (Serializable) nowExerciseCourseList);
        startActivity(exerciseIntent);
    }

    @OnItemClick(R.id.main_exercise_course_list)
    void OnItemClicked(int position){
        //TODO : 선택되면 이미지 바뀌게 하기 (opacity or color)
        nowExerciseCourseList.add(exerciseCourseList.get(position));
    }
}
