package com.kim.nohttp;

import android.app.Application;

import com.yolanda.nohttp.NoHttp;

/**
 * Application类，用于初始化NoHttp
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.initialize(this);
    }
}
