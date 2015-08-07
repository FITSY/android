package com.example.syoung.fitsy.main.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import com.example.syoung.fitsy.main.contents.ContentObject;
import com.example.syoung.fitsy.main.utils.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.UUID;

public class ConnectThread extends Thread {
    private final String TAG = "ConnectThread";

    private static final int PARSE_MODE_ERROR = 0;
    private static final int PARSE_MODE_WAIT_START_BYTE = 1;
    private static final int PARSE_MODE_WAIT_COMMAND = 2;
    private static final int PARSE_MODE_WAIT_DATA = 3;
    private static final int PARSE_MODE_WAIT_END_BYTE = 4;
    private static final int PARSE_MODE_COMPLETED = 101;

    private static final int POINT_WIDTH = 5;		// must be odd number.
    private static final int POINT_THICKNESS = 5;	// must be odd number.
    private static final int POINT_WIDTH_HALF = 2;
    private static final int POINT_THICKNESS_HALF = 2;

    static boolean bStart = true;
    static int PrevDrawingX;
    private int mCurrentDrawingX = 1 + POINT_WIDTH_HALF;	// current drawing position

    private final BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;
    private final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private TransactionBuilder mTransactionBuilder = null;
    private TransactionReceiver mTransactionReceiver = null;

    private byte mCacheStart = 0x00;
    private byte mCacheEnd = 0x00;
    private int mCacheData = 0x00;
    private boolean mCached = false;

    private ArrayList<ContentObject> mObjectQueue = new ArrayList<ContentObject>();
    private ContentObject mContentObject = null;

    private int mParseMode = PARSE_MODE_WAIT_START_BYTE;

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

