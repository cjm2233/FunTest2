package com.example.funtest2.activity;

import android.app.Activity;
import android.content.SharedPreferences;
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

import static com.manager.db.Define.LOGIN_BY_ACCOUNT;

/**
 * @author hws
 * @class describe
 * @time 2019-12-24 10:47
 */
public class LoginActivity extends BaseActivity {
    //账号
    private EditText user;
    //密码
    private EditText password;
    //登录
    private Button login;
    private String userStr;
    private String passwordStr;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
        initOnclick();
    }

    @Override
    public void initView() {
        user = findViewById(R.id.ed_user_login);
        password = findViewById(R.id.ed_password_login);
        login = findViewById(R.id.btn_login_login);
    }

    @Override
    public void initData() {
        sharedPreferences = getSharedPreferences("save", Activity.MODE_PRIVATE);
        user.setText("13587229027");
        password.setText("12345678");
    }

    @Override
    public void initOnclick() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginByPhone();
            }
        });
    }
    private void loginByPhone(){
        userStr = user.getText().toString();
        passwordStr = password.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",userStr);
        editor.putString("password",passwordStr);
        editor.commit();
        XMAccountManager.getInstance().login(userStr, passwordStr, LOGIN_BY_ACCOUNT, new BaseAccountManager.OnAccountManagerListener() {
            @Override
            public void onSuccess(int i) {
                Toast.makeText(MyApplication.getContext(),"登录成功",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailed(int i, int i1) {
                Toast.makeText(MyApplication.getContext(),"登录失败"+i1,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFunSDKResult(Message message, MsgContent msgContent) {

            }
        });
    }
}
