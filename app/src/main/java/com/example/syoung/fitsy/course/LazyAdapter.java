package com.example.syoung.fitsy.course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.syoung.fitsy.R;

public class LazyAdapter extends SimpleAdapter {
    private Context mContext;
    public LayoutInflater inflater = null;
    private final String packageName = "com.example.syoung.fitsy.course";

    static final String TAG_EXERCISE_NAME = "exercise_name";
    private static int imageId;
    private static String exercise_name;

    ImageButton course_image;
    TextView course_name;

    public LazyAdapter(Context context,
                           List<? extends Map<String, ?>> data, int resource, String[] from,
                           int[] to) {
        super(context, data, resource, from, to);
        mContext = context;
        inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null) {
            vi = inflater.inflate(R.layout.course_list_node, null);

            course_image = (ImageButton) vi.findViewById(R.id.course_image);
            course_name = (TextView) vi.findViewById(R.id.course_name);
        }

        HashMap<String, String> data = (HashMap<String, String>) getItem(position);

        exercise_name = data.get(TAG_EXERCISE_NAME);
        imageId = vi.getResources().getIdentifier(exercise_name, "drawable", packageName);

        course_name.setText(exercise_name);
        course_image.setImageResource(imageId);

        return vi;
    }

}
