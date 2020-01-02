package com.example.funtest2.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.funtest2.R;
import com.example.funtest2.application.MyApplication;
import com.example.funtest2.base.BaseActivity;
import com.manager.device.DeviceManager;

/**
 * @author hws
 * @class describe
 * @time 2019-12-31 15:25
 */
public class DeviceReNameActivity extends BaseActivity {
    private EditText sn;
    private EditText name;
    private Button reName;
    private String snStr;
    private String nameStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devicerename);
        initView();
        initData();
        initOnclick();
    }

    @Override
    public void initView() {
        sn = findViewById(R.id.ed_sn_devicerename);
        name = findViewById(R.id.ed_name_devicerename);
        reName = findViewById(R.id.btn_rename_devicerename);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnclick() {
        reName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reName();
            }
        });
    }

    private void reName() {
        snStr = sn.getText().toString();
        nameStr = name.getText().toString();
        if (snStr != null && snStr.length() > 0) {
            if (nameStr != null && nameStr.length() > 0) {
                DeviceManager.getInstance().modifyDevName(snStr, nameStr, new DeviceManager.OnDevManagerListener() {
                    @Override
                    public void onSuccess(String s, int i, Object o) {
                        Toast.makeText(MyApplication.getContext(), "更改成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed(String s, int i, String s1, int i1) {
                        Toast.makeText(MyApplication.getContext(), "更改失败"+i1, Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(MyApplication.getContext(), "请输入设备新名称", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MyApplication.getContext(), "请输入设备序列号", Toast.LENGTH_SHORT).show();
        }
    }
}
