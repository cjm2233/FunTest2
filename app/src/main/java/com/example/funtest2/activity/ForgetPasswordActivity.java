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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hws
 * @class describe
 * @time 2019-12-25 14:44
 */
public class ForgetPasswordActivity extends BaseActivity {
    private EditText what;
    private EditText password1;
    private EditText password2;
    private EditText code;
    private Button getCode;
    private Button resetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        initView();
        initData();
        initOnclick();
    }

    @Override
    public void initView() {
        what = findViewById(R.id.ed_phone_forgetpassword);
        password1 = findViewById(R.id.ed_password1_forgetpassword);
        password2 = findViewById(R.id.ed_password2_forgetpassword);
        code = findViewById(R.id.ed_code_forgetpassword);
        getCode = findViewById(R.id.btn_Code_forgetpassword);
        resetPassword = findViewById(R.id.btn_reset_forgetpassowrd);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnclick() {
        getCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCode();
            }
        });
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void getCode() {
        String whatStr = what.getText().toString();
        if (whatStr.isEmpty()) {
            Toast.makeText(MyApplication.getContext(), "请输入邮箱或手机号", Toast.LENGTH_SHORT).show();
        } else {
            if (isEmail(whatStr)) {
                XMAccountManager.getInstance().sendEmailCodeForResetPwd(whatStr, new BaseAccountManager.OnAccountManagerListener() {
                    @Override
                    public void onSuccess(int i) {
                        Toast.makeText(MyApplication.getContext(), "发送成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed(int i, int i1) {
                        Toast.makeText(MyApplication.getContext(), "发送失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFunSDKResult(Message message, MsgContent msgContent) {

                    }
                });
            } else if (isPhone(whatStr)) {
                XMAccountManager.getInstance().sendPhoneCodeForResetPwd(whatStr, new BaseAccountManager.OnAccountManagerListener() {
                    @Override
                    public void onSuccess(int i) {
                        Toast.makeText(MyApplication.getContext(), "发送成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed(int i, int i1) {
                        Toast.makeText(MyApplication.getContext(), "发送失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFunSDKResult(Message message, MsgContent msgContent) {

                    }
                });
            } else {
                Toast.makeText(MyApplication.getContext(), "请输入邮箱或手机号", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void resetPassword() {
        String whatStr = what.getText().toString();
        String password1Str = password1.getText().toString();
        String password2Str = password2.getText().toString();
        String codeStr = code.getText().toString();
        if (password1Str.equals(password2Str)) {
            if (isEmail(whatStr)) {
                if (XMAccountManager.getInstance().verifyEmailCode(whatStr, codeStr, new BaseAccountManager.OnAccountManagerListener() {
                    @Override
                    public void onSuccess(int i) {

                    }

                    @Override
                    public void onFailed(int i, int i1) {
                        Toast.makeText(MyApplication.getContext(), "验证码有误", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFunSDKResult(Message message, MsgContent msgContent) {

                    }
                })) {
                    XMAccountManager.getInstance().resetPwdByEmail(whatStr, password1Str, new BaseAccountManager.OnAccountManagerListener() {
                        @Override
                        public void onSuccess(int i) {
                            Toast.makeText(MyApplication.getContext(), "重置成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailed(int i, int i1) {
                            Toast.makeText(MyApplication.getContext(), "重置失败", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFunSDKResult(Message message, MsgContent msgContent) {

                        }
                    });
                }
            } else if (isPhone(whatStr)) {
                if (XMAccountManager.getInstance().verifyPhoneCode(whatStr, codeStr, new BaseAccountManager.OnAccountManagerListener() {
                    @Override
                    public void onSuccess(int i) {

                    }

                    @Override
                    public void onFailed(int i, int i1) {
                        Toast.makeText(MyApplication.getContext(), "验证码有误", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFunSDKResult(Message message, MsgContent msgContent) {

                    }
                })) {
                    XMAccountManager.getInstance().resetPwdByPhone(whatStr, password1Str, new BaseAccountManager.OnAccountManagerListener() {
                        @Override
                        public void onSuccess(int i) {
                            Toast.makeText(MyApplication.getContext(), "重置成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailed(int i, int i1) {
                            Toast.makeText(MyApplication.getContext(), "重置失败", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFunSDKResult(Message message, MsgContent msgContent) {

                        }
                    });
                }
            } else {
                Toast.makeText(MyApplication.getContext(), "请输入邮箱或手机号", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(MyApplication.getContext(), "两次输入的密码不同", Toast.LENGTH_SHORT).show();
        }
    }

    public static Boolean isEmail(String str) {
        Boolean isEmail = false;
        String expr = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})$";

        if (str.matches(expr)) {
            isEmail = true;
        }
        return isEmail;
    }

    public static boolean isPhone(String phone) {
        Pattern pattern = Pattern
                .compile("^(13[0-9]|15[0-9]|153|15[6-9]|180|18[23]|18[5-9])\\d{8}$");
        Matcher matcher = pattern.matcher(phone);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}
