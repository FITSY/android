package com.example.syoung.fitsy.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.syoung.fitsy.R;

import java.util.List;

public class MainCourseListAdapter extends BaseAdapter {

    private List<String> data;
    private Context context;

    public MainCourseListAdapter(Context context){
        this.context = context;
    }

    public void setData(List<String> data){
        this.data = data;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
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
        String value = data.get(position);
        holder.setData(value);

        return convertView;
    }

    private class Holder {
        private TextView value;

        private Holder(View parent) {
            value = (TextView) parent.findViewById(R.id.item_main_course_list_value);
        }

        private void setData(String data) {
            value.setText(data);
        }
    }
}
