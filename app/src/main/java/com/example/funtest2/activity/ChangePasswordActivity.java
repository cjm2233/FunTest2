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
 * @time 2019-12-24 16:11
 */
public class ChangePasswordActivity extends BaseActivity {
    private EditText password1;
    private EditText password2;
    private Button changePassword;
    private EditText username;
    private EditText oldPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        initView();
        initData();
        initOnclick();
    }

    @Override
    public void initView() {
        password1 = findViewById(R.id.ed_password1_changepassword);
        password2 = findViewById(R.id.ed_password2_changepassword);
        changePassword = findViewById(R.id.btn_change_changepassword);
        oldPassword = findViewById(R.id.ed_oldpassword_changepassword);
        username = findViewById(R.id.ed_username_changepassword);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnclick() {
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });
    }
    private void changePassword(){
        String password1Str = password1.getText().toString();
        String password2Str = password2.getText().toString();
        String userNameStr = username.getText().toString();
        String oldPasswordStr = oldPassword.getText().toString();
        if (password1Str.equals(password2Str)){
            XMAccountManager.getInstance().modifyPwd(userNameStr,oldPasswordStr, password1Str, new BaseAccountManager.OnAccountManagerListener() {
                @Override
                public void onSuccess(int i) {
                    Toast.makeText(MyApplication.getContext(), "更改密码成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailed(int i, int i1) {
                    Toast.makeText(MyApplication.getContext(), "更改密码失败"+i1, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFunSDKResult(Message message, MsgContent msgContent) {

                }
            });
        }else {
            Toast.makeText(MyApplication.getContext(), "两次输入的密码不同", Toast.LENGTH_SHORT).show();
        }
    }

}
