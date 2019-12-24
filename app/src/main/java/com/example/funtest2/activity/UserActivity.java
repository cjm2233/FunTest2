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
 * @time 2019-12-24 9:56
 */
public class UserActivity extends BaseActivity {
    private TextView register;
    private TextView login;
    private TextView changePassword;
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
                Intent intent = new Intent(UserActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this,ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
