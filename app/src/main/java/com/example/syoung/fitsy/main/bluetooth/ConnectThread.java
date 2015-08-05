package com.example.syoung.fitsy.main.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class ConnectThread extends Thread {
    private final BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;
    private final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private BluetoothAdapter adapter;

    public ConnectThread(BluetoothDevice device, BluetoothAdapter adapter) {
        // Use a temporary object that is later assigned to mmSocket,
        // because mmSocket is final
        BluetoothSocket tmp = null;
        mmDevice = device;
        this.adapter = adapter;

        // Get a BluetoothSocket to connect with the given BluetoothDevice
        try {
            // MY_UUID is the app's UUID string, also used by the server code
            tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mmSocket = tmp;
    }

    @Override
    public void run() {
        try {
            adapter.cancelDiscovery();
            mmSocket.connect();
        } catch (IOException connectException) {
            connectException.printStackTrace();
            try {
                mmSocket.close();
            } catch (IOException closeException) {
                closeException.printStackTrace();
            }
            return;
        }

        manageConnectedSocket(mmSocket);
    }

    /**
     * Will cancel an in-progress connection, and close the socket
     */
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) {
        }
    }

    public void manageConnectedSocket(BluetoothSocket mmSocket) {
        byte[] buffer = new byte[2048];
        int count;
        InputStream inputStream = null;
        try {
            inputStream = mmSocket.getInputStream();
            while ((count = inputStream.read(buffer)) != -1) {
                manageData(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO 연결이 끊기면 그 전에 연결한 기기 다시 연결하게 하기

    private void manageData(byte[] data) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for(byte b : data){
            ++i;
            if(i%3==0){
                builder.append(b).append("           ");
            }else{
                builder.append(b).append(" ");
            }
        }
        Log.e("data", builder.toString());
    }
}
