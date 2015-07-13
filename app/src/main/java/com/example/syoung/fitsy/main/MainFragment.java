package com.example.syoung.fitsy.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.syoung.fitsy.R;
import com.example.syoung.fitsy.main.adapter.MainCourseListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

public class MainFragment extends Fragment {
    private View rootView;

    private static MainFragment instance;
    @InjectView(R.id.mainCourseListView) ListView mainCourseView;

//    private List<String> courseData = new ArrayList<>();
//    private MainCourseListAdapter mainCourseListAdapter;

    private MainFragment() {

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
//        mainCourseListAdapter = new MainCourseListAdapter();
//        mainCourseListAdapter.setData(courseData);
        return rootView;
    }

    private void setMainCourseList() {
//        mainCourseListAdapter.notifyDataSetChanged();
    }

    @OnItemClick(R.id.mainCourseListView)
    void onItemClicked(int position) {
//        if(courseData.size() == position){
//            return;
//        }
//        String selectedCourse = courseData.get(position);
//        Toast.makeText(getActivity(), selectedCourse , Toast.LENGTH_SHORT).show();
    }
}
