package com.example.funtest2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.funtest2.R;
import com.example.funtest2.base.BaseActivity;

/**
 * @author hws
 * @class describe
 * @time 2019-12-24 11:15
 */
public class DeviceActivity extends BaseActivity {
    private TextView deviceList;
    private TextView addDevice;
    private TextView deleteDevice;
    private TextView addByWIFI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        initView();
        initData();
        initOnclick();
    }

    @Override
    public void initView() {
        deviceList = findViewById(R.id.tv_deviceList_device);
        addDevice = findViewById(R.id.tv_addDevice_device);
        deleteDevice = findViewById(R.id.tv_deleteDevice_device);
        addByWIFI = findViewById(R.id.tv_addByWIFI_device);
    }

    @Override
    public void initData() {
    }

    @Override
    public void initOnclick() {
        deviceList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeviceActivity.this, DeviceListActivity.class);
                startActivity(intent);
            }
        });
        addDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeviceActivity.this, AddDeviceActivity.class);
                startActivity(intent);
            }
        });
        deleteDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeviceActivity.this, DeleteDeviceActivity.class);
                startActivity(intent);
            }
        });
        addByWIFI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeviceActivity.this, AddByWifiActivity.class);
                startActivity(intent);
            }
        });
    }
}
