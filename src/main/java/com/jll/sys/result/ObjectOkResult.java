package com.jll.sys.result;

/**
 * Created by Daffy on 2019/7/11.
 */
public class ObjectOkResult extends BaseResult {
    public String message = "success";
    public Object data;

    public ObjectOkResult() {
        super();
    }

    public ObjectOkResult(Object data) {
        super();
        this.data = data;
    }
}