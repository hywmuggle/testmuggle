package com.jhtsoft.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * @author haorenqiang
 * @Describe TODO
 * @Date 2018/10/11
 * @功能
 */
public class Result implements Serializable {
    private Logger logger = LoggerFactory.getLogger(Result.class);


    /**
     * 提示信息
     */
    private String message;

    /**
     * 是否成功
     */
    private String status;

    private String date;

    @Override
    public String toString() {
        String result = null;

        try {
            result = JsonUtil.toJson(this);
        } catch (IOException e) {
            e.printStackTrace();
            result = null;
        }

        logger.info("响应： " + result);

        return result;
    }

    /**
     * 响应数据
     */
    private Object data;

    public static Result buildSuccess(String message) {
        Result result = new Result();
        result.setStatus("200");
        result.setMessage(message);
        result.setDate(UtilDate.formatDateTime(new Date()));

        return result;
    }

    public static Result buildError(String status, String message) {
        Result result = new Result();
        result.setStatus(status);
        result.setMessage(message);
        result.setDate(UtilDate.formatDateTime(new Date()));

        return result;
    }

    public Result addResult(Object data) {
        this.setData(data);
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
