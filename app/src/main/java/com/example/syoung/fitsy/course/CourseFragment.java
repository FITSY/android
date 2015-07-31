package com.example.syoung.fitsy.course;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.syoung.fitsy.common.*;
import com.example.syoung.fitsy.common.HorizontalListView;
import com.example.syoung.fitsy.R;

/**
 * Created by HyunJoo on 2015. 7. 27..
 */
public class CourseFragment extends android.support.v4.app.Fragment{
    private static View rootView;
    static final String TAG = "CourseFragment";

    // 정보를 가져올 페이지 정보
    static final String CURRENT_URL = "http://192.168.0.21:8080/sgen_test/current_course.php"; // 현재 운동정보 관련 URL
    static final String ADD_URL = "http://192.168.0.21:8080/sgen_test/add_course.php"; // 검색/추가 운동 관련 URL
    static final String RECOMMEND_URL = "http://192.168.0.21:8080/sgen_test/recommend_course.php"; // 추천 운동정보 관련 URL

    public static HorizontalListView current_course_view;
    public static HorizontalListView add_course_view;
    public static HorizontalListView recommend_course_view;

    public static ArrayList<RowItem> current_array_list;
    public static ArrayList<RowItem> add_array_list;
    public static ArrayList<RowItem> recommend_array_list;

    LazyAdapter current_course_adapter;
    LazyAdapter add_course_adapter;

    private Button open_button;

    public static int test_image_id;

    private static CourseFragment instance;
    private static Activity thisActivity;

    public CourseFragment() {

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

        thisActivity = getActivity();

        // ListView 등록
        current_course_view = (HorizontalListView) rootView.findViewById (R.id.current_course_list);
        add_course_view = (HorizontalListView) rootView.findViewById (R.id.add_course_list);
        recommend_course_view = (HorizontalListView) rootView.findViewById (R.id.recommend_course_list);

        // 버튼 등록
        open_button = (Button) rootView.findViewById(R.id.open_add_course);

        // list 초기화
        current_array_list = new ArrayList<RowItem>();
        add_array_list = new ArrayList<RowItem>();
        recommend_array_list = new ArrayList<RowItem>();

        open_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                buttonClick();
            }

        });

        MakeDynamicList makeList = new MakeDynamicList(getActivity());
        makeList.execute();

        return rootView;
    }

    private void buttonClick(){

        if(add_course_view.isShown()){
            open_button.setText("운동 수정 열기 ∨");
            add_course_view.setVisibility(View.GONE);
        }else{
            open_button.setText("운동 수정 닫기 ∧");
            add_course_view.setVisibility(View.VISIBLE);
        }
    }

    public static void setOnClickLisenter(){

        LazyAdapter current_adapter = new LazyAdapter(thisActivity,R.layout.course_list_node, current_array_list);
        LazyAdapter add_adapter = new LazyAdapter(thisActivity, R.layout.course_list_node, add_array_list);

        current_course_view.setAdapter(current_adapter);
        add_course_view.setAdapter(add_adapter);
        recommend_course_view.setAdapter(current_adapter);

        current_course_view.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast toast = Toast.makeText(thisActivity, "currennt_course : " + current_array_list.get(position).getImageName(), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        });
        add_course_view.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast toast = Toast.makeText(thisActivity, "add_course : " + add_array_list.get(position).getImageName(), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        });

    }

}