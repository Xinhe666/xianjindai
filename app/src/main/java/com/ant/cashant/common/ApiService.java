package com.ant.cashant.common;

import com.ant.cashant.utils.SPUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.ant.cashant.App;
import com.ant.cashant.R;
import com.ant.cashant.inter.OnRequestDataListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by apple on 2018/4/13.
 */

public class ApiService {
    /**
     * @param params
     * @param listener
     * banner
     */
    public static void GET_SERVICE(String url, Map<String,String> params, final OnRequestDataListener listener) {
        newExcuteJsonPost(url,params,listener);
    }
    public static void GET_Get(String url, Map<String,String> params,final OnRequestDataListener listener) {
        newExcuteJsonGet(url,params,listener);
    }

    private static void newExcuteJsonPost(String url, Map<String,String> params, final OnRequestDataListener listener){
        final String netError = App.getApp().getString(R.string.error_net);
        String token = SPUtil.getString(Contacts.TOKEN);

        OkGo.<String>post(url)
                .tag(App.getApp())
                .headers("X-Token",token)
                .params(params,false)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body()!=null){
                            try {
                                JSONObject jsonObject=new JSONObject(response.body());
                                int code = jsonObject.getInt("error_code");
                                if(code==0){
                                    listener.requestSuccess(code, jsonObject);
                                }else {
                                    listener.requestFailure(code, jsonObject.getString("error_message"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else {
                            listener.requestFailure(-1, netError);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        listener.requestFailure(-1, netError);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        listener.onFinish();
                    }
                });

    }
    private static void newExcuteJsonGet(String url,  Map<String,String> params,final OnRequestDataListener listener){
        final String netError = App.getApp().getString(R.string.error_net);
        OkGo.<String>get(url)
                .params(params)
                .tag(App.getApp())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body()!=null){
                            try {
                                JSONObject jsonObject=new JSONObject(response.body());
                                int code = jsonObject.getInt("error_code");
                                if(code==0){
                                    listener.requestSuccess(code, jsonObject);
                                }else {
                                    listener.requestFailure(code, jsonObject.getString("error_message"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else {
                            listener.requestFailure(-1, netError);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        listener.requestFailure(-1, netError);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        listener.onFinish();
                    }
                });

    }

}
