package com.example.funtest2.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.funtest2.R;
import com.example.funtest2.application.MyApplication;
import com.example.funtest2.base.BaseActivity;
import com.lib.MsgContent;
import com.manager.account.BaseAccountManager;
import com.manager.account.XMAccountManager;

/**
 * @author hws
 * @class describe
 * @time 2019-12-24 13:57
 */
public class DeleteDeviceActivity extends BaseActivity {
    private EditText sn;
    private Button deleteDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        initView();
        initData();
        initOnclick();
    }

    @Override
    public void initView() {
        sn = findViewById(R.id.ed_sn_delete);
        deleteDevice = findViewById(R.id.btn_deletedevice_delete);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnclick() {
        deleteDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDevice();
            }
        });
    }

    private void deleteDevice() {
        String snStr = sn.getText().toString().trim();
        XMAccountManager.getInstance().deleteDev(snStr, new BaseAccountManager.OnAccountManagerListener() {
            @Override
            public void onSuccess(int i) {
                Toast.makeText(MyApplication.getContext(), "删除成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(int i, int i1) {
                Toast.makeText(MyApplication.getContext(), "删除失败" + i1, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFunSDKResult(Message message, MsgContent msgContent) {

            }
        });
    }
}
