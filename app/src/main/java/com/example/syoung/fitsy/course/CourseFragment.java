package com.example.syoung.fitsy.course;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
// TODO : 길게 드래그 시 운동 순서변경 / 삭제 가능하게 하기
// TODO : recommend_course_list들에다가 운동 관련 정보들 모두 제대로 담기

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
    public static HorizontalListView elephant_recommend_course_view;
    public static HorizontalListView bye_fat_recommend_course_view;

    public static ArrayList<RowItem> current_array_list;
    public static ArrayList<RowItem> add_array_list;

    public static ArrayList<RowItem> pt_recommend_list;
    public static ArrayList<RowItem> elephant_recommend_list;
    public static ArrayList<RowItem> bye_fat_recommend_list;

    LazyAdapter current_course_adapter;
    LazyAdapter add_course_adapter;

    LazyAdapter pt_recommend_adapter;
    LazyAdapter elephant_recommend_adapter;
    LazyAdapter bye_fat_recommend_adapter;

    private Button open_button;

    public static int test_image_id;

    private static CourseFragment instance;
    private static Activity thisActivity;

    Action_Anim anim;
    SearchImageRID searchImageRID;
    public static ProgressBar bar;
    public static LinearLayout backgrd;

    private ImageButton pt_confirm;
    private ImageButton elephant_confirm;
    private ImageButton bye_fat_confirm;

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
        searchImageRID = new SearchImageRID(thisActivity);

        // ListView 등록
        current_course_view = (HorizontalListView) rootView.findViewById (R.id.current_course_list);
        add_course_view = (HorizontalListView) rootView.findViewById (R.id.add_course_list);

        // 추천 운동 코스들
        pt_recommend_course_view = (HorizontalListView) rootView.findViewById (R.id.pt_recommend_list);
        elephant_recommend_course_view = (HorizontalListView) rootView.findViewById (R.id.elephant_recommend_list);
        bye_fat_recommend_course_view = (HorizontalListView) rootView.findViewById (R.id.bye_fat_recommend_list);

        // 버튼 등록
        open_button = (Button) rootView.findViewById(R.id.open_add_course);
        pt_confirm = (ImageButton) rootView.findViewById(R.id.pt_confirm);
        elephant_confirm = (ImageButton) rootView.findViewById(R.id.elephant_confirm);
        bye_fat_confirm = (ImageButton) rootView.findViewById(R.id.bye_fat_confirm);

        // custom progress circle 등록
        bar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        backgrd = (LinearLayout) rootView.findViewById(R.id.progress_layout);

        // list 초기화
        current_array_list = new ArrayList<RowItem>();
        add_array_list = new ArrayList<RowItem>();

        pt_recommend_list = new ArrayList<RowItem>();
        elephant_recommend_list = new ArrayList<RowItem>();
        bye_fat_recommend_list = new ArrayList<RowItem>();

        pt_recommend_list.add(new RowItem(1, 10, 10, searchImageRID.getImageID("leg_press2")));
        pt_recommend_list.add(new RowItem(2, 10, 20, searchImageRID.getImageID("leg_extension2")));
        pt_recommend_list.add(new RowItem(3, 30, 20, searchImageRID.getImageID("let_pull_down2")));
        pt_recommend_list.add(new RowItem(4, 10, 20, searchImageRID.getImageID("pec_deck_flyes2")));
        pt_recommend_list.add(new RowItem(5, 10, 20, searchImageRID.getImageID("shoulder_press2")));
        pt_recommend_list.add(new RowItem(6, 1, 6, searchImageRID.getImageID("running2")));
        pt_recommend_list.add(new RowItem(7, 20, 10, searchImageRID.getImageID("cycle2")));
        pt_recommend_list.add(new RowItem(8, 10, 20, searchImageRID.getImageID("leg_curl2")));


        elephant_recommend_list.add(new RowItem(5, 10, 20, searchImageRID.getImageID("shoulder_press2")));
        elephant_recommend_list.add(new RowItem(6, 1, 6, searchImageRID.getImageID("running2")));
        elephant_recommend_list.add(new RowItem(3, 30, 20, searchImageRID.getImageID("let_pull_down2")));
        elephant_recommend_list.add(new RowItem(2, 10, 20, searchImageRID.getImageID("leg_extension2")));
        elephant_recommend_list.add(new RowItem(1, 10, 10, searchImageRID.getImageID("leg_press2")));
        elephant_recommend_list.add(new RowItem(4, 10, 20, searchImageRID.getImageID("pec_deck_flyes2")));
        elephant_recommend_list.add(new RowItem(7, 20, 10, searchImageRID.getImageID("cycle2")));


        bye_fat_recommend_list.add(new RowItem(6, 1, 6, searchImageRID.getImageID("running2")));
        bye_fat_recommend_list.add(new RowItem(2, 10, 20, searchImageRID.getImageID("leg_extension2")));
        bye_fat_recommend_list.add(new RowItem(6, 1, 6, searchImageRID.getImageID("running2")));
        bye_fat_recommend_list.add(new RowItem(4, 10, 20, searchImageRID.getImageID("pec_deck_flyes2")));
        bye_fat_recommend_list.add(new RowItem(5, 10, 20, searchImageRID.getImageID("shoulder_press2")));
        bye_fat_recommend_list.add(new RowItem(3, 30, 20, searchImageRID.getImageID("let_pull_down2")));
        bye_fat_recommend_list.add(new RowItem(1, 10, 10, searchImageRID.getImageID("leg_press2")));


        LazyAdapter pt_adapter = new LazyAdapter(thisActivity, R.layout.course_list_node, pt_recommend_list);
        LazyAdapter elepahnt_adapter = new LazyAdapter(thisActivity, R.layout.course_list_node, elephant_recommend_list);
        LazyAdapter bye_fat_adapter = new LazyAdapter(thisActivity, R.layout.course_list_node, bye_fat_recommend_list);

        pt_recommend_course_view.setAdapter(pt_adapter);
        elephant_recommend_course_view.setAdapter(elepahnt_adapter);
        bye_fat_recommend_course_view.setAdapter(bye_fat_adapter);

        // 버튼 리스터 등록
        open_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openButtonClick();
            }

        });

        pt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        elephant_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        bye_fat_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        MakeDynamicList makeList = new MakeDynamicList(getActivity());
        makeList.execute();

        return rootView;
    }

    private void openButtonClick(){

        if(add_course_view.isShown()){
            anim.slide_up(thisActivity, rootView.findViewById(R.id.add_course_open));
            anim.translate_to_up(thisActivity, rootView.findViewById(R.id.recommend_course_vertical_list));
            open_button.setText("운동 수정 열기 ∨");
            rootView.findViewById(R.id.add_course_open).setVisibility(View.GONE);
        }else{
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

        // 리스트 아이템을 터치 했을 떄 이벤트 발생
        current_course_view.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast toast = Toast.makeText(thisActivity, "currennt_course : " + current_array_list.get(position), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        });

        // 리스트 아이템을 길게 터치 했을 떄 이벤트 발생
        current_course_view.setOnItemLongClickListener(new OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast toast = Toast.makeText(thisActivity, "Long Clicked", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                // 터치 시 해당 아이템 이름 출력
                return true;
            }

        });

        add_course_view.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast toast = Toast.makeText(thisActivity, "add_course : " + add_array_list.get(position), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        });

    }

}