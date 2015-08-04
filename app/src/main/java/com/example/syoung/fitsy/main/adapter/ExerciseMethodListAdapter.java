package com.example.syoung.fitsy.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.syoung.fitsy.R;

import java.util.List;

public class ExerciseMethodListAdapter extends BaseAdapter {

    private List<String> data;
    private Context context;

    public ExerciseMethodListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public String getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_exercise_method_list, null);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        String item = data.get(position);

        holder.setData(item);

        return convertView;
    }

    private class Holder {
        private TextView text;

        private Holder(View parent) {
            text = (TextView) parent.findViewById(R.id.exercise_method_item);
        }

        private void setData(String data) {
            text.setText(data);
        }
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
