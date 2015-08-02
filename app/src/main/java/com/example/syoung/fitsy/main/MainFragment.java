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
import com.example.syoung.fitsy.main.server.UserCourse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends Fragment {

    private View rootView;
    private static MainFragment instance;

    @Bind(R.id.startBtn) ImageButton startBtn;
    @Bind(R.id.main_exercise_course_list) HorizontalListView exerciseCourseHorizontalListView;

    private ExerciseCourseListAdapter exerciseCourseListAdapter;
    private List<UserCourse> exerciseCourseItemList;

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
        exerciseCourseItemList = (ArrayList<UserCourse>) getActivity().getIntent().getSerializableExtra("userCourseList");
        setExerciseCourseList();
        return rootView;
    }

    private void setExerciseCourseList() {

        exerciseCourseListAdapter = new ExerciseCourseListAdapter(getActivity());
        exerciseCourseListAdapter.setData(exerciseCourseItemList);
        exerciseCourseHorizontalListView.setAdapter(exerciseCourseListAdapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }

    @OnClick(R.id.startBtn)
    public void exerciseStart() {
        Intent exerciseIntent = new Intent(this.getActivity(), NFCReadActivity.class);
        exerciseIntent.putExtra("userCourseList", (Serializable) exerciseCourseItemList);
        startActivity(exerciseIntent);
    }
}
