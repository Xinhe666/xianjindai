package com.Michael.xianjinka;

import android.app.Application;
import android.os.Build;

import com.avos.avoscloud.AVOSCloud;
import com.Michael.xianjinka.base.InitializeService;
import com.Michael.xianjinka.common.Contacts;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.meituan.android.walle.WalleChannelReader;
import com.umeng.commonsdk.UMConfigure;

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
     //   InitializeService.start(this);
        String channel = WalleChannelReader.getChannel(getApplicationContext());

        HttpParams params=new HttpParams();
        String name = getString(R.string.appname);
        params.put("market",channel);
        params.put("name", name);
        OkGo.getInstance().init(App.getApp())
                .addCommonParams(params);

        UMConfigure.init(this, Contacts.KEY.ANALYTICS_KEY, channel, UMConfigure.DEVICE_TYPE_PHONE, Contacts.KEY.ANALYTICS_KEY);

    }

    public static App getApp(){
        return instance;
    }
}
