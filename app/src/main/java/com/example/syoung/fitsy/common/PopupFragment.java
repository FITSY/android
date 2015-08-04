package com.example.syoung.fitsy.common;

import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.syoung.fitsy.R;
import com.example.syoung.fitsy.course.CourseFragment;

/**
 * Created by HyunJoo on 2015. 8. 4..
 */
public class PopupFragment extends DialogFragment {
    private View rootView;

    private static final int CHANGE_ALERT = 1; // 현재 코스가 변경된 상태인데 다른 창을 가려고 할 때 경고를 띄워주는 알림
    private static final int ADD_ALERT = 2; // add_course에서 현재 코스에 해당 운동을 추가할지 말지를 물어보는 알림
    private static final int TRANSFER_ALERT = 3; // 변경된 current_course를 전송할 때 전송할지 말지 물어보는 알림

    private ImageButton ok;
    private ImageButton no;
    private ImageButton cancle;
    private TextView question;

    private int which_alert;

    public PopupFragment(int which_alert) {
        this.which_alert = which_alert;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_popup_change_alert, container, false);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));

        cancle = (ImageButton) rootView.findViewById(R.id.cancle_btn);
        ok = (ImageButton) rootView.findViewById(R.id.btn_yes);
        no = (ImageButton) rootView.findViewById(R.id.btn_no);

        question = (TextView) rootView.findViewById(R.id.question);

        cancle.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  getDialog().dismiss();
              }}
        );
        ok.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  CourseFragment.isChanged = true;
                  CourseFragment.changeSave();
              }}
        );
        no.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  CourseFragment.isChanged = false;
                  CourseFragment.changeSave();
              }}
        );

        return rootView;
    }

    private void clickConfirm(){
    }


}
