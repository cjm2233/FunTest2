package com.example.funtest2.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basic.G;
import com.example.funtest2.R;
import com.example.funtest2.application.MyApplication;
import com.example.funtest2.base.BaseActivity;
import com.lib.MsgContent;
import com.lib.sdk.struct.SDBDeviceInfo;
import com.manager.account.BaseAccountManager;
import com.manager.account.XMAccountManager;
import com.manager.db.XMDevInfo;

/**
 * @author hws
 * @class describe
 * @time 2019-12-24 13:08
 */
public class AddDeviceActivity extends BaseActivity {
    private EditText sn;
    private Button addDevice;
    private String userNameStr;
    private String passwordStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        initData();
        initOnclick();
    }

    @Override
    public void initView() {
        sn = findViewById(R.id.ed_sn_add);
        addDevice = findViewById(R.id.btn_adddevice_add);
    }

    @Override
    public void initData() {
        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        userNameStr = sharedPreferences.getString("username", "");
        passwordStr = sharedPreferences.getString("password", "");
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
        String snStr = sn.getText().toString();
        SDBDeviceInfo info = new SDBDeviceInfo();
        G.SetValue(info.st_0_Devmac, snStr.trim());
        G.SetValue(info.st_1_Devname, G.ToString(info.st_0_Devmac));
        G.SetValue(info.st_4_loginName, "admin");
        G.SetValue(info.st_5_loginPsw, "");
        XMDevInfo xmDevInfo = new XMDevInfo();
        xmDevInfo.sdbDevInfoToXMDevInfo(info);
        XMAccountManager.getInstance().addDev(xmDevInfo, new BaseAccountManager.OnAccountManagerListener() {
            @Override
            public void onSuccess(int i) {
                Toast.makeText(MyApplication.getContext(), "添加成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(int i, int i1) {
                Toast.makeText(MyApplication.getContext(), "添加失败" + i1, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFunSDKResult(Message message, MsgContent msgContent) {

            }
        });
    }
}
