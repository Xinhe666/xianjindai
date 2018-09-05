package com.ant.cashant.utils;

import android.widget.Toast;

import com.ant.cashant.App;

/**
 * Created by apple on 2017/4/6.
 */

public class ToastUtils {
    private static Toast toast;

    public static void showToast(String message) {
        if (toast == null) {
            toast = Toast.makeText(App.getApp(), message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }
}
