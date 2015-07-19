package com.example.syoung.fitsy.main;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.syoung.fitsy.R;
import com.example.syoung.fitsy.main.adapter.BluetoothListAdapter;
import com.example.syoung.fitsy.main.bluetooth.ConnectThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

//TODO : Sun - 메뉴바 버튼 눌렀을 때 AlertDialog 호출, AlertDialog BluetoothList 추가
//TODO : Sun - MainFragment 에 horizontal Scroll View 추가
//TODO : Sun - 블루투스 값 제대로 받기


public class MainFragment extends Fragment {

    private View rootView;
    private static MainFragment instance;

    private MainFragment() {

    }

    public static MainFragment getInstance() {
        if (instance == null) {
            instance = new MainFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_fitsy_main, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
//        setBluetooth();
    }

}