    public void reset() {
        mParseMode = PARSE_MODE_WAIT_START_BYTE;
        mCacheStart = 0x00;
        mCacheEnd = 0x00;
        mCacheData = 0x00;
        mCached = false;
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
                setByteArray(buffer, buffer.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setByteArray(byte[] buffer, int count) {
        parseStream(buffer, count);
    }

    public ContentObject getObject() {
        ContentObject object = null;
        if(mObjectQueue != null && mObjectQueue.size() > 0)
            object = mObjectQueue.remove(0);

        return object;
    }

    public void parseStream(byte[] buffer, int count) {
        if(buffer != null && buffer.length > 0 && count > 0) {
            for(int i=0; i < buffer.length && i < count; i++) {

                // Parse received data
                // Protocol description -----------------------------------------------------------
                // [*] Accel data
                // 		[Start byte: 2byte]
                //		[Data: 6byte: 3 integer data]...
                //		[End byte: 2byte]

                switch(mParseMode) {

                    case PARSE_MODE_WAIT_START_BYTE:
                        if(buffer[i] == Transaction.TRANSACTION_START_BYTE_2
                                && mCacheStart == Transaction.TRANSACTION_START_BYTE) {
                            Log.d(TAG, "Read data: TRANSACTION_START_BYTE");
                            mParseMode = PARSE_MODE_WAIT_DATA;
                            if(mContentObject == null) {
                                mContentObject = new ContentObject(ContentObject.CONTENT_TYPE_ACCEL, -1, 0);
                                mContentObject.mTimeInMilli = System.currentTimeMillis();
                            }
                        } else {
                            mCacheStart = buffer[i];
                        }
                        break;
                    /**
                     *  Disabled:
                     */
                    case PARSE_MODE_WAIT_COMMAND:
                        Log.d(TAG, "Read data: PARSE_MODE_WAIT_COMMAND = " + String.format("%02X ", buffer[i]));
                        switch(buffer[i]) {
                            case Transaction.COMMAND_TYPE_PING:
                                mParseMode = PARSE_MODE_WAIT_END_BYTE;
                                break;

                            case Transaction.COMMAND_TYPE_ACCEL_DATA:
                                mParseMode = PARSE_MODE_WAIT_DATA;
                                break;

                            default:
                                mParseMode = PARSE_MODE_WAIT_START_BYTE;
                                break;
                        }	// End of switch()
                        break;
                    case PARSE_MODE_WAIT_DATA:
					/*
					 * TODO: Check end byte (sometimes data byte is same with end byte)
					 *
					*/
                        if(buffer[i] == Transaction.TRANSACTION_END_BYTE
                                || buffer[i] == Transaction.TRANSACTION_END_BYTE_2) {
                            if(buffer[i] == Transaction.TRANSACTION_END_BYTE_2
                                    && mCacheEnd == Transaction.TRANSACTION_END_BYTE) {
                                Log.d(TAG, "Read data: TRANSACTION_END_BYTE");
                                mParseMode = PARSE_MODE_COMPLETED;
                                break;
                            } else {
                                mCacheEnd = buffer[i];
                            }
                        }

                        // Forced to fill 20 accel data
                        if(mContentObject != null && mContentObject.mAccelIndex > ContentObject.DATA_COUNT - 1) {
                            Log.d(TAG, "Read data: TRANSACTION_END_BYTE");
                            mParseMode = PARSE_MODE_COMPLETED;
                            break;
                        }

                        // Remote device(Arduino) uses 2-byte integer.
                        // We must cache 2byte to make single value
                        if(mCached) {
                            int tempData = 0x00000000;
                            int tempData2 = 0x00000000;
                            boolean isNegative = false;

                            if(mCacheData == 0x0000007f)	// Recover first byte (To avoid null byte, 0x00 was converted to 0x7f)
                                mCacheData = 0x00000000;
                            if( (mCacheData & 0x00000080) == 0x00000080 )	// Check first bit which is 'sign' bit
                                isNegative = true;
                            if(buffer[i] == 0x01) 	// Recover second byte (To avoid null byte, 0x00 was converted to 0x01)
                                buffer[i] = 0x00;

                            tempData2 |= (buffer[i] & 0x000000ff);
                            tempData = (((mCacheData << 8) | tempData2) & 0x00007FFF);

                            //Log.d(TAG, String.format("%02X ", mCacheData) + String.format("%02X ", tempData2) + String.format("%02X ", tempData));

                            // negative number uses 2's complement math. Set first 9 bits as 1.
                            if(isNegative)
                                tempData = (tempData | 0xFFFF8000);

                            // Recovered integer value. Remember this value.
                            if(mContentObject != null) {
                                mContentObject.setAccelData(tempData);
                            }
                            mCacheData = 0x00000000;
                            mCached = false;
                        } else {
                            mCacheData |= (buffer[i] & 0x000000ff);		// Remember first byte
                            mCached = true;
                        }
                        break;

                }	// End of switch()

                if(mParseMode == PARSE_MODE_COMPLETED) {
                    pushObject();
                    reset();
                }
            }	// End of for loop
        }	// End of if()
    }

    /**
     * Push new object to queue
     */
    private void pushObject() {
        if(mContentObject != null) {
            //Logs.d("ContentObject created: time="+mContentObject.mTimeInMilli);
            mObjectQueue.add(mContentObject);
            mContentObject = null;
        }
/**
 *
 drawAccelGraph(getObject().mAccelData);
 */
        if(mObjectQueue != null){
            drawAccelData();
        }
    }

    private void drawAccelData(){
        drawAccelGraph(getObject().mAccelData);
    }

    private void drawAccelGraph(int[] accelArray){
        if(accelArray == null || accelArray.length < 3)
            return;

        if(bStart == true) {
            PrevDrawingX = mCurrentDrawingX;
            bStart = false;
            return;
        }

        for(int i=3; i<accelArray.length; i+=3) {
            // x axis value is Red dot
            /*drawPoint(TYPE_RED, mCurrentDrawingX, accelArray[i]);
            drawLine(TYPE_RED, PrevDrawingX, mCurrentDrawingX, accelArray[i - 3], accelArray[i]);*/

            //mCurrentDrawingX = accelArray[i];
            int result = accelArray[i] - accelArray[i-3];
            Log.e(TAG, "x Axis | Current X - Prev X = " + result);

            // y axis value is Blue dot
            /*drawPoint(TYPE_GREEN, mCurrentDrawingX, accelArray[i+1]);
            drawLine(TYPE_GREEN, PrevDrawingX, mCurrentDrawingX, accelArray[i - 2], accelArray[i + 1]);*/

            //mCurrentDrawingX = accelArray[i +1];
            //Log.e(TAG, "y Axis | Prev Y : " + accelArray[i - 2] + ", Current Y : " + accelArray[i + 1]);
            int result2 = accelArray[i + 1] - accelArray[i-2];
            Log.e(TAG, "y Axis | Current Y - Prev Y = " + result2);

            // z axis value is Green dot
            /*drawPoint(TYPE_BLUE, mCurrentDrawingX, accelArray[i+2]);
            drawLine(TYPE_BLUE, PrevDrawingX, mCurrentDrawingX, accelArray[i - 1], accelArray[i + 2]);*/

            //Log.e(TAG, "z Axis | Prev Z : " + accelArray[i - 1] + ", Current Z : " + accelArray[i + 2]);
            int result3 = accelArray[i + 2] - accelArray[i-1];
            Log.e(TAG, "z Axis | Current Z - Prev Z = " + result3);

        }

    }

    /**
     * Defines transaction constants
     */
    public class Transaction {
        private static final byte TRANSACTION_START_BYTE = (byte)0xfe;
        private static final byte TRANSACTION_START_BYTE_2 = (byte)0xfd;
        private static final byte TRANSACTION_END_BYTE = (byte)0xfd;
        private static final byte TRANSACTION_END_BYTE_2 = (byte)0xfe;

        public static final int COMMAND_TYPE_NONE = 0x00;
        public static final int COMMAND_TYPE_PING = 0x01;
        public static final int COMMAND_TYPE_ACCEL_DATA = 0x02;
    }

    //TODO 연결이 끊기면 그 전에 연결한 기기 다시 연결하게 하기
   /* private void manageData(byte[] data) {
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
    }*/
}
