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



// TODO : recommend_course_list에서 해당 코스의 이름을 클릭했을 시 http request
// TODO : 검색 기능 완성
// TODO : MakeDynamicList에서 로딩 애니메이션 추가
// TODO :


public class CourseFragment extends android.support.v4.app.Fragment{
    private static View rootView;
    static final String TAG = "CourseFragment";
    private final int exercise_number = 7;

    // 정보를 가져올 페이지 정보
    static final String CURRENT_URL = "http://192.168.0.21:8080/sgen_test/current_course.php"; // 현재 운동정보 관련 URL
    static final String ADD_URL = "http://192.168.0.21:8080/sgen_test/add_course.php"; // 검색/추가 운동 관련 URL
    static final String RECOMMEND_URL = "http://192.168.0.21:8080/sgen_test/recommend_course.php"; // 추천 운동정보 관련 URL

    public static HorizontalListView current_course_view;
    public static HorizontalListView add_course_view;
    public static HorizontalListView pt_recommend_course_view;

    public static ArrayList<RowItem> current_array_list;
    public static ArrayList<RowItem> add_array_list;
    public static ArrayList<RowItem> pt_recommend_list;

    LazyAdapter current_course_adapter;
    LazyAdapter add_course_adapter;
    LazyAdapter pt_recommend_adapter;

    private Button open_button;

    public static int test_image_id;

    private static CourseFragment instance;
    private static Activity thisActivity;

    Action_Anim anim;

    public CourseFragment() {

    }

    public static CourseFragment getInstance() {
        if (instance == null) {
            instance = new CourseFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_course, container, false);

        thisActivity = getActivity();

        anim = new Action_Anim();

        // ListView 등록
        current_course_view = (HorizontalListView) rootView.findViewById (R.id.current_course_list);
        add_course_view = (HorizontalListView) rootView.findViewById (R.id.add_course_list);
        pt_recommend_course_view = (HorizontalListView) rootView.findViewById (R.id.pt_recommend_list);

        // 버튼 등록
        open_button = (Button) rootView.findViewById(R.id.open_add_course);

        // list 초기화
        current_array_list = new ArrayList<RowItem>();
        add_array_list = new ArrayList<RowItem>();
        pt_recommend_list = new ArrayList<RowItem>();

        for(int i = 0; i < exercise_number; i++){
            RowItem item = new RowItem();
        }

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
            //slide_up(thisActivity,add_course_view);
            anim.slide_up(thisActivity, rootView.findViewById(R.id.add_course_open));
            anim.translate_to_up(thisActivity, rootView.findViewById(R.id.recommend_course_vertical_list));
            open_button.setText("운동 수정 열기 ∨");
            rootView.findViewById(R.id.add_course_open).setVisibility(View.GONE);
        }else{
            //slide_down(thisActivity,add_course_view);
            anim.slide_down(thisActivity,rootView.findViewById(R.id.add_course_open));
            anim.translate_to_down(thisActivity,rootView.findViewById(R.id.recommend_course_vertical_list));
            open_button.setText("운동 수정 닫기 ∧");
            rootView.findViewById(R.id.add_course_open).setVisibility(View.VISIBLE);
        }
    }

    public static void setOnClickLisenter() {

        LazyAdapter current_adapter = new LazyAdapter(thisActivity, R.layout.course_list_node, current_array_list);
        LazyAdapter add_adapter = new LazyAdapter(thisActivity, R.layout.course_list_node, add_array_list);

        current_course_view.setAdapter(current_adapter);
        add_course_view.setAdapter(add_adapter);

        current_course_view.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast toast = Toast.makeText(thisActivity, "currennt_course : " + current_array_list.get(position).getImageName(), Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
//                toast.show();
            }
        });
        add_course_view.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast toast = Toast.makeText(thisActivity, "add_course : " + add_array_list.get(position).getImageName(), Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
//                toast.show();
            }
        });

    }

}