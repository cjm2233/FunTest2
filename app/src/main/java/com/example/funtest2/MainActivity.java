package com.example.funtest2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.funtest2.activity.DeviceActivity;
import com.example.funtest2.activity.UserActivity;
import com.example.funtest2.activity.VedioActivity;
import com.example.funtest2.base.BaseActivity;

public class MainActivity extends BaseActivity {
    private TextView user;
    private TextView device;
    private TextView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initOnclick();
//        XMFunSDKManager.getInstance().initXMCloudPlatform();
//        XMFunSDKManager.getInstance().initLog();
//        //账号相关
//        AccountManager;//通用账号管理类
//        LocalAccountManager;//本地
//        XMAccountManager.getInstance().resetPwdByPhone();//XM
//        com.manager.account.AccountManager.getInstance().localLogin();
//
//        //设备相关
//        DeviceManager;
//        //监控预览类
//        MonitorManager;
//        //回放类
//        RecordManager;
//        //配置相关类
//        DevConfigManager;
//        //配置参数类；
//        DevConfigInfo;
    }

    @Override
    public void initView() {
        user = findViewById(R.id.tv_user_main);
        device = findViewById(R.id.tv_device_main);
        video = findViewById(R.id.tv_video_main);
    }

    @Override
    public void initData() {
    }

    @Override
    public void initOnclick() {
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });
        device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DeviceActivity.class);
                startActivity(intent);
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VedioActivity.class);
                startActivity(intent);
            }
        });
    }
}
