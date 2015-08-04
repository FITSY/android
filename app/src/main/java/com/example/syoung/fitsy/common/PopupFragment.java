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
import com.example.syoung.fitsy.history.HistoryFragment;

import java.util.ArrayList;

/**
 * Created by HyunJoo on 2015. 8. 4..
 */
public class PopupFragment extends DialogFragment {
    private View rootView;

    private static final int CHANGE_ALERT = 1; // 현재 코스가 변경된 상태인데 다른 창을 가려고 할 때 경고를 띄워주는 알림
    private static final int ADD_ALERT = 2; // add_course에서 현재 코스에 해당 운동을 추가할지 말지를 물어보는 알림
    private static final int HISTORY_ALERT = 3; // add_course에서 현재 코스에 해당 운동을 추가할지 말지를 물어보는 알림
    private static final int RECOMMEND_ALERT = 4; // add_course에서 현재 코스에 해당 운동을 추가할지 말지를 물어보는 알림

    private static final int PT = 5;
    private static final int ELE = 6;
    private static final int FAT = 7;

    private ImageButton ok;
    private ImageButton no;
    private ImageButton cancle;
    private TextView question;

    RowItem data;

    private int which_recommend;
    private ArrayList<RowItem> items;

    private int which_alert;

    public PopupFragment(int which_alert) {
        this.which_alert = which_alert;
    }

    public PopupFragment(int which_alert, RowItem data) {
        this.which_alert = which_alert;
        this.data = data;
    }

    public PopupFragment(int which_alert, ArrayList<RowItem> items) {
        this.which_alert = which_alert;
        this.items = items;
    }

    public PopupFragment(int which_alert, int which_recommend) {
        this.which_alert = which_alert;
        this.which_recommend = which_recommend;
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

        switch (which_alert){
            case CHANGE_ALERT:
                question.setText("변경 사항 저장");
                usualChange();
                break;
            case ADD_ALERT:
                addChange();
                break;
            case HISTORY_ALERT:
                historyChange();
                break;
            case RECOMMEND_ALERT:
                recommendChange();
                break;
        }

        cancle.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          getDialog().dismiss();
                                      }}
        );

        return rootView;
    }

    private void historyChange(){
        ok.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      HistoryFragment.makeChange(items);
                                  }}
        );
        no.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      getDialog().dismiss();
                                  }
                              }
        );
    }

    private void recommendChange(){
        ok.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      CourseFragment.recommendSave(which_recommend);
                                  }}
        );
        no.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      getDialog().dismiss();
                                  }}
        );
    }

    private void addChange(){
        ok.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      RowItem temp = new RowItem(data.getId(),data.getCid(),data.getCpw(),
                                              data.getOtype(),data.getOpart(),data.getOoption1(),data.getOoption2(),data.getImageId(),data.getExerciseName());
                                      CourseFragment.isChanged = true;
                                      CourseFragment.addToCurrent(temp);
                                  }}
        );
        no.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      getDialog().dismiss();
                                  }}
        );
    }

    private void usualChange(){
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
    }

}
