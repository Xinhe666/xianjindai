package com.xinhe.cashloan.common;

/**
 * Created by apple on 2018/8/8.
 */

public interface Api {


    String HOST = "http://api.anwenqianbao.com/v2/";

    interface Home {
        /**
         * 新品
         **/
        String NEW_PRODUCT = HOST + "vest/onlineProduct";
        /**
         * banner
         **/
        String BANNER = HOST + "vest/banner";
        /**
         * 热门
         **/
        String HOT_PRODUCT = HOST + "vest/hotProduct";

    }

    /**
     * 新or老用户
     **/
    String isOldUser = HOST + "quick/isOldUser";
    /**
     * 验证码获取
     **/
    String CODE = HOST + "sms/getcode";
    /**
     * 验证码效验
     **/
    String CHECKCODE = HOST + "sms/checkCode";

}
