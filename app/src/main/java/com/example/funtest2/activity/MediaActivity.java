package com.example.funtest2.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.funtest2.R;
import com.example.funtest2.application.MyApplication;
import com.example.funtest2.base.BaseActivity;
import com.lib.sdk.bean.HandleConfigData;
import com.lib.sdk.bean.JsonConfig;
import com.lib.sdk.bean.SystemInfoBean;
import com.manager.device.DeviceManager;
import com.manager.device.config.DevConfigInfo;
import com.manager.device.config.DevConfigManager;
import com.manager.device.media.attribute.PlayerAttribute;
import com.manager.device.media.monitor.MonitorManager;

import java.io.File;

/**
 * @author hws
 * @class describe
 * @time 2019-12-24 17:09
 */
public class MediaActivity extends BaseActivity {
    private LinearLayout linearLayout;
    private MonitorManager monitorManager;
    private DevConfigManager devConfigManager;
    private Button config;
    private Button stopMedia;
    private Button reStartMedia;
    private Button pauseMedia;
    private Button capture;
    private Button setConfig;
    private String sn;
    private SystemInfoBean systemInfoBean;
    private Button voice;
    private Button closeVoice;
    private Button startRecord;
    private Button stopRecord;
    private Button high;
    private Button low;
    private Button record;
    private PlayerAttribute playerAttribute;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        initView();
        initData();
        initOnclick();
    }

    @Override
    public void initView() {
        linearLayout = findViewById(R.id.lin_media_media);
        config = findViewById(R.id.btn_config_media);
        stopMedia = findViewById(R.id.btn_stop_media);
        reStartMedia = findViewById(R.id.btn_start_media);
        pauseMedia = findViewById(R.id.btn_pause_media);
        capture = findViewById(R.id.btn_capture_media);
        setConfig = findViewById(R.id.btn_setconfig_media);
        voice = findViewById(R.id.btn_openvoice_media);
        closeVoice = findViewById(R.id.btn_closevoice_media);
        startRecord = findViewById(R.id.btn_startrecord_media);
        stopRecord = findViewById(R.id.btn_stoprecord_media);
        high = findViewById(R.id.btn_high_media);
        low = findViewById(R.id.btn_low_media);
        record = findViewById(R.id.btn_record_media);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        sn = intent.getStringExtra("sn");
//        DeviceManager.getInstance().createMonitorPlayer(linearLayout,sn).startMonitor();
        playerAttribute = new PlayerAttribute(sn);
        monitorManager = new MonitorManager(linearLayout, playerAttribute);
        startMedia();
    }

    @Override
    public void initOnclick() {
        openPerssion();
        stopMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destoryMedia();
            }
        });
        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getConfig();
            }
        });
        reStartMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reStart();
            }
        });
        pauseMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause();
            }
        });
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capture();
            }
        });
        setConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setConfig();
            }
        });
        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVoice();
            }
        });
        closeVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeVoice();
            }
        });
        startRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecord();
            }
        });
        stopRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecord();
            }
        });
        high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHigh();
            }
        });
        low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLow();
            }
        });
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaActivity.this,RecordActivity.class);
                intent.putExtra("sn",sn);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        destoryMedia();
        super.onDestroy();
    }

    //继续播放
    private void reStart() {
        monitorManager.rePlay();
//        DeviceManager.getInstance().createMonitorPlayer(linearLayout,sn).rePlay();
    }

    //暂停播放
    private void pause() {
        monitorManager.pausePlay();
//        DeviceManager.getInstance().createMonitorPlayer(linearLayout,sn).pausePlay();
    }

    //获取配置
    private void getConfig() {
        devConfigManager = DevConfigManager.create(sn);
        DevConfigInfo devConfigInfo = DevConfigInfo.create(new DeviceManager.OnDevManagerListener() {
            @Override
            public void onSuccess(String s, int i, Object result) {
                String str = result.toString();
                HandleConfigData<SystemInfoBean> handleConfigData = new HandleConfigData();
                handleConfigData.getDataObj((String) result, SystemInfoBean.class, JsonConfig.SYSTEM_INFO);
                systemInfoBean = handleConfigData.getObj();
                Toast.makeText(MyApplication.getContext(), "获取成功：" + str, Toast.LENGTH_SHORT).show();
                Log.i("asjfklj", str);
            }

            @Override
            public void onFailed(String s, int i, String s1, int i1) {
                Toast.makeText(MyApplication.getContext(), "获取失败：" + i1, Toast.LENGTH_SHORT).show();
            }
        }, "configInfo" + sn);
        devConfigInfo.setJsonName(JsonConfig.SYSTEM_INFO);
        devConfigInfo.setSeq(0);
        devConfigInfo.setTimeOut(10000);
        devConfigInfo.setChnId(0);


        devConfigManager.getDevConfig(devConfigInfo);
    }

    private void destoryMedia() {
        monitorManager.destroyPlay();
//        DeviceManager.getInstance().createMonitorPlayer(linearLayout,sn).destroyPlay();
    }

    //截图
    private void capture() {
//        File file = Environment.getExternalStorageDirectory();
//        file.mkdirs();
//        String dir = file.getAbsolutePath();
        String dir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String path = monitorManager.capture(dir);
//        String path = DeviceManager.getInstance().createMonitorPlayer(linearLayout,sn).capture(dir);
        Log.i("capture path", "msg:" + path);
        try {
            File filePath = new File(path);
            if (!filePath.exists()) {
                Log.i("capture file", "不存在");
            } else {
                Log.i("capture file", "存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Log.i("asjklfklasj", path);
    }

    //保存配置
    private void setConfig() {
        devConfigManager = DevConfigManager.create(sn);
        DevConfigInfo devConfigInfo = DevConfigInfo.create(new DeviceManager.OnDevManagerListener() {
            @Override
            public void onSuccess(String s, int i, Object o) {
                String str = o.toString();
                Toast.makeText(MyApplication.getContext(), "设置成功：" + str, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(String s, int i, String s1, int i1) {
                Toast.makeText(MyApplication.getContext(), "设置失败：" + i1, Toast.LENGTH_SHORT).show();
            }
        }, "configInfo" + sn);
        devConfigInfo.setJsonName(JsonConfig.SYSTEM_INFO);
        devConfigInfo.setSeq(0);
        devConfigInfo.setTimeOut(10000);
        devConfigInfo.setChnId(0);
        String jsonData = HandleConfigData.getSendData(HandleConfigData.
                getFullName(JsonConfig.SYSTEM_INFO, -1), "0x08", systemInfoBean);
        devConfigInfo.setJsonData(jsonData);
        devConfigManager.setDevConfig(devConfigInfo);
    }

    //打开声音
    private void openVoice() {
        monitorManager.openVoiceBySound();
    }

    //关闭声音
    private void closeVoice() {
        monitorManager.closeVoiceBySound();
    }

    //开始录像
    private void startRecord() {
        File file = Environment.getExternalStorageDirectory();
        file.mkdirs();
        String dir = file.getAbsolutePath();
//        DeviceManager.getInstance().createMonitorPlayer(linearLayout, sn).startRecord(dir);
        monitorManager.startRecord(dir);
        Toast.makeText(MyApplication.getContext(), "开始录制", Toast.LENGTH_SHORT).show();
    }

    //停止录像
    private void stopRecord() {
        String recordName = monitorManager.stopRecord();
        Toast.makeText(MyApplication.getContext(), "录制结束", Toast.LENGTH_SHORT).show();
//        String recordName = DeviceManager.getInstance().createMonitorPlayer(linearLayout, sn).stopRecord();
        Log.i("afsasfsaj", recordName);
    }

    //申请读写权限
    private void openPerssion() {
        verifyStoragePermissions(this);
    }

    public void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //开始播放
    private void startMedia() {
        monitorManager.startMonitor();
    }

    //设置高清
    private void setHigh() {
        destoryMedia();
        playerAttribute.setStreamType(0);
        monitorManager = new MonitorManager(linearLayout, playerAttribute);
        startMedia();
    }

    //设置标清
    private void setLow() {
        destoryMedia();
        playerAttribute.setStreamType(1);
        monitorManager = new MonitorManager(linearLayout, playerAttribute);
        startMedia();
    }

}
