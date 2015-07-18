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

    private static final int REQUEST_ENABLE_BT = 0;
    private BluetoothAdapter mBluetoothAdapter;
    @Bind(R.id.bluetoothList) ListView bluetoothListView;
//    private List<BluetoothDevice> bluetoothDeviceData = new ArrayList<>();
    private BluetoothListAdapter adapter;
    private ProgressBar bluetoothProgressBar;

    private List<String> bluetoothDeviceData = new ArrayList<String>();

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
        setBluetooth();
    }

    private void setBluetooth() {

        adapter = new BluetoothListAdapter(this.getActivity());
        bluetoothDeviceData.add("nam");
        bluetoothDeviceData.add("ba");
        adapter.setData(bluetoothDeviceData);
        bluetoothListView.setAdapter(adapter);

//        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
//        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
//        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
//        getActivity().registerReceiver(mReceiver, filter);

//        bluetoothProgressBar = (ProgressBar) LayoutInflater.from(getActivity()).inflate(R.layout.progressbar, null);
//        bluetoothListView.addFooterView(bluetoothProgressBar);

//        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//        if (mBluetoothAdapter == null) {
//            Toast.makeText(getActivity(), "This device did not support bluetooth", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (!mBluetoothAdapter.isEnabled()) {
//            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
//        } else {
//            setBluetoothList();
//        }
    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case REQUEST_ENABLE_BT:
//                setBluetoothList();
//                break;
//        }
//    }

//    private void setBluetoothList() {
//        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
//        for (BluetoothDevice device : pairedDevices) {
//            bluetoothDeviceData.add(device);
//        }
//        adapter.notifyDataSetChanged();
//        mBluetoothAdapter.startDiscovery();
//    }
//
//    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            Log.e("action", action);
//            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
//                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//                bluetoothDeviceData.add(device);
//                adapter.notifyDataSetChanged();
//            } else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
//                bluetoothListView.removeFooterView(bluetoothProgressBar);
//            }
//        }
//    };

    @OnItemClick(R.id.bluetoothList)
    void onItemClicked(int position) {
        if(bluetoothDeviceData.size() == position){
            return;
        }
//        BluetoothDevice selectedDevice = bluetoothDeviceData.get(position);
//        startConnect(selectedDevice);
    }

//    private void startConnect(BluetoothDevice selectedDevice) {
//        ConnectThread connectThread = new ConnectThread(selectedDevice);
//        connectThread.start();
//    }
}
