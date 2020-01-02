package com.example.funtest2.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funtest2.R;
import com.example.funtest2.adapter.MyAdapter;
import com.example.funtest2.application.MyApplication;
import com.example.funtest2.base.BaseActivity;
import com.example.funtest2.interfaces.OnItemClickListener;
import com.manager.db.XMDevInfo;
import com.manager.device.DeviceManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hws
 * @class describe
 * @time 2019-12-30 14:33
 */
public class DeviceLanListActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private List<String> devList = new ArrayList();
    private List<XMDevInfo> list = new ArrayList<XMDevInfo>();
    private MyAdapter myAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devicelanlist);
        initView();
        initData();
        initOnclick();
    }

    @Override
    public void initView() {
        recyclerView = findViewById(R.id.rec_device_devicelanlist);
    }

    @Override
    public void initData() {
        if (isWifi(this)) {
            showProgressDialog();
            DeviceManager.getInstance().searchLanDevice(new DeviceManager.OnSearchLocalDevListener() {
                @Override
                public void onSearchLocalDevResult(List<XMDevInfo> list) {
                    Log.i("devlistsize1:", "" + list.size());
                    devList.clear();
                    if (list.size() > 0) {
                        for (int i = 0; i < list.size(); i++) {
                            devList.add(list.get(i).getDevId());
                            myAdapter.setList(devList);
                            myAdapter.notifyDataSetChanged();
                        }
                    } else {
                        Toast.makeText(MyApplication.getContext(), "当前局域网下不存在设备", Toast.LENGTH_SHORT).show();
                    }

                    closeProgressDialog();
                }
            });
        } else {
            Toast.makeText(MyApplication.getContext(), "当前未连接局域网", Toast.LENGTH_SHORT).show();
        }
        linearLayoutManager = new LinearLayoutManager(this);
        Log.i("devlistsize2:", "" + devList.size());
        myAdapter = new MyAdapter(devList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void initOnclick() {
        myAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(DeviceLanListActivity.this, MediaActivity.class);
                intent.putExtra("sn", devList.get(position));
                startActivity(intent);
            }

            @Override
            public void onItemLongClicked(View view, int position) {

            }
        });
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("请稍等");
        progressDialog.setMessage("加载中");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void closeProgressDialog() {
        progressDialog.dismiss();
    }

    /**
     *
     * @param context
     * @return
     */
    public boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        }
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }
}
