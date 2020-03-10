package com.indestructible_backend.domain;

import com.indestructible_backend.DataSourceHelper.DataSourceContextHolder;

/**
 * @Author Sbaby
 * @Date 2020/03/08 18:00
 * @Version 1.0
 */
public class Response {

    private static final String OK = "ok";
    private static final String ERROR = "error";

    private Meta meta;
    private Object data;

    public Response success() {
        this.meta = new Meta(true, OK, DataSourceContextHolder.getDataSource());
        return this;
    }

    public Response success(Object data) {
        this.meta = new Meta(true, OK, DataSourceContextHolder.getDataSource());
        this.data = data;
        return this;
    }

    public Response failure() {
        this.meta = new Meta(false, ERROR, DataSourceContextHolder.getDataSource());
        return this;
    }

    public Response failure(String message) {
        this.meta = new Meta(false, message, DataSourceContextHolder.getDataSource());
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public Object getData() {
        return data;
    }

    public class Meta {
        private boolean success;
        private String message;
        private String dataSource;

        public Meta(boolean success) {
            this.success = success;
        }

        public Meta(boolean success, String message, String dataSource) {
            this.success = success;
            this.message = message;
            this.dataSource = dataSource;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public String getDataSource() { return dataSource; }
    }

}
