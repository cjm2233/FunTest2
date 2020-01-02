package com.example.funtest2.activity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funtest2.R;
import com.example.funtest2.application.MyApplication;
import com.example.funtest2.base.BaseActivity;
import com.lib.MsgContent;
import com.manager.account.BaseAccountManager;
import com.manager.account.XMAccountManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author hws
 * @class describe
 * @time 2019-12-26 10:40
 */
public class UserInfoActivity extends BaseActivity {
    private TextView id;
    private TextView username;
    private TextView email;
    private TextView phone;
    private String idStr = null;
    private String usernameStr = null;
    private String emailStr = null;
    private String phoneStr = null;
    private String responseMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        initView();
        initData();
        initOnclick();
    }

    @Override
    public void initView() {
        id = findViewById(R.id.ed_id_userinfo);
        username = findViewById(R.id.ed_username_userinfo);
        email = findViewById(R.id.ed_email_userinfo);
        phone = findViewById(R.id.ed_phone_userinfo);
    }

    @Override
    public void initData() {
        getInfo();
    }

    @Override
    public void initOnclick() {

    }

    private void getInfo() {
        XMAccountManager.getInstance().getUserInfo(new BaseAccountManager.OnAccountManagerListener() {
            @Override
            public void onSuccess(int i) {
                Toast.makeText(MyApplication.getContext(), "获取成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(int i, int i1) {
                Toast.makeText(MyApplication.getContext(), "获取失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFunSDKResult(Message message, MsgContent msgContent) {
                Log.i("asfkl", msgContent.str);
                try {
                    JSONObject jsonContent = new JSONObject(msgContent.str);
                    responseMsg = jsonContent.getString("msg");
                    if (responseMsg.equalsIgnoreCase("success")) {
                        JSONObject jsonData = jsonContent.getJSONObject("data");
                        idStr = jsonData.optString("id");
                        usernameStr = jsonData.optString("username");
                        emailStr = jsonData.optString("mail");
                        phoneStr = jsonData.optString("phone");
                        Log.i("sajfkl", idStr + "  " + usernameStr + "  " + emailStr + "  " + phoneStr);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                id.setText(idStr);
                username.setText(usernameStr);
                email.setText(emailStr);
                phone.setText(phoneStr);

            }
        });
    }
}
