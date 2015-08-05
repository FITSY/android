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
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import com.example.syoung.fitsy.R;
import com.example.syoung.fitsy.common.LazyAdapter;
import com.example.syoung.fitsy.common.RowItem;

import java.util.ArrayList;

/**
 * Created by HyunJoo on 2015. 8. 5..
 */
public class ChangeNDelete extends DialogFragment {

    private static final String TAG = "ChangeNDelete";
    private ArrayList<RowItem> items = new ArrayList<RowItem>();
    private View rootView;

    private ImageButton cancle;
    private ImageButton save;
    private ImageButton delete;

    ArrayList<RowItem> temp_array;

    Activity activity;
    DragSortListView draggableListView;
    LazyAdapter lazyAdapter;

    public ChangeNDelete(){}

    public ChangeNDelete (ArrayList<RowItem> data, Activity activity){
        this.items = data;
        this.activity = activity;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_change_n_delete, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));

        temp_array = new ArrayList<RowItem>();
        temp_array = items;

        cancle = (ImageButton) rootView.findViewById(R.id.btn_cancle);
        save = (ImageButton) rootView.findViewById(R.id.btn_save);
        delete = (ImageButton) rootView.findViewById(R.id.btn_delete);

        delete.setVisibility(View.GONE);

        draggableListView = (DragSortListView) rootView.findViewById(R.id.change_list_layout);
        setting();

        draggableListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }

        );

        draggableListView.setDropListener(new DragSortListView.DropListener() {
            @Override
            public void drop(int from, int to) {
                doDropAct(from, to);
            }
        });

        cancle.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CourseFragment.changeOrder(0);
                    }
                }
        );

        return rootView;
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

    private void setting(){
        lazyAdapter = new LazyAdapter(activity, R.layout.draggable_node, items);
        draggableListView.setAdapter(lazyAdapter);
    }

}
