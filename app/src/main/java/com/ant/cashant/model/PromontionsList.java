package com.ant.cashant.model;

import java.io.Serializable;
import java.util.List;

public class PromontionsList implements Serializable {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 7
         * product_id : 13
         * product_name : 菠萝贷
         * product_logo : http://or2eh71ll.bkt.clouddn.com/152699508859237.png
         * min_loan : null
         * max_loan : null
         * create_at : 2018-05-22 21:31:55
         * update_at : 2018-05-22 21:31:55
         * h5_link :
         * apply_num : 8705
         */

        private int id;
        private String product_id;
        private String product_name;
        private String product_logo;
        private Object min_loan;
        private Object max_loan;
        private String create_at;
        private String update_at;
        private String h5_link;
        private int apply_num;
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
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

        public Object getMin_loan() {
            return min_loan;
        }

        public void setMin_loan(Object min_loan) {
            this.min_loan = min_loan;
        }

        public Object getMax_loan() {
            return max_loan;
        }

        public void setMax_loan(Object max_loan) {
            this.max_loan = max_loan;
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

        public String getH5_link() {
            return h5_link;
        }

        public void setH5_link(String h5_link) {
            this.h5_link = h5_link;
        }

        public int getApply_num() {
            return apply_num;
        }

        public void setApply_num(int apply_num) {
            this.apply_num = apply_num;
        }
    }
}
