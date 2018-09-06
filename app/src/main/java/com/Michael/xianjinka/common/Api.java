package com.Michael.xianjinka.common;

/**
 * Created by apple on 2018/8/8.
 */

public interface Api {


    String HOST = "http://api.anwenqianbao.com/v2/";

    String CREDIT = HOST + "vip/creditCard";
    String APPLY=HOST+"vest/apply";
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
        /**
         * 我要办卡
         */
        String BANK = HOST + "vest/projectLink";
        /**
         * 精品推荐
         */
        String RECOMMEND = HOST + "vest/recommendProduct";

        /**
         * 产品详情
         */
        String PRODUCT_DETAIL = HOST + "vest/detail";

        /**
         * 产品筛选
         */
        String PRODUCT_SCREEN = HOST + "vest/screening";
        /**
         * 急速
         */
        String TOP_PRODUCT=HOST+"vest/topProduct";

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
