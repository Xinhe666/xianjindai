package com.ant.cashant.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by apple on 2018/8/13.
 */

public class ProductDetail implements Serializable {

    /**
     * id : 155
     * pl_id : 1
     * link :
     * minimum_amount : 1000
     * maximum_amount : 5000
     * use : 秒贷现金、还信用卡、消费分期
     * crowd : 上班族 逍遥客 企业主
     * fastest : 0
     * fastest_type : 0
     * review : 自动审核
     * index : 5
     * interest_algorithm : 0
     * min_algorithm : 0.030
     * max_algorithm : 0.030
     * min_cycle : 14
     * max_cycle : 14
     * text_supplement : 0
     * fee : 0.00
     * fee_proportion : 0.00
     * fee_type : 0
     * fee_max : 0.00
     * fee_text :
     * actual_account : 全部到账
     * repayment : 等额本金
     * repayment_channels : APP还款
     * prepayment : 可以
     * expected_algorithm :
     * raiders_connection :
     * product_details : 1、20-37周岁
     2、不查征信，不需要芝麻分
     3、客服电话：4006551962
     * created_at : 2018-07-04 18:59:58
     * updated_at : 2018-08-07 16:45:59
     * status : 1
     * prepayments :
     * admin_users_id : 0
     * online : 2
     * activity : 1
     * ad_image :
     * admin_id : 0
     * repayments :
     * actual_accounts :
     * create_time : 2018-07-04 18:59:58
     * time : 2018-08-07 16:45:59
     * fastest_time : 10分钟
     * username : 雁梅
     * vip_level : 0
     * is_large : 1
     * url : http://pt.xjbaitiao.com/h5/guide/xjbt1/index.html?source_tag=xjbt_anwenqianbao_01
     * labels : [{"id":2,"name":"实名手机","font":"#fb8189","background":"#13a3ff","number":0,"status":1,"created_at":"2017-07-28 04:08:33","updated_at":"2017-07-28 04:08:33","pivot":{"product_id":155,"labels_id":2}},{"id":1,"name":"身份证","font":"#fb8189","background":"#13a3ff","number":0,"status":1,"created_at":"2017-07-27 08:30:50","updated_at":"2017-07-28 04:09:15","pivot":{"product_id":155,"labels_id":1}}]
     * apply : 509.2万
     * p_name : 现金白条
     * p_logo : http://or2eh71ll.bkt.clouddn.com/153250520771915.png
     * p_desc : 借钱不愁，现金2000秒到账
     * crowds : ["1","3","4"]
     */

    private String id;
    private int pl_id;
    private String link;
    private int minimum_amount;
    private int maximum_amount;
    private String use;
    private String crowd;
    private int fastest;
    private int fastest_type;
    private String review;
    private int index;
    private String interest_algorithm;
    private String min_algorithm;
    private String max_algorithm;
    private int min_cycle;
    private int max_cycle;
    private String text_supplement;
    private String fee;
    private String fee_proportion;
    private int fee_type;
    private String fee_max;
    private String fee_text;
    private String actual_account;
    private String repayment;
    private String repayment_channels;
    private String prepayment;
    private String expected_algorithm;
    private String raiders_connection;
    private String product_details;
    private String created_at;
    private String updated_at;
    private int status;
    private String prepayments;
    private int admin_users_id;
    private int online;
    private int activity;
    private String ad_image;
    private int admin_id;
    private String repayments;
    private String actual_accounts;
    private String create_time;
    private String time;
    private String fastest_time;
    private String username;
    private int vip_level;
    private int is_large;
    private String url;
    private String apply;
    private String p_name;
    private String p_logo;
    private String p_desc;
    private List<LabelsBean> labels;
    private List<String> crowds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPl_id() {
        return pl_id;
    }

