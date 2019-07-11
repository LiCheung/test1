package com.jll.externalconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * Created by Daffy on 2019/7/10.
 */
@Component
@ConfigurationProperties(prefix="camera")
public class SettingTest {
    private String uploadPath;

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
