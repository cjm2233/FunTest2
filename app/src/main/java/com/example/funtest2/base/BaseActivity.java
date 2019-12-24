package com.example.funtest2.base;

import android.app.Activity;

/**
 * @author hws
 * @class describe
 * @time 2019-12-24 9:13
 */
public abstract class BaseActivity extends Activity {
    public abstract void initView();
    public abstract void initData();
    public abstract void initOnclick();
}
