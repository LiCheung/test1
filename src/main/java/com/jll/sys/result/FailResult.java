package com.jll.sys.result;

/**
 * Created by Daffy on 2019/7/11.
 */
public class FailResult extends BaseResult {
    public int code = 404;
    public String[] details = null;

    public FailResult() {
        super();
    }

    public FailResult(int code,String message, String[] details) {
        super();
        this.code = code;
        super.message = message;
        this.details = details;
    }
}