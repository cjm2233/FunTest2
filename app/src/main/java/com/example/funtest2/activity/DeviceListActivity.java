package com.example.funtest2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funtest2.R;
import com.example.funtest2.adapter.MyAdapter;
import com.example.funtest2.base.BaseActivity;
import com.example.funtest2.interfaces.OnItemClickListener;
import com.manager.account.XMAccountManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hws
 * @class describe
 * @time 2019-12-24 11:25
 */
public class DeviceListActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private List<String> devList = new ArrayList();
    private MyAdapter myAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devicelist);
        initView();
        initData();
        initOnclick();
    }

    @Override
    public void initView() {
        recyclerView = findViewById(R.id.rec_device_devicelist);
    }

    @Override
    public void initData() {
        devList = XMAccountManager.getInstance().getDevList();
        linearLayoutManager =new LinearLayoutManager(this);
        myAdapter = new MyAdapter(devList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void initOnclick() {
        myAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(DeviceListActivity.this,MediaActivity.class);
                intent.putExtra("sn",devList.get(position));
                startActivity(intent);
            }

            @Override
            public void onItemLongClicked(View view, int position) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        devList = XMAccountManager.getInstance().getDevList();
        myAdapter.setList(devList);
        myAdapter.notifyDataSetChanged();
    }
}
