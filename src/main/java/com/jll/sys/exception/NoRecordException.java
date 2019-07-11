package com.jll.sys.exception;

/**
 * Created by Daffy on 2019/7/11.
 */
public final class NoRecordException extends RuntimeException {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    public NoRecordException(String message) {
        super(message);
    }

}