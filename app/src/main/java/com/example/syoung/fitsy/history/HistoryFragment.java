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

    private static final int CHANGE_ALERT = 1; // 현재 코스가 변경된 상태인데 다른 창을 가려고 할 때 경고를 띄워주는 알림
    private static final int ADD_ALERT = 2; // add_course에서 현재 코스에 해당 운동을 추가할지 말지를 물어보는 알림
    private static final int TRANSFER_ALERT = 3; // 변경된 current_course를 전송할 때 전송할지 말지 물어보는 알림

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
