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

    private Activity activity;
    private DragSortListView draggableListView;
    private LazyAdapter lazyAdapter;

    public ChangeNDelete(){}

    public ChangeNDelete (Activity activity){
        this.items = CourseFragment.current_array_list;
        this.activity = activity;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_change_n_delete, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));

        temp_array = new ArrayList<RowItem>();
        temp_array.addAll(items);

        cancle = (ImageButton) rootView.findViewById(R.id.btn_cancle);
        save = (ImageButton) rootView.findViewById(R.id.btn_save);
        delete = (ImageButton) rootView.findViewById(R.id.btn_delete);

        delete.setVisibility(View.GONE);

        draggableListView = (DragSortListView) rootView.findViewById(R.id.change_list_layout);
        setting();

        cancle.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        items = new ArrayList<RowItem>();
                        items.addAll(temp_array);
                        getDialog().dismiss();
                    }
                }
        );

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
                RowItem temp_from = new RowItem();
                RowItem temp_to = new RowItem();
                RowItem temp = new RowItem();

                int deff = Math.abs(from - to);

                if (deff == 1) {
                    temp_from = items.get(from);
                    items.set(from, items.get(to));
                    items.set(to, temp_from);
                } else if (deff >= 2 && to > from) {
                    temp_from = items.get(from);
                    temp_to = items.get(to);
                    for (int i = to-1; i >= from; i--) {
                        temp = items.get(i);
                        items.set(i, temp_to);
                        temp_to = temp;
                    }
                    items.set(to, temp_from);
                } else if (deff >= 2 && to < from) {
                    temp_from = items.get(from);
                    temp_to = items.get(to);
                    for (int i = to+1; i <= from; i++) {
                        temp = items.get(i);
                        items.set(i, temp_to);
                        temp_to = temp;
                    }
                    items.set(to, temp_from);
                }

                setting();
            }
        });

        return rootView;
    }

    private void setting(){
        lazyAdapter = new LazyAdapter(activity, R.layout.draggable_node, items);
        draggableListView.setAdapter(lazyAdapter);
    }

    private void initialize(){
        items = null;
    }

}
