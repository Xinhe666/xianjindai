package com.ant.cashant.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by apple on 2018/8/14.
 */

public class DiBean implements Serializable {


    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * phone : 17777407645
         * createdAt : 2018-08-06T00:03:56.050Z
         * updatedAt : 2018-08-06T00:03:56.050Z
         * objectId : 5b67906c808ca4003c8c10b8
         */

        private String phone;
        private String createdAt;
        private String updatedAt;
        private String objectId;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }

        @Override
        public String toString() {
            return "ResultsBean{" +
                    "phone='" + phone + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", updatedAt='" + updatedAt + '\'' +
                    ", objectId='" + objectId + '\'' +
                    '}';
        }

    }

    @Override
    public String toString() {
        return "DiBean{" +
                "results=" + results +
                '}';
    }
}
