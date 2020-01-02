package com.example.funtest2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.basic.G;
import com.example.funtest2.R;
import com.example.funtest2.base.BaseActivity;
import com.lib.MsgContent;
import com.lib.SDKCONST;
import com.manager.device.media.MediaManager;
import com.manager.device.media.attribute.PlayerAttribute;
import com.manager.device.media.attribute.RecrodPlayerAttribute;
import com.manager.device.media.playback.DevRecordManager;

/**
 * @author hws
 * @class describe
 * @time 2019-12-25 13:42
 */
public class RecordActivity extends BaseActivity implements MediaManager.OnRecordManagerListener {
    private LinearLayout linearLayout;
    private EditText sn;
    private String snStr;
    private Button button;
    private EditText hour;
    private EditText minute;
    private EditText second;
    private int hourInt;
    private int minuteInt;
    private int secondInt;
    private DevRecordManager devRecordManager;
    private RecrodPlayerAttribute playerAttribute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        initView();
        initData();
        initOnclick();
    }

    @Override
    public void initView() {
        linearLayout = findViewById(R.id.lin_record_record);
        sn = findViewById(R.id.ed_sn_record);
        button = findViewById(R.id.btn_start_record);
        hour = findViewById(R.id.ed_hour_record);
        minute = findViewById(R.id.ed_minute_record);
        second = findViewById(R.id.ed_second_record);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        snStr = intent.getStringExtra("sn");
        playerAttribute = new RecrodPlayerAttribute(snStr);
        playerAttribute.setFileType(SDKCONST.FileType.SDK_RECORD_ALL);
        devRecordManager = new DevRecordManager(linearLayout, playerAttribute);
        int record = devRecordManager.searchFile();
        Log.i("recordint:", " " + record);
    }

    @Override
    public void initOnclick() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                hourInt = Integer.parseInt(hour.getText().toString());
//                minuteInt = Integer.parseInt(minute.getText().toString());
//                secondInt = Integer.parseInt(second.getText().toString());
//                int time = getTime(hourInt, minuteInt, secondInt);
//                devRecordManager.seekToTime(,  + 5);
            }
        });
    }

    private int getTime(int hour, int minute, int second) {
        return hour * 3600 + minute * 60 + second;
    }

    @Override
    public void searchResult(PlayerAttribute playerAttribute, Object o) {
        byte[] datas;
        datas = G.ObjToBytes(o);
        Log.i("datasString:", datas.toString());
    }

    @Override
    public void onMediaPlayState(PlayerAttribute playerAttribute, int i) {

    }

    @Override
    public void onFailed(PlayerAttribute playerAttribute, int i, int i1) {

    }

    @Override
    public void onShowRateAndTime(PlayerAttribute playerAttribute, boolean b, String s, String s1) {

    }

    @Override
    public void onVideoBufferEnd(PlayerAttribute playerAttribute, MsgContent msgContent) {

    }
}
