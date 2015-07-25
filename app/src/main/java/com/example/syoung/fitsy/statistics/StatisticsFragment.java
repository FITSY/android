package com.example.syoung.fitsy.statistics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.syoung.fitsy.R;

/**
 * Created by syoung on 2015-06-28.
 */
public class StatisticsFragment extends Fragment {
    private View rootView;

    private static StatisticsFragment instance;

    public StatisticsFragment() {

    }

    public static StatisticsFragment getInstance() {
        if (instance == null) {
            instance = new StatisticsFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_statistics, container, false);
        return rootView;
    }
}
