package com.example.syoung.fitsy.history;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.syoung.fitsy.R;
import com.example.syoung.fitsy.common.ChangeCurrentCourse;
import com.example.syoung.fitsy.common.PopupFragment;
import com.example.syoung.fitsy.common.RowItem;
import com.example.syoung.fitsy.course.ScrollSelector;

import java.util.ArrayList;

/**
 * Created by syoung on 2015-06-28.
 */
public class HistoryFragment extends Fragment {
    private View rootView;

    private static final String TAG = "HistoryFragment";

    private static final int CHANGE_ALERT = 1; // 현재 코스가 변경된 상태인데 다른 창을 가려고 할 때 경고를 띄워주는 알림
    private static final int ADD_ALERT = 2; // add_course에서 현재 코스에 해당 운동을 추가할지 말지를 물어보는 알림
    private static final int HISTORY_ALERT = 3; // add_course에서 현재 코스에 해당 운동을 추가할지 말지를 물어보는 알림
    private static final int RECOMMEND_ALERT = 4; // add_course에서 현재 코스에 해당 운동을 추가할지 말지를 물어보는 알림

    static final int RECOMMEND = 1;
    static final int HISTORY = 2;

    private static HistoryFragment instance;
    private static ListView historyListView;

    private static HistoryCourseAdapter historyCourseAdapter;

    public static ProgressBar bar;
    public static LinearLayout backgrd;
    public static ArrayList<HistoryRowItem> history_array_list;
    private static Activity thisActivity;

    private static PopupFragment popupFragment;

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

        thisActivity = getActivity();
        history_array_list = new ArrayList<HistoryRowItem>();

        historyListView = (ListView) rootView.findViewById(R.id.history_vl);

        // custom progress circle 등록
        bar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        backgrd = (LinearLayout) rootView.findViewById(R.id.progress_layout);

        startConnection();

        return rootView;
    }

    public static void startConnection(){
        MakeHistoryList makeList = new MakeHistoryList(thisActivity);
        makeList.execute();
    }

    public static void makeChange(ArrayList<RowItem> items){
        popupFragment.dismiss();

        // TODO : 바뀐 현재 코스 내용 (ArrayList<RowItem> 형태)를 전송하는 클래스 완성
        ChangeCurrentCourse changeCurrentCourse = new ChangeCurrentCourse(thisActivity,items,HISTORY);
        changeCurrentCourse.execute();
    }

    public static void initialize(){
        history_array_list.clear();
    }

    public static void isChanged(){
        Toast toast = Toast.makeText(thisActivity, "적용 되었습니다", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER|Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    /**
     * 서버에서 current_list와 add_list 받아온 거를 Adapter에 적용시켜 주고, 각 아이템의 Listener 를 등록해 준다
     */
    public static void setOnClickListener() {

        historyCourseAdapter = new HistoryCourseAdapter(thisActivity, R.layout.horizontal_node, history_array_list);
        historyListView.setAdapter(historyCourseAdapter);

        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupFragment = new PopupFragment(HISTORY_ALERT,
                        history_array_list.get(position).getItems());
                popupFragment.show(thisActivity.getFragmentManager(), TAG);
            }
        });

    }
}
