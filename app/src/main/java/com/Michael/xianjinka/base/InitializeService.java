package com.Michael.xianjinka.base;

import android.app.IntentService;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;

import com.avos.avoscloud.AVOSCloud;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.meituan.android.walle.WalleChannelReader;
import com.umeng.commonsdk.UMConfigure;
import com.Michael.xianjinka.App;
import com.Michael.xianjinka.R;
import com.Michael.xianjinka.common.Contacts;

/**
 * Created by apple on 2018/8/8.
 */

public class InitializeService extends IntentService {

    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_INIT_WHEN_APP_CREATE = "com.guesslive.caixiangji.service.action.app.create";
    public static final String EXTRA_PARAM = "com.guesslive.caixiangji.service.extra.PARAM";

    public InitializeService(String name) {
        super("InitializeService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT_WHEN_APP_CREATE.equals(action)) {
                performInit(EXTRA_PARAM);
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startForeground(1,new Notification());
    }

    /** * 启动调用 * @param context */
    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT_WHEN_APP_CREATE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    /** * 启动初始化操作 */
    private void performInit(String param) {
        String channel = WalleChannelReader.getChannel(this.getApplicationContext());
        initUmeng(channel);
        initOkgo(channel);
    }

    private void initOkgo(String channel) {
        HttpParams params=new HttpParams();
        String name = getString(R.string.appname);
        params.put("market","123");
        params.put("name", name);
        OkGo.getInstance().init(App.getApp())
                .addCommonParams(params);
    }

    private void initUmeng(String channel) {
        UMConfigure.init(this, Contacts.KEY.ANALYTICS_KEY, channel, UMConfigure.DEVICE_TYPE_PHONE, Contacts.KEY.ANALYTICS_KEY);

    }
}
