package com.example.syoung.fitsy.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.syoung.fitsy.R;

import java.util.List;

public class LazyAdapter extends ArrayAdapter<RowItem> {

    private List<RowItem> items;
    private LayoutInflater inflater;

    public LazyAdapter(Context context, int textViewResourceId,
                       List<RowItem> items) {
        super(context, textViewResourceId, items);
        this.items = items;
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = inflater.inflate(R.layout.course_list_node, null);
        }

        // 현재 position 의 내용을 View 로 작성하여 리턴
        RowItem item = (RowItem) items.get(position);

        if (item != null) {

            /*TextView course_name = (TextView) view.findViewById(R.id.course_name);
            course_name.setText(item.getImageName());*/

            ImageView course_image = (ImageView) view.findViewById(R.id.course_image);
            course_image.setImageResource(item.getImageId());

        }
        return view;
    }
}