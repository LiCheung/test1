package com.jll.sys.exception;

import com.jll.sys.result.FailResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Daffy on 2019/7/11.
 */
@RestControllerAdvice
public class ExceptionHandleAdvice {

    @ExceptionHandler(value=Throwable.class)
    public FailResult exception(Throwable t){
        int code = 500;
        if (t instanceof NoRecordException) {
            code = 404;
        }
//		else if (t instanceof NoHandlerFoundException) {
        else if (t instanceof HttpRequestMethodNotSupportedException){
            code = 404;
        }

        StackTraceElement[] steArr = t.getStackTrace();
        String[] details = new String[steArr.length];
        for (int i = 0; i < details.length; i++) {
            details[i] = steArr[i].toString();
        }
        FailResult r = new FailResult(code, t.getMessage(), details);
        return r;
    }
}