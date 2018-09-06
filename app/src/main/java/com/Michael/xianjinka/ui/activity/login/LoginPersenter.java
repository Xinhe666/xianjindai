package com.Michael.xianjinka.ui.activity.login;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.Michael.xianjinka.common.Api;
import com.Michael.xianjinka.common.ApiService;
import com.Michael.xianjinka.inter.OnRequestDataListener;
import com.Michael.xianjinka.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/8/8 下午4:22
 * - @Email whynightcode@gmail.com
 */

public class LoginPersenter {
    private LoginMvpView mLoginMvpView;

    public LoginPersenter(LoginMvpView loginMvpView) {
        mLoginMvpView = loginMvpView;
    }

    /**
     * 判断新老用户
     */
    public void judgment() {
        String account = mLoginMvpView.getAccount();
        Map<String, String> map = new HashMap<>();
        map.put("userphone", account);
        ApiService.GET_SERVICE(Api.isOldUser, map, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {
                try {
                    JSONObject date = json.getJSONObject("data");
                    int i = date.getInt("isolduser");
                    String token = date.getString("token");
                    mLoginMvpView.isNewUser(i, token);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void requestFailure(int code, String msg) {
                ToastUtils.showToast(msg);

            }

            @Override
            public void onFinish() {

            }
        });
    }

    //获取验证吗
    public void getCode() {
        String account = mLoginMvpView.getAccount();
        Map<String, String> map = new HashMap<>();
        map.put("userphone", account);
        ApiService.GET_SERVICE(Api.CODE, map, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {
                try {
                    JSONObject date = json.getJSONObject("data");
                    String string = date.getString("isSuccess");
                    //验证码获取是否成功（1成功 0失败）
                    if ("1".equals(string)) {
                        mLoginMvpView.getCodeSuccess();
                    } else {
                        mLoginMvpView.getCodeFaild(date.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void requestFailure(int code, String msg) {
                ToastUtils.showToast(msg);
            }

            @Override
            public void onFinish() {

            }
        });

    }

    public void verifyCode(final KProgressHUD kProgressHUD) {

        String account = mLoginMvpView.getAccount();
        String passward = mLoginMvpView.getPassward();

        Map<String, String> map = new HashMap<>();
        map.put("userphone", account);
        map.put("code", passward);
        kProgressHUD.show();
        ApiService.GET_SERVICE(Api.CHECKCODE, map, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject json) {
                try {
                    JSONObject date = json.getJSONObject("data");
                    String msg = date.getString("msg");
                    String isSucess = date.getString("isSuccess");
                    String token = date.getString("token");
                    if ("1".equals(isSucess)) {
                        mLoginMvpView.loginSuccess(token);
                    } else {
                        mLoginMvpView.loginFaild(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void requestFailure(int code, String msg) {
                mLoginMvpView.loginFaild(msg);
            }

            @Override
            public void onFinish() {
                if (kProgressHUD.isShowing()) {
                    kProgressHUD.dismiss();
                }
            }
        });

    }
}
