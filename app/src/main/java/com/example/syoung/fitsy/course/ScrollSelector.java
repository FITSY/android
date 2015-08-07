package com.example.syoung.fitsy.course;

import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.syoung.fitsy.R;
import com.example.syoung.fitsy.common.RowItem;

/**
 * Created by HyunJoo on 2015. 8. 4..
 */
public class ScrollSelector extends DialogFragment{
    private View rootView;

    private static ScrollSelector instance;
    private RowItem item;
    private ImageButton change_confirm;
    private ImageButton cancle;

    private NumberPicker picker1;
    private NumberPicker picker2;
    private TextView unit;
    private int position;

    private final int AEROBIC = 1;
    private final int ANAEROBIC = 2;

    public ScrollSelector(RowItem item, int position) {
        this.item = item;
        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_scrollview, container, false);
        getDialog().setTitle("운동 횟수/시간 수정");
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));

        change_confirm = (ImageButton) rootView.findViewById(R.id.change_confirm);

        ImageView exercise_image = (ImageView) rootView.findViewById(R.id.exercise_image);
        change_confirm = (ImageButton) rootView.findViewById(R.id.change_confirm);
        cancle = (ImageButton) rootView.findViewById(R.id.change_cancle);

        picker1 = (NumberPicker) rootView.findViewById(R.id.numPick1);
        picker2 = (NumberPicker) rootView.findViewById(R.id.numPick2);
        unit = (TextView) rootView.findViewById(R.id.unit);

        picker1.setMinValue(0);
        picker1.setMaxValue(9);

        picker2.setMinValue(0);
        picker2.setMaxValue(9);

        picker1.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        picker2.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        /**
         * - ooption1 : 무게(무산소) / 운동시간(유산소)
         * - ooption2 : 횟수(무산소) / 속도(유산소)
         */

        if(item.getEtype() == AEROBIC){
            int temp = item.getOoption2();
            Log.e("ScollSelector", "유산소 : " + temp);
            Log.e("ScollSelector", "운동 순서 : " + item.getCorder());

            if(temp >= 10){
                int temp2 = temp/10;
                int temp3 = temp%10;
                picker1.setValue(temp2);
                picker2.setValue(temp3);
            }else{
                picker1.setValue(0);
                picker2.setValue(temp);
            }

            unit.setText("분");
        }else{
            int temp = item.getOoption2();
            Log.e("ScrollSelector", "무산소 : " + temp);
            Log.e("ScollSelector", "운동 순서 : " + item.getCorder());

            if(temp >= 10){
                int temp2 = temp/10;
                int temp3 = temp%10;
                picker1.setValue(temp2);
                picker2.setValue(temp3);
            }else{
                picker1.setValue(0);
                picker2.setValue(temp);
            }

            unit.setText("회");

        }

        exercise_image.setImageResource(item.getImageId());
        change_confirm.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  clickConfirm();
                                              }
                                          }
        );
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return rootView;
    }

    private void clickConfirm(){
        int result = 0;

        if(item.getEtype() == AEROBIC){
            // 운동 시간
            int temp = picker1.getValue();

            if(temp > 0){
                int temp2 = picker2.getValue();
                result = temp*10 + temp2;
            }else{
                int temp2 = picker2.getValue();
                result = temp2;
            }

            CourseFragment.current_array_list.get(position).setOoption2(result);
        }else{
            // 횟수
            int temp = picker1.getValue();

            if(temp > 0){
                int temp2 = picker2.getValue();
                result = temp*10 + temp2;
            }else{
                int temp2 = picker2.getValue();
                result = temp2;
            }

            CourseFragment.current_array_list.get(position).setOoption2(result);
        }

        CourseFragment.change_save.setVisibility(View.VISIBLE);
        getDialog().dismiss();
    }

    public void onStop(){
        super.onStop();
    }
}