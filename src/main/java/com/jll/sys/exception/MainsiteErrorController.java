package com.jll.sys.exception;

import com.jll.sys.result.FailResult;
import org.springframework.boot.autoconfigure.web.ErrorController;
//import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Daffy on 2019/7/11.
 */
@RestController
public class MainsiteErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(value=ERROR_PATH)
    public FailResult handleError(){
        String[] details = {};
        FailResult r = new FailResult(404, "无法找到该资源", details);
        return r;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}
