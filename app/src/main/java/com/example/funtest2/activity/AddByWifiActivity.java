package com.example.funtest2.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.funtest2.R;
import com.example.funtest2.base.BaseActivity;
import com.manager.device.DeviceManager;

/**
 * @author hws
 * @class describe
 * @time 2019-12-24 14:36
 */
public class AddByWifiActivity extends BaseActivity {
    private TextView ssid;
    private EditText wifiPassword;
    private Button addDevice;
    private String ssidStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbywifi);
        initView();
        initData();
        initOnclick();
    }

    @Override
    public void initView() {
        ssid = findViewById(R.id.tv_ssid_addbywifi);
        wifiPassword = findViewById(R.id.ed_password_addbywifi);
        addDevice = findViewById(R.id.btn_add_addbywifi);
    }

    @Override
    public void initData() {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        ssidStr = networkInfo.getExtraInfo();
        ssid.setText(ssidStr);
    }

    @Override
    public void initOnclick() {
        addDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDevice();
            }
        });
    }

    private void addDevice() {
        String passwordStr = wifiPassword.getText().toString();
        DeviceManager.getInstance().startQuickSetWiFi(ssidStr, passwordStr);
    }

    @Override
    protected void onDestroy() {
        DeviceManager.getInstance().stopQuickSetWiFi();
        super.onDestroy();
    }
}
