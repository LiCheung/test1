package com.jll.sys.result;

/**
 * Created by Daffy on 2019/7/11.
 */
public class ArrayOkResult extends BaseResult {
    public String message = "success";
    public Object[] data;
    public ArrayOkResult() {
        super();
    }
    public ArrayOkResult(Object[] data) {
        super();
        this.data = data;
    }


}