package com.jll.web;

import java.io.File;

import com.jll.dto.ProfileUploadDTO;
import com.jll.sys.result.BaseResult;
import com.jll.sys.result.ObjectOkResult;
import com.jll.sys.utils.Base64Utils;
import com.jll.externalconfig.SettingTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Daffy on 2019/7/10.
 */
@RestController
@RequestMapping("/api/profile")
@Api(tags= {"profile"})
public class ProfileController {
    @Autowired
    private SettingTest settingTest;

    @ApiOperation(value = "上传拍照头像。", notes = "上传拍照头像。用Base64传输图片内容。", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value="/upload",method=RequestMethod.POST)
    public BaseResult upload(@RequestBody ProfileUploadDTO profileUploadDTO){
        ObjectOkResult r = new ObjectOkResult();
        Integer userId = profileUploadDTO.userId;
        String imgStr = profileUploadDTO.imgStr;
        String basePath = this.settingTest.getUploadPath();
        String filePath = basePath + "/" + userId + ".png";
        Base64Utils.createFile(imgStr, new File(filePath));
        return r;
    }

}
