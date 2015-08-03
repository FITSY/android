package com.example.syoung.fitsy.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.syoung.fitsy.R;
import com.example.syoung.fitsy.main.data.NowCourse;

import java.util.List;

public class ExerciseCourseListAdapter extends BaseAdapter {

    private List<NowCourse> data;
    private Context context;

    public ExerciseCourseListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public NowCourse getItem(int position) {
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

        NowCourse item = data.get(position);

        holder.setData(item);

        return convertView;
    }

    private class Holder {
        private ImageView image;

        private Holder(View parent) {
            image = (ImageView) parent.findViewById(R.id.item_main_course_image);
        }

        private void setData(NowCourse data) {
            image.setImageResource(data.getImageId());
        }
    }

    public List<NowCourse> getData() {
        return data;
    }

    public void setData(List<NowCourse> data) {
        this.data = data;
    }
}
