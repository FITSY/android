package com.example.syoung.fitsy;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.syoung.fitsy.course.CourseFragment;
import com.example.syoung.fitsy.history.HistoryFragment;
import com.example.syoung.fitsy.main.MainFragment;
import com.example.syoung.fitsy.main.adapter.BluetoothListAdapter;
import com.example.syoung.fitsy.main.bluetooth.ConnectThread;
import com.example.syoung.fitsy.myinfo.MyInformationFragment;
import com.example.syoung.fitsy.statistics.StatisticsFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//TODO : all Fragment change(private constructor => public constructor)

public class MainActivity extends AppCompatActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment navigationDrawerFragment;
    private CharSequence title;
    private static final int REQUEST_ENABLE_BT = 0;

    private BluetoothAdapter bluetoothAdapter;
    private List<BluetoothDevice> bluetoothDeviceData = new ArrayList<>();
    private BluetoothListAdapter bluetoothListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        title = getString(R.string.title_section1);

        navigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

        bluetoothListAdapter = new BluetoothListAdapter(this);
        bluetoothListAdapter.setData(bluetoothDeviceData);

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.registerReceiver(mReceiver, filter);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment objFragment = null;

        switch (position){
            case 0:
                objFragment = MainFragment.getInstance();
                title = getString(R.string.title_section1);
                break;
            case 1:
                objFragment = StatisticsFragment.getInstance();
                title = getString(R.string.title_section2);
                break;
            case 2:
                objFragment = HistoryFragment.getInstance();
                title = getString(R.string.title_section3);
                break;
            case 3:
                objFragment = CourseFragment.getInstance();
                title = getString(R.string.title_section4);
                break;
            case 4:
                objFragment = MyInformationFragment.getInstance();
                title = getString(R.string.title_section5);
                break;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, objFragment).commit();
        setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!navigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (item.getItemId() == R.id.hand_device) {
            String [] items=new String []{"Item 1","Item 2","Item 3","Item 4"};
            AlertDialog.Builder handDeviceBuilder = getDeviceAlertDialogBuilder("Hand Device");

            handDeviceBuilder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            handDeviceBuilder.show();
            return true;
        }

        if (item.getItemId() == R.id.foot_device) {
            AlertDialog.Builder footDeviceBuilder = getDeviceAlertDialogBuilder("Foot Device");
            footDeviceBuilder.setAdapter(bluetoothListAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (bluetoothDeviceData.size() == which) {
                        return;
                    }
                    BluetoothDevice selectedDevice = bluetoothDeviceData.get(which);
                    startConnect(selectedDevice);
                }
            });

            footDeviceBuilder.show();

            if (bluetoothAdapter == null) {
                Toast.makeText(this, "This device did not support bluetooth", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (!bluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            } else {
                setBluetoothList();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private AlertDialog.Builder getDeviceAlertDialogBuilder(String title) {
        AlertDialog.Builder deviceBuilder;
        deviceBuilder = new AlertDialog.Builder(this);
        deviceBuilder.setTitle(title);
        return deviceBuilder;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_ENABLE_BT:
                setBluetoothList();
                break;
        }
    }

    private void setBluetoothList() {
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        for (BluetoothDevice device : pairedDevices) {
            bluetoothDeviceData.add(device);
        }
        bluetoothListAdapter.notifyDataSetChanged();
        bluetoothAdapter.startDiscovery();
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.e("action", action);
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                bluetoothDeviceData.add(device);
                bluetoothListAdapter.notifyDataSetChanged();
            } else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
            }
        }
    };

    private void startConnect(BluetoothDevice selectedDevice) {
        ConnectThread connectThread = new ConnectThread(selectedDevice);
        connectThread.start();
    }
}
