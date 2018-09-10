package com.ant.cashant.model;

import java.io.Serializable;

public class RecommProduct implements Serializable {


    /**
     * id : 19
     * product_id : 22
     * sort : 0
     * product_name : 威尔花
     * product_logo : http://or2eh71ll.bkt.clouddn.com/152774721363657.png
     * h5_link : http://www.pengvi.com/weh/index.jsp?channelID=67
     * product_desc : 门槛低，审核快，效率高，放款快
     */

    private String id;
    private int product_id;
    private int sort;
    private String product_name;
    private String product_logo;
    private String h5_link;
    private String product_desc;

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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
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

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }
}
