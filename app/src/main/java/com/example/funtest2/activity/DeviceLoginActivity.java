package com.example.funtest2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funtest2.R;
import com.example.funtest2.application.MyApplication;
import com.example.funtest2.base.BaseActivity;
import com.manager.device.DeviceManager;

/**
 * @author hws
 * @class describe
 * @time 2019-12-30 11:18
 */
public class DeviceLoginActivity extends BaseActivity {
    private TextView sn;
    private TextView username;
    private TextView password;
    private Button deviceLogin;
    private String snStr;
    private String usernameStr;
    private String passwordStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devicelogin);
        initView();
        initData();
        initOnclick();
    }

    @Override
    public void initView() {
        sn = findViewById(R.id.ed_sn_devicelogin);
        username = findViewById(R.id.ed_usernanme_devicelogin);
        password = findViewById(R.id.ed_password_devicelogin);
        deviceLogin = findViewById(R.id.btn_login_devicelogin);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnclick() {
        deviceLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        snStr = sn.getText().toString();
        usernameStr = username.getText().toString();
        passwordStr = password.getText().toString();
        if (snStr != null&&snStr.length() > 0){
            DeviceManager.getInstance().loginDev(snStr, usernameStr, passwordStr, new DeviceManager.OnDevManagerListener() {
                @Override
                public void onSuccess(String s, int i, Object o) {
                    Toast.makeText(MyApplication.getContext(), "设备登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DeviceLoginActivity.this,MediaActivity.class);
                    intent.putExtra("sn",snStr);
                    startActivity(intent);
                }

                @Override
                public void onFailed(String s, int i, String s1, int i1) {
                    Toast.makeText(MyApplication.getContext(), "设备登录失败", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(MyApplication.getContext(), "设备序列号不可为空", Toast.LENGTH_SHORT).show();
        }
    }
}
