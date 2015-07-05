package com.example.syoung.fitsy.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.syoung.fitsy.R;

/**
 * Created by syoung on 2015-06-28.
 */
public class MainFragment extends Fragment {
    private View rootView;

    private static MainFragment instance;

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
        rootView = inflater.inflate(R.layout.exercise_main_layout, container, false);
        return rootView;
    }
}
