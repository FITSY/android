package com.example.syoung.fitsy.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.syoung.fitsy.R;
import com.example.syoung.fitsy.common.HorizontalListView;
import com.example.syoung.fitsy.common.LazyAdapter;
import com.example.syoung.fitsy.common.RowItem;

import java.util.List;

/**
 * Created by HyunJoo on 2015. 8. 5..
 */
public class HistoryCourseAdapter extends ArrayAdapter<HistoryRowItem> {

    private List<HistoryRowItem> items;
    private LayoutInflater inflater;

    public HistoryCourseAdapter(Context context, int textViewResourceId,
                                List<HistoryRowItem> items) {
        super(context, textViewResourceId, items);
        this.items = items;
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = inflater.inflate(R.layout.horizontal_node, null);
        }

        // 현재 position 의 내용을 View 로 작성하여 리턴
        HistoryRowItem item = (HistoryRowItem) items.get(position);

        if (item != null) {

            TextView date = (TextView) view.findViewById(R.id.date);
            HorizontalListView horizontalListView = (HorizontalListView) view.findViewById(R.id.history_course_list);

            LazyAdapter history_course_adapter = new LazyAdapter(getContext(),R.layout.course_list_node,item.getItems());

            horizontalListView.setAdapter(history_course_adapter);
            date.setText(item.getDate());

        }
        return view;
    }
}
