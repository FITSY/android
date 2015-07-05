package com.example.syoung.fitsy.myinfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.syoung.fitsy.R;

/**
 * Created by syoung on 2015-06-28.
 */
public class MyInformationFragment extends Fragment {
    private View rootView;

    private static MyInformationFragment instance;

    private MyInformationFragment() {

    }

    public static MyInformationFragment getInstance() {
        if (instance == null) {
            instance = new MyInformationFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_my_information, container, false);
        return rootView;
    }
}
