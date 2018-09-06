package com.Michael.xianjinka.utils;


import com.Michael.xianjinka.App;
import com.Michael.xianjinka.R;
import com.Michael.xianjinka.common.Api;
import com.Michael.xianjinka.common.ApiService;
import com.Michael.xianjinka.common.Contacts;
import com.Michael.xianjinka.inter.OnRequestDataListener;
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
        String netError = App.getApp().getString(R.string.appname);
        Map<String,String>map=new HashMap<>();
        map.put("token",token);
        map.put("id",prdId);
        map.put("name",netError);
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
