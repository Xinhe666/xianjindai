package com.ant.cashant.common;

/**
 * Created by apple on 2018/8/8.
 */

public interface Contacts {

    String PHONE = "phone";
    String TOKEN = "token";


    /** 启动页显示时间 **/
    int LAUCHER_DIPLAY_MILLIS = 2000;
    /** 倒计时时间 **/
    int MILLIS_IN_TOTAL = 60000;
    /** 时间间隔 **/
    int COUNT_DOWN_INTERVAL = 1000;


    interface KEY {
        /**
         * Umeng
         **/
        String ANALYTICS_KEY = "5b3b365bf29d9834f5000021";
        String PUSH_KEY = "aad126dc2e5103320048f84e0c382067";

        /**
         * Cloud
         **/
        String LEAN_KEY = "1Xractc2sikvNt5m6r9GP7sK";
        String LEAN_ID = "9zeHS4F8GAHi97Fkg0p80FYV-gzGzoHsz";
    }
}
