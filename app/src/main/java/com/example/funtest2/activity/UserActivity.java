package com.example.funtest2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.funtest2.R;
import com.example.funtest2.application.MyApplication;
import com.example.funtest2.base.BaseActivity;
import com.manager.account.XMAccountManager;

/**
 * @author hws
 * @class describe
 * @time 2019-12-24 9:56
 */
public class UserActivity extends BaseActivity {
    private TextView register;
    private TextView login;
    private TextView changePassword;
    private TextView forgetPassword;
    private TextView registerByEmail;
    private TextView logout;
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();
        initData();
        initOnclick();
    }

    @Override
    public void initView() {
        register = findViewById(R.id.tv_register_user);
        login = findViewById(R.id.tv_login_user);
        changePassword = findViewById(R.id.tv_changePassword_user);
        forgetPassword = findViewById(R.id.tv_forgetPassword_user);
        registerByEmail = findViewById(R.id.tv_registerbyemail_user);
        logout = findViewById(R.id.tv_logout_user);
        info = findViewById(R.id.tv_info_user);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnclick() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
        registerByEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, RegisterByEmailActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this,UserInfoActivity.class);
                startActivity(intent);
            }
        });
    }
    private void logout(){
        if (XMAccountManager.getInstance().hasLogin()) {
            XMAccountManager.getInstance().logout();
            if (!XMAccountManager.getInstance().hasLogin()) {
                Toast.makeText(MyApplication.getContext(), "退出登录", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MyApplication.getContext(), "还未登陆", Toast.LENGTH_SHORT).show();
        }
    }
}
