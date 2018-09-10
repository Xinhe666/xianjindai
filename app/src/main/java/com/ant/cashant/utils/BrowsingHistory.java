package com.ant.cashant.utils;


import com.ant.cashant.App;
import com.ant.cashant.R;
import com.ant.cashant.common.Api;
import com.ant.cashant.common.ApiService;
import com.ant.cashant.common.Contacts;
import com.ant.cashant.inter.OnRequestDataListener;
import com.meituan.android.walle.WalleChannelReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by tantan on 2017/7/14.
 */

public class BrowsingHistory {

    public  void execute(final String prdId) {

        String token = SPUtil.getString(Contacts.TOKEN);
        String channel = WalleChannelReader.getChannel(App.getApp());
        String netError = App.getApp().getString(R.string.app_name);
        Map<String,String> map=new HashMap<>();
        map.put("id",prdId);
        map.put("terminal","0");
        ApiService.GET_SERVICE(Api.APPLY, map, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {

            }

            @Override
            public void requestFailure(int code, String msg) {

            }

            @Override
            public void onFinish() {

            }
        });


    }
}
