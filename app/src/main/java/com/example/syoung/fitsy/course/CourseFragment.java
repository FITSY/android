package com.example.syoung.fitsy.course;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.syoung.fitsy.common.CommonUtilities;

import com.example.syoung.fitsy.R;

/**
 * Created by syoung on 2015-06-28.
 */
public class CourseFragment extends Fragment{
    private View rootView;

    // 정보를 가져올 페이지 정보
    static final String CURRENT_URL = "localhost:8080/current_course.php"; // 현재 운동정보 관련 URL
    static final String ADD_URL = "localhost:8080/add_course.php"; // 검색/추가 운동 관련 URL
    static final String RECOMMEND_URL = "localhost:8080/recommend_course.php"; // 추천 운동정보 관련 URL
    static final int TAG_CURRENT = 1;
    static final int TAG_ADD = 2;
    static final int TAG_RECO = 3;

    public static ArrayList<HashMap<String, String>> current_course_list = null;
    public static ArrayList<HashMap<String, String>> add_course_list = null;
    public static ArrayList<HashMap<String, String>> recommend_course_list = null;

    public int course_image;
    public String course_name;

    ListView view_current_course_list;
    ListView view_add_course_list;
    ListView view_recommend_course_list;

    LazyAdapter current_course_adapter;
    LazyAdapter add_course_adapter;
    LazyAdapter recommend_course_adapter;

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

        CommonUtilities.ID = "test"; // 임시 아이디

        // ListView 등록
        view_current_course_list = (ListView) rootView.findViewById (R.id.current_course_list);
        view_add_course_list = (ListView) rootView.findViewById (R.id.add_course_list);
        view_recommend_course_list = (ListView) rootView.findViewById (R.id.recommend_course_list);

        // http request 요청
        MakeDynamicList current_list_request = new MakeDynamicList(CURRENT_URL, TAG_CURRENT);
        MakeDynamicList add_list_request = new MakeDynamicList(ADD_URL, TAG_ADD);
        MakeDynamicList recommend_list_request = new MakeDynamicList(RECOMMEND_URL, TAG_RECO);

        current_list_request.execute();
        add_list_request.execute();
        recommend_list_request.execute();

        // 각 리스트가 완성될 때까지 대기
        while(current_course_list == null || add_list_request == null || recommend_list_request == null){}

        // LazyAdapter -> 리스트 View 생성
        current_course_adapter = new LazyAdapter(getActivity(), current_course_list, R.layout.course_list_node, new String[] {}, new int[] {});
        add_course_adapter = new LazyAdapter(getActivity(), add_course_list, R.layout.course_list_node, new String[] {}, new int[] {});
        recommend_course_adapter = new LazyAdapter(getActivity(), recommend_course_list, R.layout.course_list_node, new String[] {}, new int[] {});

        view_current_course_list.setAdapter(current_course_adapter);
        view_add_course_list.setAdapter(add_course_adapter);
        view_recommend_course_list.setAdapter(recommend_course_adapter);

        // 각 노드들에 대해 클릭 리스너 설정
        view_current_course_list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


            }
        });

        return rootView;
    }
}