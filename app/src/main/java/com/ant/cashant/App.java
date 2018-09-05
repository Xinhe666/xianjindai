package com.ant.cashant;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.tencent.bugly.crashreport.CrashReport;
import com.ant.cashant.base.InitializeService;
import com.ant.cashant.common.Contacts;

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
        CrashReport.initCrashReport(getApplicationContext(), "0f064dde5f", false);
        AVOSCloud.initialize(this, Contacts.KEY.LEAN_ID,Contacts.KEY.LEAN_KEY);
        InitializeService.start(this);
    }

    public static App getApp(){
        return instance;
    }
}
