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
 * @time 2019-12-26 9:14
 */
public class RegisterByEmailActivity extends BaseActivity {
    //用户名
    private EditText username;
    //密码
    private EditText password1;
    //第二遍验证密码
    private EditText password2;
    //电话号码
    private EditText email;
    //验证码
    private EditText checkcode;
    //获取验证码
    private Button getcode;
    //注册
    private Button register;
    private String usernameStr;
    private String password1Str;
    private String password2Str;
    private String checkcodeStr;
    private String emailStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerbyemail);
        initView();
        initData();
        initOnclick();
    }

    @Override
    public void initView() {
        username = findViewById(R.id.ed_username_registerbyemail);
        password1 = findViewById(R.id.ed_password1_registerbyemail);
        password2 = findViewById(R.id.ed_password2_registerbyemail);
        email = findViewById(R.id.ed_email_registerbyemail);
        checkcode = findViewById(R.id.ed_code_registerbyemail);
        getcode = findViewById(R.id.btn_getcode_registerbyemail);
        register = findViewById(R.id.btn_register_registerbyemail);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnclick() {
        getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCode();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerByEmail();
            }
        });
    }

    private void getCode() {
        emailStr = email.getText().toString();
        XMAccountManager.getInstance().sendEmailCodeForRegister(emailStr, new BaseAccountManager.OnAccountManagerListener() {
            @Override
            public void onSuccess(int i) {
                Toast.makeText(MyApplication.getContext(), "验证码已发送", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(int i, int i1) {
                Toast.makeText(MyApplication.getContext(), "验证码发送失败" + i1, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFunSDKResult(Message message, MsgContent msgContent) {

            }
        });
    }

    private void registerByEmail() {
        usernameStr = username.getText().toString();
        password1Str = password1.getText().toString();
        password2Str = password2.getText().toString();
        checkcodeStr = checkcode.getText().toString();
        emailStr = email.getText().toString();
        if (password1Str.equals(password2Str)) {
            XMAccountManager.getInstance().registerByEmail(usernameStr, password1Str, emailStr, checkcodeStr, new BaseAccountManager.OnAccountManagerListener() {
                @Override
                public void onSuccess(int i) {
                    Toast.makeText(MyApplication.getContext(), "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailed(int i, int i1) {
                    Toast.makeText(MyApplication.getContext(), "注册失败" + i1, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFunSDKResult(Message message, MsgContent msgContent) {

                }
            });
        } else {
            Toast.makeText(MyApplication.getContext(), "两次输入的密码不同", Toast.LENGTH_SHORT).show();
        }
    }
}
