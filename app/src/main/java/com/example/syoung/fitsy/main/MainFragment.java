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
import butterknife.OnItemClick;

//TODO : onstartActivityResult 함수 만들어서 nowExerciseCourseItemList 초기화 시키기
//TODO : NowCourse클래스 만든거 적용하기
public class MainFragment extends Fragment {

    private View rootView;
    private static MainFragment instance;

    @Bind(R.id.startBtn) ImageButton startBtn;
    @Bind(R.id.main_exercise_course_list) HorizontalListView exerciseCourseHorizontalListView;

    private ExerciseCourseListAdapter exerciseCourseListAdapter;
    private List<UserCourse> exerciseCourseItemList;
    private List<UserCourse> nowExerciseCourseItemList;

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
        nowExerciseCourseItemList = new ArrayList<UserCourse>();
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
        exerciseIntent.putExtra("userCourseList", (Serializable) nowExerciseCourseItemList);
        startActivity(exerciseIntent);
    }

    @OnItemClick(R.id.main_exercise_course_list)
    void OnItemClicked(int position){
        //TODO : 선택되면 이미지 바뀌게 하기 (opacity or color)
        nowExerciseCourseItemList.add(exerciseCourseItemList.get(position));
    }
}
