package com.example.syoung.fitsy.course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.syoung.fitsy.R;
import com.example.syoung.fitsy.common.RowItem;

import java.util.List;

/**
 * Created by HyunJoo on 2015. 8. 6..
 */
public class DragAdapter extends ArrayAdapter<RowItem> {

    private List<RowItem> items;
    private LayoutInflater inflater;

    public DragAdapter(Context context, int textViewResourceId,
                       List<RowItem> items) {
        super(context, textViewResourceId, items);
        this.items = items;
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = inflater.inflate(R.layout.draggable_node, null);
        }

        // 현재 position 의 내용을 View 로 작성하여 리턴
        final RowItem item = (RowItem) items.get(position);

        if (item != null) {

            ImageView course_image = (ImageView) view.findViewById(R.id.handler);
            ImageButton delete_button = (ImageButton) view.findViewById(R.id.btn_delete);

            course_image.setImageResource(item.getImageId());
            delete_button.setImageResource(R.drawable.delete_item);
            delete_button.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ChangeNDelete.deleteItemDialog(item);
                        }
                    }
            );

        }
        return view;
    }
}
