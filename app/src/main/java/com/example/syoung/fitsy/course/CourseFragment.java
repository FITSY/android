package com.example.syoung.fitsy.course;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
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
    private final int exercise_number = 7;

    static final int RECOMMEND = 1;

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

    private static LazyAdapter current_adapter;
    private static LazyAdapter add_adapter;

    LazyAdapter pt_recommend_adapter;
    LazyAdapter elephant_recommend_adapter;
    LazyAdapter bye_fat_recommend_adapter;

    private Button open_button;

    public static int test_image_id;

    private static CourseFragment instance;
    private static Activity thisActivity;

    Action_Anim anim;
    SearchImageRID searchImageRID;
    ChangeCurrentCourse changeCurrentCourse;
    private static SearchConverter searchConverter;

    public static ProgressBar bar;
    public static LinearLayout backgrd;

    private ImageButton pt_confirm;
    private ImageButton elephant_confirm;
    private ImageButton bye_fat_confirm;
    private EditText search_input;
    private ImageButton search_button;
    private InputMethodManager keyboard;
    private TextView search_part;

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

        // 액티비티 저장
        thisActivity = getActivity();

        // 클래스 초기화
        anim = new Action_Anim();
        searchImageRID = new SearchImageRID(thisActivity);
        searchConverter = new SearchConverter(thisActivity);

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
        search_button = (ImageButton) rootView.findViewById(R.id.search_button);

        // 텍스트뷰 등록
        search_part = (TextView) rootView.findViewById(R.id.search_part);

        // 키보드 등록
        keyboard = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);

        // EditText 등록
        search_input = (EditText) rootView.findViewById(R.id.search_input);

        // custom progress circle 등록
        bar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        backgrd = (LinearLayout) rootView.findViewById(R.id.progress_layout);

        // list 초기화
        current_array_list = new ArrayList<RowItem>();
        add_array_list = new ArrayList<RowItem>();

        pt_recommend_list = new ArrayList<RowItem>();
        elephant_recommend_list = new ArrayList<RowItem>();
        bye_fat_recommend_list = new ArrayList<RowItem>();

        // 추천 운동 코스 리스트들에 아이템 등록하기
        pt_recommend_list.add(new RowItem(1, 10, 10, searchImageRID.getImageID("leg_press2"),"leg_press"));
        pt_recommend_list.add(new RowItem(2, 10, 20, searchImageRID.getImageID("leg_extension2"),"leg_extension"));
        pt_recommend_list.add(new RowItem(3, 30, 20, searchImageRID.getImageID("let_pull_down2"),"let_pull_down"));
        pt_recommend_list.add(new RowItem(4, 10, 20, searchImageRID.getImageID("pec_deck_flyes2"),"pec_deck_flyes"));
        pt_recommend_list.add(new RowItem(5, 10, 20, searchImageRID.getImageID("shoulder_press2"),"shoulder_press"));
        pt_recommend_list.add(new RowItem(6, 1, 6, searchImageRID.getImageID("running2"),"running"));
        pt_recommend_list.add(new RowItem(7, 20, 10, searchImageRID.getImageID("cycle2"),"cycle"));
        pt_recommend_list.add(new RowItem(8, 10, 20, searchImageRID.getImageID("leg_curl2"),"leg_curl"));


        elephant_recommend_list.add(new RowItem(5, 10, 20, searchImageRID.getImageID("shoulder_press2"),"shoulder_press"));
        elephant_recommend_list.add(new RowItem(6, 1, 6, searchImageRID.getImageID("running2"),"running"));
        elephant_recommend_list.add(new RowItem(3, 30, 20, searchImageRID.getImageID("let_pull_down2"),"let_pull_down"));
        elephant_recommend_list.add(new RowItem(2, 10, 20, searchImageRID.getImageID("leg_extension2"),"leg_extension"));
        elephant_recommend_list.add(new RowItem(1, 10, 10, searchImageRID.getImageID("leg_press2"),"leg_press"));
        elephant_recommend_list.add(new RowItem(4, 10, 20, searchImageRID.getImageID("pec_deck_flyes2"),"pec_deck_flyes"));
        elephant_recommend_list.add(new RowItem(7, 20, 10, searchImageRID.getImageID("cycle2"),"cycle"));


        bye_fat_recommend_list.add(new RowItem(6, 1, 6, searchImageRID.getImageID("running2"),"running"));
        bye_fat_recommend_list.add(new RowItem(2, 10, 20, searchImageRID.getImageID("leg_extension2"),"leg_extension"));
        bye_fat_recommend_list.add(new RowItem(6, 1, 6, searchImageRID.getImageID("running2"),"running"));
        bye_fat_recommend_list.add(new RowItem(4, 10, 20, searchImageRID.getImageID("pec_deck_flyes2"),"pec_deck_flyes"));
        bye_fat_recommend_list.add(new RowItem(5, 10, 20, searchImageRID.getImageID("shoulder_press2"),"shoulder_press"));
        bye_fat_recommend_list.add(new RowItem(3, 30, 20, searchImageRID.getImageID("let_pull_down2"),"let_pull_down"));
        bye_fat_recommend_list.add(new RowItem(1, 10, 10, searchImageRID.getImageID("leg_press2"),"leg_press"));


        // 추천 운동 코스 리스트를 Adapter에 등록하기
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
        search_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                searchButtonClick();
            }

        });

        pt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeCurrentCourse = new ChangeCurrentCourse(getActivity(), pt_recommend_list, RECOMMEND);
            }
        });
        elephant_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeCurrentCourse = new ChangeCurrentCourse(getActivity(), elephant_recommend_list, RECOMMEND);
            }
        });
        bye_fat_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeCurrentCourse = new ChangeCurrentCourse(getActivity(), bye_fat_recommend_list, RECOMMEND);
            }
        });

        // 검색 입력창 이벤트
        search_input.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        search_input.setInputType(InputType.TYPE_CLASS_TEXT);
        search_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        // 엔터 눌렀을 시에 검색되게 함
                        searchButtonClick();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

        // 리스트 아이템들을 받아오는 서버 통신 시작
        startConnection();

        return rootView;
    }

    public static void startConnection(){
        MakeCurrentList makeList = new MakeCurrentList(thisActivity);
        makeList.execute();
    }

    /**
     * 검색 버튼 클릭했을 시
     */
    private void searchButtonClick(){

        ArrayList<RowItem> temp_add_course_list = new ArrayList<RowItem>();

        // 키보드 감추기
        keyboard.hideSoftInputFromWindow(search_input.getWindowToken(), 0);

        // 검색어를 searchConverter로 보내서 검색 시작. 결과값을 temp_add_course_list로 받는다.
        temp_add_course_list = searchConverter.getSearchResult(search_input.getText().toString());

        // 받은 값을 add_adapter에 추가해 준다.
        if(temp_add_course_list != null) {
            // EditText 내용을 제목으로 만들기
            search_part.setText(search_input.getText().toString());

            add_array_list = temp_add_course_list;
            add_adapter = new LazyAdapter(thisActivity, R.layout.course_list_node, add_array_list);
            add_course_view.setAdapter(add_adapter);

            // TODO : 아이템 속성 (시간/분) 수정 및 현재 코스에 추가 이벤트 시작
            // 아이템 리스너 등록
            add_course_view.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // 터치 시 해당 아이템 이름 출력
                    Toast toast = Toast.makeText(thisActivity, "add_course : " + add_array_list.get(position), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
            });

        }else{
            search_part.setText("검색어가 잘못 되었습니다");
        }

        // EditText 초기화
        search_input.setText(null);
    }

    /**
     * 운동수정 열기/닫기 버튼 클릭했을 시
     */
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

    /**
     * 서버에서 current_list와 add_list 받아온 거를 Adapter에 적용시켜 주고, 각 아이템의 Listener 를 등록해 준다
     */
    public static void setOnClickListener() {

        current_adapter = new LazyAdapter(thisActivity, R.layout.course_list_node, current_array_list);
        add_adapter = new LazyAdapter(thisActivity, R.layout.course_list_node, add_array_list);

        current_course_view.setAdapter(current_adapter);
        add_course_view.setAdapter(add_adapter);

        // 처음 받은 전체 운동 코스 리스트를 searchConverter에 저장해 둔다.
       searchConverter.setArrayList(add_array_list);

        // TODO : 아이템 순서 변경 이벤트 시작
        // 리스트 아이템을 터치 했을 떄 이벤트 발생
        current_course_view.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast toast = Toast.makeText(thisActivity, "currennt_course : " + current_array_list.get(position), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        });

        // TODO : 아이템 속성 (시간/분) 수정 및 현재 코스에 추가 이벤트 시작
        // 리스트 아이템을 길게 터치 했을 떄 이벤트 발생
        current_course_view.setOnItemLongClickListener(new OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // 터치 시 해당 아이템 이름 출력
                Toast toast = Toast.makeText(thisActivity, "Long Clicked", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                return true;
            }

        });

        add_course_view.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 터치 시 해당 아이템 이름 출력
                Toast toast = Toast.makeText(thisActivity, "add_course : " + add_array_list.get(position), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        });

    }

}