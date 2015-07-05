package com.example.syoung.fitsy.course;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.syoung.fitsy.R;

/**
 * Created by syoung on 2015-06-28.
 */
public class CourseFragment extends Fragment{
    private View rootView;

    private static CourseFragment instance;

    private CourseFragment() {

    }

    public static CourseFragment getInstance() {
        if (instance == null) {
            instance = new CourseFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_course, container, false);
        return rootView;
    }
}
