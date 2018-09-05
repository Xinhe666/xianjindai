package com.ant.cashant.base;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.avos.avoscloud.AVOSCloud;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.meituan.android.walle.WalleChannelReader;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.ant.cashant.App;
import com.ant.cashant.R;
import com.ant.cashant.common.Contacts;
import com.ant.cashant.utils.LogUtils;

import org.android.agoo.huawei.HuaWeiRegister;
import org.android.agoo.mezu.MeizuRegister;
import org.android.agoo.xiaomi.MiPushRegistar;

/**
 *
 * @author apple
 * @date 2018/8/8
 */

public class InitializeService extends IntentService {

    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_INIT_WHEN_APP_CREATE = "com.guesslive.caixiangji.service.action.app.create";
    public static final String EXTRA_PARAM = "com.guesslive.caixiangji.service.extra.PARAM";

    public InitializeService( ) {
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

    /** * 启动调用 * @param context */
    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT_WHEN_APP_CREATE);
        context.startService(intent);
    }

    /** * 启动初始化操作 */
    private void performInit(String param) {
        String channel = WalleChannelReader.getChannel(this.getApplicationContext());
        initUmeng(channel);
        initOkgo(channel);
    }

    private void initOkgo(String channel) {
        HttpParams params=new HttpParams();
        String name = getString(R.string.App_name);
        params.put("market",channel);
        params.put("name", name);
        OkGo.getInstance().init(App.getApp());
    }

    private void initUmeng(String channel) {

        UMConfigure.init(this, Contacts.KEY.ANALYTICS_KEY, channel, UMConfigure.DEVICE_TYPE_PHONE, Contacts.KEY.PUSH_KEY);
        PushAgent mPushAgent = PushAgent.getInstance(this);


        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });
        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SERVER); //声音
        mPushAgent.setNotificationPlayLights(MsgConstant.NOTIFICATION_PLAY_SERVER);//呼吸灯


        MiPushRegistar.register(this,"2882303761517491552", "5991749119552");
        HuaWeiRegister.register(this);

    }
}
