package com.ant.cashant.model;

import java.io.Serializable;

/**
 *
 * @author apple
 * @date 2018/8/9
 *
 */

public class Product implements Serializable {


    /**
     * id : 8
     * product_id : 21
     * product_name : 壁虎岩
     * product_logo : http://or2eh71ll.bkt.clouddn.com/152748723532614.jpeg
     * h5_link : http://bhhl.51jzkj.com/#/proxyReg?inviteCode=A100332
     * create_at : 2017-11-20 23:24:21
     * update_at : 2017-11-20 23:24:21
     */

    private String id;
    private int product_id;
    private String product_name;
    private String product_logo;
    private String h5_link;
    private String create_at;
    private String update_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_logo() {
        return product_logo;
    }

    public void setProduct_logo(String product_logo) {
        this.product_logo = product_logo;
    }

    public String getH5_link() {
        return h5_link;
    }

    public void setH5_link(String h5_link) {
        this.h5_link = h5_link;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }
}
