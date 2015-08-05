package com.example.syoung.fitsy.course;

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;

import com.example.syoung.fitsy.R;
import com.example.syoung.fitsy.common.PopupFragment;
import com.example.syoung.fitsy.common.RowItem;

import java.util.ArrayList;

/**
 * Created by HyunJoo on 2015. 8. 5..
 */
public class ChangeNDelete extends DialogFragment {

    private static final String TAG = "ChangeNDelete";
    private View rootView;

    private static final int CHANGE_ALERT = 1; // 현재 코스가 변경된 상태인데 다른 창을 가려고 할 때 경고를 띄워주는 알림
    private static final int ADD_ALERT = 2; // add_course에서 현재 코스에 해당 운동을 추가할지 말지를 물어보는 알림
    private static final int HISTORY_ALERT = 3; // history_course를 현재 코스로 바꿀 것인지 말지를 물어보는 알림
    private static final int RECOMMEND_ALERT = 4; // recommend_course 현재 코스로 바꿀 것인지를 물어보는 알림
    private static final int DELETE_ITEM_ALERT = 5; // 운동 코스 수정에서 해당 아이템을 지울 것인지를 물어보는 알림

    private ImageButton cancle;
    private ImageButton save;

    private static ArrayList<RowItem> temp_array;

    private static Activity activity;
    private static DragSortListView draggableListView;
    private static DragAdapter lazyAdapter;

    private static PopupFragment popupFragment;

    public ChangeNDelete(){}

    public ChangeNDelete (ArrayList<RowItem> data, Activity activity){
        temp_array = new ArrayList<RowItem>();
        this.temp_array = data;
        this.activity = activity;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_change_n_delete, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));

        //temp_array = new ArrayList<RowItem>();
        //temp_array = items;

        cancle = (ImageButton) rootView.findViewById(R.id.btn_cancle);
        save = (ImageButton) rootView.findViewById(R.id.btn_save);

        draggableListView = (DragSortListView) rootView.findViewById(R.id.change_list_layout);
        setting();

        draggableListView.setDropListener(new DragSortListView.DropListener() {
            @Override
            public void drop(int from, int to) {
                doDropAct(from, to);
            }
        });

        draggableListView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        popupFragment = new PopupFragment(DELETE_ITEM_ALERT, temp_array.get(position));
                        popupFragment.show(getFragmentManager(), TAG);

                        return true;
                    }
                }
        );

        cancle.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        temp_array.clear();
                        CourseFragment.changeOrder(0, null);
                    }
                }
        );

        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CourseFragment.changeOrder(1, temp_array);
                    }
                }
        );

        return rootView;
    }

    public static void deleteItemDialog(RowItem temp_row_item){
        popupFragment = new PopupFragment(DELETE_ITEM_ALERT, temp_row_item);
        popupFragment.show(activity.getFragmentManager(), TAG);
    }

    public static void deleteItem(RowItem temp_row_item){
        temp_array.remove(temp_row_item);
        setting();
        popupFragment.dismiss();
    }

    private void doDropAct(int from, int to){
        RowItem temp_from = new RowItem();
        RowItem temp_to = new RowItem();
        RowItem temp = new RowItem();

        int deff = Math.abs(from - to);

        if (deff == 1) {
            temp_from = temp_array.get(from);
            temp_array.set(from, temp_array.get(to));
            temp_array.set(to, temp_from);
            // TODO : 바뀐 현재 운동 순서 (corder) 바꿔주기
        } else if (deff >= 2 && to > from) {
            temp_from = temp_array.get(from);
            temp_to = temp_array.get(to);
            for (int i = to-1; i >= from; i--) {
                temp = temp_array.get(i);
                temp_array.set(i, temp_to);
                temp_to = temp;
            }
            temp_array.set(to, temp_from);
        } else if (deff >= 2 && to < from) {
            temp_from = temp_array.get(from);
            temp_to = temp_array.get(to);
            for (int i = to+1; i <= from; i++) {
                temp = temp_array.get(i);
                temp_array.set(i, temp_to);
                temp_to = temp;
            }
            temp_array.set(to, temp_from);
        }

        setting();
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    private static void setting(){
        lazyAdapter = new DragAdapter(activity, R.layout.draggable_node, temp_array);
        draggableListView.setAdapter(lazyAdapter);
    }

}
