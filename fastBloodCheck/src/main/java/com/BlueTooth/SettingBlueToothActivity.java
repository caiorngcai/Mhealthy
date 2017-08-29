package com.BlueTooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.LoginActivity;
import com.Utils.ContantValue;
import com.Utils.SPutil;
import com.Utils.SysApplication;
import com.geniuseoe.demo.R;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by crc on 2017/7/27.
 */

public class SettingBlueToothActivity extends Activity implements AdapterView.OnItemClickListener{

    private ListView mLv;
    private BluetoothAdapter mBluetoothAdapter;
    private ArrayList<BluetoothDevice> mDevices = new ArrayList<BluetoothDevice>();
    private BroadcastReceiver mBluetoothReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // 扫描到蓝牙设备
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mDevices.add(device);
                mAdapter.notifyDataSetChanged();
                //System.out.println("device name" + device.getName());
            } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                Toast.makeText(getApplicationContext(), "开始扫描", Toast.LENGTH_SHORT).show();
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                Toast.makeText(getApplicationContext(), "扫描结束", Toast.LENGTH_SHORT).show();
            }
        }
    };
    private DeviceAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysApplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_main);
        mLv = (ListView) findViewById(R.id.lv);
        mAdapter = new DeviceAdapter(getApplicationContext(), mDevices);
        mLv.setAdapter(mAdapter);
        mLv.setOnItemClickListener(this);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
       // 注册广播接收者, 当扫描到蓝牙设备的时候, 系统会发送广播
        IntentFilter filter = new IntentFilter();
       filter.addAction(BluetoothDevice.ACTION_FOUND);
       filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
       filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mBluetoothReceiver, filter);

    }
    public void clickBtn(View v) {
        switch (v.getId()) {
            case R.id.button1:
                // 蓝牙是否可用
                if (!mBluetoothAdapter.isEnabled()) {
                    // 打开蓝牙
                    mBluetoothAdapter.enable();
                }
                break;
            case R.id.button2:
                // 关闭蓝牙
                if (mBluetoothAdapter.isEnabled()) {
                    mBluetoothAdapter.disable();
                }
                break;
            case R.id.button3:
                // 开始扫描
                mDevices.clear();
                mAdapter.notifyDataSetChanged();
                mBluetoothAdapter.startDiscovery();
                break;
            case R.id.button4:
                // 停止扫描
                mBluetoothAdapter.cancelDiscovery();
                break;
            case R.id.button5:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            default:
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBluetoothReceiver);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        BluetoothDevice device = mDevices.get(i);
        StorageAddress(device.getAddress());//存储mac地址
        Toast.makeText(getApplicationContext(),"绑定设置成功",Toast.LENGTH_SHORT).show();
       // conn(device);
        SPutil.putBoolean(getApplicationContext(),ContantValue.BLUETOOTH_ISOVER,true);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
//    private void conn(final BluetoothDevice device) {
//        // 建立蓝牙连接是耗时操作, 类似TCP Socket, 需要放在子线程里
//        new Thread() {
//            public void run() {
//                try {
//                    // 获取 BluetoothSocket, UUID需要和蓝牙服务端保持一致
//                    BluetoothSocket bluetoothSocket = device.createRfcommSocketToServiceRecord(UUID
//                            .fromString("00001101-0000-1000-8000-00805F9B34FB"));
//                    // 和蓝牙服务端建立连接
//                    bluetoothSocket.connect();
//                    // 获取输出流, 往蓝牙服务端写指令信息
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            System.out.println("连接成功----");
//                            Toast.makeText(getApplicationContext(), "连接成功", Toast.LENGTH_SHORT)
//                                    .show();
//                        }
//                    });
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            };
//        }.start();
//    }

    /**
     * 存储蓝牙地址到sp的方法
     * @return
     */
    public void StorageAddress(String address)
    {
        SPutil.putString(this, ContantValue.BLUETOOTHADDRESS,address);
    }
}
