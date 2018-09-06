package com.Michael.xianjinka;

import android.app.Application;
import android.os.Build;

import com.avos.avoscloud.AVOSCloud;
import com.Michael.xianjinka.base.InitializeService;
import com.Michael.xianjinka.common.Contacts;

/**
 *
 * @author apple
 * @date 2018/8/8
 */

public class App extends Application{
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AVOSCloud.initialize(this, Contacts.KEY.LEAN_ID,Contacts.KEY.LEAN_KEY);
        InitializeService.start(this);
    }

    public static App getApp(){
        return instance;
    }
}
