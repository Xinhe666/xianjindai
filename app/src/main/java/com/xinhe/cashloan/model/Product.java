package com.xinhe.cashloan.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by apple on 2018/8/9.
 */

public class Product implements Serializable {

    /**
     * id : 171
     * url : https://api.91jiekuan.com/index/register?channel=anwenqianbao216&subchannel=002
     * min_algorithm : 0.017
     * fastest_time : 24小时之内
     * minimum_amount : 5000
     * maximum_amount : 200000
     * interest_algorithm : 1
     * online : 1
     * labels : [{"id":2,"name":"实名手机","font":"#fb8189","background":"#13a3ff","number":0,"status":1,"created_at":"2017-07-28 04:08:33","updated_at":"2017-07-28 04:08:33","pivot":{"product_id":171,"labels_id":2}},{"id":1,"name":"身份证","font":"#fb8189","background":"#13a3ff","number":0,"status":1,"created_at":"2017-07-27 08:30:50","updated_at":"2017-07-28 04:09:15","pivot":{"product_id":171,"labels_id":1}}]
     * apply : 342.3万
     * p_name : 借东风
     * p_logo : http://or2eh71ll.bkt.clouddn.com/153197004329405.png
     * p_desc : 极速大额借款，资料真实即可
     */

    private String id;
    private String url;
    private double min_algorithm;
    private String fastest_time;
    private int minimum_amount;
    private int maximum_amount;
    private String interest_algorithm;
    private int online;
    private String apply;
    private String p_name;
    private String p_logo;
    private String p_desc;
    private List<LabelsBean> labels;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getMin_algorithm() {
        return min_algorithm;
    }

    public void setMin_algorithm(double min_algorithm) {
        this.min_algorithm = min_algorithm;
    }

    public String getFastest_time() {
        return fastest_time;
    }

    public void setFastest_time(String fastest_time) {
        this.fastest_time = fastest_time;
    }

    public int getMinimum_amount() {
        return minimum_amount;
    }

    public void setMinimum_amount(int minimum_amount) {
        this.minimum_amount = minimum_amount;
    }

    public int getMaximum_amount() {
        return maximum_amount;
    }

    public void setMaximum_amount(int maximum_amount) {
        this.maximum_amount = maximum_amount;
    }

    public String getInterest_algorithm() {
        return interest_algorithm;
    }

    public void setInterest_algorithm(String interest_algorithm) {
        this.interest_algorithm = interest_algorithm;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_logo() {
        return p_logo;
    }

    public void setP_logo(String p_logo) {
        this.p_logo = p_logo;
    }

    public String getP_desc() {
        return p_desc;
    }

    public void setP_desc(String p_desc) {
        this.p_desc = p_desc;
    }

    public List<LabelsBean> getLabels() {
        return labels;
    }

    public void setLabels(List<LabelsBean> labels) {
        this.labels = labels;
    }

    public static class LabelsBean implements Serializable{
        /**
         * id : 2
         * name : 实名手机
         * font : #fb8189
         * background : #13a3ff
         * number : 0
         * status : 1
         * created_at : 2017-07-28 04:08:33
         * updated_at : 2017-07-28 04:08:33
         * pivot : {"product_id":171,"labels_id":2}
         */

        private int id;
        private String name;
        private String font;
        private String background;
        private int number;
        private int status;
        private String created_at;
        private String updated_at;
        private PivotBean pivot;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFont() {
            return font;
        }

        public void setFont(String font) {
            this.font = font;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public PivotBean getPivot() {
            return pivot;
        }

        public void setPivot(PivotBean pivot) {
            this.pivot = pivot;
        }

        public static class PivotBean implements Serializable{
            /**
             * product_id : 171
             * labels_id : 2
             */

            private int product_id;
            private int labels_id;

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public int getLabels_id() {
                return labels_id;
            }

            public void setLabels_id(int labels_id) {
                this.labels_id = labels_id;
            }
        }
    }
}
