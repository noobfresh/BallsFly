package com.example.administrator.ballsfly;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by PYF on 2018/1/5.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