    public void setPl_id(int pl_id) {
        this.pl_id = pl_id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getCrowd() {
        return crowd;
    }

    public void setCrowd(String crowd) {
        this.crowd = crowd;
    }

    public int getFastest() {
        return fastest;
    }

    public void setFastest(int fastest) {
        this.fastest = fastest;
    }

    public int getFastest_type() {
        return fastest_type;
    }

    public void setFastest_type(int fastest_type) {
        this.fastest_type = fastest_type;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getInterest_algorithm() {
        return interest_algorithm;
    }

    public void setInterest_algorithm(String interest_algorithm) {
        this.interest_algorithm = interest_algorithm;
    }

    public String getMin_algorithm() {
        return min_algorithm;
    }

    public void setMin_algorithm(String min_algorithm) {
        this.min_algorithm = min_algorithm;
    }

    public String getMax_algorithm() {
        return max_algorithm;
    }

    public void setMax_algorithm(String max_algorithm) {
        this.max_algorithm = max_algorithm;
    }

    public int getMin_cycle() {
        return min_cycle;
    }

    public void setMin_cycle(int min_cycle) {
        this.min_cycle = min_cycle;
    }

    public int getMax_cycle() {
        return max_cycle;
    }

    public void setMax_cycle(int max_cycle) {
        this.max_cycle = max_cycle;
    }

    public String getText_supplement() {
        return text_supplement;
    }

    public void setText_supplement(String text_supplement) {
        this.text_supplement = text_supplement;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getFee_proportion() {
        return fee_proportion;
    }

    public void setFee_proportion(String fee_proportion) {
        this.fee_proportion = fee_proportion;
    }

    public int getFee_type() {
        return fee_type;
    }

    public void setFee_type(int fee_type) {
        this.fee_type = fee_type;
    }

    public String getFee_max() {
        return fee_max;
    }

    public void setFee_max(String fee_max) {
        this.fee_max = fee_max;
    }

    public String getFee_text() {
        return fee_text;
    }

    public void setFee_text(String fee_text) {
        this.fee_text = fee_text;
    }

    public String getActual_account() {
        return actual_account;
    }

    public void setActual_account(String actual_account) {
        this.actual_account = actual_account;
    }

    public String getRepayment() {
        return repayment;
    }

    public void setRepayment(String repayment) {
        this.repayment = repayment;
    }

    public String getRepayment_channels() {
        return repayment_channels;
    }

    public void setRepayment_channels(String repayment_channels) {
        this.repayment_channels = repayment_channels;
    }

    public String getPrepayment() {
        return prepayment;
    }

    public void setPrepayment(String prepayment) {
        this.prepayment = prepayment;
    }

    public String getExpected_algorithm() {
        return expected_algorithm;
    }

    public void setExpected_algorithm(String expected_algorithm) {
        this.expected_algorithm = expected_algorithm;
    }

    public String getRaiders_connection() {
        return raiders_connection;
    }

    public void setRaiders_connection(String raiders_connection) {
        this.raiders_connection = raiders_connection;
    }

    public String getProduct_details() {
        return product_details;
    }

    public void setProduct_details(String product_details) {
        this.product_details = product_details;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPrepayments() {
        return prepayments;
    }

    public void setPrepayments(String prepayments) {
        this.prepayments = prepayments;
    }

    public int getAdmin_users_id() {
        return admin_users_id;
    }

    public void setAdmin_users_id(int admin_users_id) {
        this.admin_users_id = admin_users_id;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public String getAd_image() {
        return ad_image;
    }

    public void setAd_image(String ad_image) {
        this.ad_image = ad_image;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getRepayments() {
        return repayments;
    }

    public void setRepayments(String repayments) {
        this.repayments = repayments;
    }

    public String getActual_accounts() {
        return actual_accounts;
    }

    public void setActual_accounts(String actual_accounts) {
        this.actual_accounts = actual_accounts;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFastest_time() {
        return fastest_time;
    }

    public void setFastest_time(String fastest_time) {
        this.fastest_time = fastest_time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getVip_level() {
        return vip_level;
    }

    public void setVip_level(int vip_level) {
        this.vip_level = vip_level;
    }

    public int getIs_large() {
        return is_large;
    }

    public void setIs_large(int is_large) {
        this.is_large = is_large;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public List<String> getCrowds() {
        return crowds;
    }

    public void setCrowds(List<String> crowds) {
        this.crowds = crowds;
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
         * pivot : {"product_id":155,"labels_id":2}
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
             * product_id : 155
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
