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
 * @time 2019-12-24 16:56
 */
public class VedioActivity extends BaseActivity {
    private TextView media;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedio);
        initView();
        initData();
        initOnclick();
    }

    @Override
    public void initView() {
        media = findViewById(R.id.tv_record_vedio);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnclick() {
        media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VedioActivity.this, RecordActivity.class);
                startActivity(intent);
            }
        });
    }
}
