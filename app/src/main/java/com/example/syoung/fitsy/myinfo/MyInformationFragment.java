package com.example.syoung.fitsy.myinfo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.syoung.fitsy.R;

/**
 * Created by syoung on 2015-06-28.
 */
public class MyInformationFragment extends Fragment {
    private View rootView;
    private ImageView img;
    TextView tv1,tv2,tv3;
    private Button btn1,btn2;
    private static MyInformationFragment instance;

    public MyInformationFragment() {
    }

    public static MyInformationFragment getInstance() {
        if (instance == null) {
            instance = new MyInformationFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_my_information, container, false);
        img = (ImageView)rootView.findViewById(R.id.imageView);
        tv1=(TextView)rootView.findViewById(R.id.text2);
        tv2=(TextView)rootView.findViewById(R.id.text3);
        tv3=(TextView)rootView.findViewById(R.id.text4);
        tv1.setText("이름 : 이상훈");
        tv2.setText("키: 180cm");
        tv3.setText("몸무게: 71kg");
        return rootView;
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.button1:
                btn1.setBackgroundColor(Color.BLUE);
                btn1.setTextColor(Color.WHITE);
                btn2.setBackgroundColor(Color.WHITE);
                btn2.setTextColor(Color.BLACK);
                break;
            case R.id.button2:
                btn1.setBackgroundColor(Color.WHITE);
                btn1.setTextColor(Color.BLACK);
                btn2.setBackgroundColor(Color.BLUE);
                btn2.setTextColor(Color.WHITE);
                break;
            default:
                break;
        }
    }
}
