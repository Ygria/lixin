package com.xiyoukeji.lixin.util;

/**
 * Created by ygria on 2018/2/5.
 */
public class ErrCodeException extends RuntimeException{

    protected String errcode = "10000";


    public ErrCodeException(String message, Integer code) {
        super(message);
        errcode = code.toString();
    }

    public ErrCodeException(Integer code,String message) {
        super(message);
        errcode = code.toString();
    }

    public ErrCodeException(String message, Integer code, Throwable throwable) {
        super(message, throwable);
        errcode = code.toString();
    }


    public String getErrcode() {
        return errcode;
    }




}
