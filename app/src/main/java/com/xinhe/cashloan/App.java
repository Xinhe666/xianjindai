package com.xinhe.cashloan;

import android.app.Application;

import com.xinhe.cashloan.base.InitializeService;

/**
 * Created by apple on 2018/8/8.
 */

public class App extends Application{
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        InitializeService.start(this);
    }

    public static App getApp(){
        return instance;
    }
}
