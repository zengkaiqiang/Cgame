package com.module.webservice.http;

import java.io.Serializable;

/**
 * Created by zjj
 */
public class HttpResult<T> implements Serializable {

    private String code;
    private String message;
    private String em;
    private String ec;
    private T Data;
    private boolean success;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        this.Data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public String getEm() {
        return em;
    }

    public void setEm(String em) {
        this.em = em;
    }

    public String getEc() {
        return ec;
    }

    public void setEc(String ec) {
        this.ec = ec;
    }


}
