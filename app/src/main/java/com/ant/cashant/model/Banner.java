package com.ant.cashant.model;

import java.io.Serializable;

/**
 * Created by apple on 2018/8/9.
 */

public class Banner implements Serializable{
    /**
     * url : http://or2eh71ll.bkt.clouddn.com/150959031673602.png?e=1509593916&token=Npg7Sanmf4z8uv3mvwwffjOvoCMYN8Ezm4T8pDrC:BUVXzWa8usjIHIN32vGDmeMxmE8=
     * app_url : null
     * h5_link : http://www.pinganzhiyuan.com/panda_loan_mobile_web/#/landing/hdy1
     * name :
     */

    private String url;
    private Object app_url;
    private String h5_link;
    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getApp_url() {
        return app_url;
    }

    public void setApp_url(Object app_url) {
        this.app_url = app_url;
    }

    public String getH5_link() {
        return h5_link;
    }

    public void setH5_link(String h5_link) {
        this.h5_link = h5_link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
