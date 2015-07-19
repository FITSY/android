package com.example.syoung.fitsy.main.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.syoung.fitsy.R;
import com.example.syoung.fitsy.main.adapter.BluetoothListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.OnItemClick;

public class ConnectBluetooth {

    private static final int REQUEST_ENABLE_BT = 0;
    private Context context;
    private BluetoothAdapter mBluetoothAdapter;
    @Bind(R.id.bluetoothList) ListView bluetoothListView;
    private List<BluetoothDevice> bluetoothDeviceData = new ArrayList<>();
    private BluetoothListAdapter adapter;
    private ProgressBar bluetoothProgressBar;

    public ConnectBluetooth(Context context){
        this.context = context;
    }

    private void setBluetooth() {

        adapter = new BluetoothListAdapter(context);
        adapter.setData(bluetoothDeviceData);
        bluetoothListView.setAdapter(adapter);

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        context.registerReceiver(mReceiver, filter);

        bluetoothProgressBar = (ProgressBar) LayoutInflater.from(context).inflate(R.layout.progressbar, null);
        bluetoothListView.addFooterView(bluetoothProgressBar);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(context, "This device did not support bluetooth", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {
            setBluetoothList();
        }
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        this.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case REQUEST_ENABLE_BT:
//                setBluetoothList();
//                break;
//        }
//    }

    private void setBluetoothList() {
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        for (BluetoothDevice device : pairedDevices) {
            bluetoothDeviceData.add(device);
        }
        adapter.notifyDataSetChanged();
        mBluetoothAdapter.startDiscovery();
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.e("action", action);
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                bluetoothDeviceData.add(device);
                adapter.notifyDataSetChanged();
            } else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                bluetoothListView.removeFooterView(bluetoothProgressBar);
            }
        }
    };

    @OnItemClick(R.id.bluetoothList)
    void onItemClicked(int position) {
        if(bluetoothDeviceData.size() == position){
            return;
        }
        BluetoothDevice selectedDevice = bluetoothDeviceData.get(position);
        startConnect(selectedDevice);
    }

    private void startConnect(BluetoothDevice selectedDevice) {
        ConnectThread connectThread = new ConnectThread(selectedDevice);
        connectThread.start();
    }
}
