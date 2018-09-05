package com.ant.cashant.ui.activity.login;



/**
 * - @Author:  闫世豪
 * - @Time:  2018/8/8 下午4:22
 * - @Email whynightcode@gmail.com
 */
public interface LoginMvpView {

    void loginSuccess(String token);

    void loginFaild(String error);

    String getAccount();

    String getPassward();

    void showDialog();

    void isNewUser(int isolduser, String token);

    void getCodeSuccess();

    void getCodeFaild(String error);
}
