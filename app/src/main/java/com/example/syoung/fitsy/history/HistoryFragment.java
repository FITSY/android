package com.example.syoung.fitsy.history;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.syoung.fitsy.R;

/**
 * Created by syoung on 2015-06-28.
 */
public class HistoryFragment extends Fragment {
    private View rootView;

    private static HistoryFragment instance;

    public HistoryFragment() {

    }

    public static HistoryFragment getInstance() {
        if (instance == null) {
            instance = new HistoryFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_history, container, false);
        return rootView;
    }
}
