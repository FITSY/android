package com.example.syoung.fitsy.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.syoung.fitsy.R;
import com.example.syoung.fitsy.main.data.CourseItem;

import java.util.List;

public class ExerciseCourseListAdapter extends BaseAdapter {

    private List<CourseItem> data;
    private Context context;

    public ExerciseCourseListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CourseItem getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_main_course_list, null);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        CourseItem item = data.get(position);

        holder.setData(item);

        return convertView;
    }

    private class Holder {
        private ImageView image;
        private TextView name;

        private Holder(View parent) {
            image = (ImageView) parent.findViewById(R.id.item_main_course_image);
            name = (TextView) parent.findViewById(R.id.item_main_course_name);
        }

        private void setData(CourseItem data) {
            image.setImageResource(data.getImageId());
            name.setText(data.getImageName());
        }
    }

    public List<CourseItem> getData() {
        return data;
    }

    public void setData(List<CourseItem> data) {
        this.data = data;
    }
}
