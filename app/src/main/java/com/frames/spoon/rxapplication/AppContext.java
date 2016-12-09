package com.frames.spoon.rxapplication;

import android.app.Application;

import com.spoon.okmanager.OkManager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by zhanxiaolin-n22 on 2016/12/9.
 */

public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initOkManager();
    }

    private void initOkManager() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .build();
        OkManager.initClient(okHttpClient);
    }
}
