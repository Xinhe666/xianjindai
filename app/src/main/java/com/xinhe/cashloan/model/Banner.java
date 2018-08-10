package com.xinhe.cashloan.model;

import java.io.Serializable;

/**
 * Created by apple on 2018/8/9.
 */

public class Banner implements Serializable{

    /**
     * advername : 身份贷
     * pictrue : http://or2eh71ll.bkt.clouddn.com/153259035133370.jpg
     * app : https://m.rong360.com/express?from=sem32&utm_source=youyu&utm_medium=cpa&utm_campaign=sem32_xinhe2
     */

    private String advername;
    private String pictrue;
    private String app;

    public String getAdvername() {
        return advername;
    }

    public void setAdvername(String advername) {
        this.advername = advername;
    }

    public String getPictrue() {
        return pictrue;
    }

    public void setPictrue(String pictrue) {
        this.pictrue = pictrue;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }
}
