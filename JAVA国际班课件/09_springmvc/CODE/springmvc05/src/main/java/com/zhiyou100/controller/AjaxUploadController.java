package com.zhiyou100.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping
public class AjaxUploadController {

    @ResponseBody
    @RequestMapping(value="/ajaxupload", method=RequestMethod.POST)
    public Map<String,Boolean> upload(@RequestParam MultipartFile uploadFile){
        Map<String,Boolean> result = new HashMap<>();
        try {
            // 获取文件名
            String fileName = uploadFile.getOriginalFilename();
            String filePath = "D:\\upload\\" + fileName;
            // 创建文件
            File newFile = new File(filePath);
            // 将文件写入磁盘
            uploadFile.transferTo(newFile);
            result.put("success",Boolean.TRUE);
        } catch (IOException e) {
            e.printStackTrace();
            result.put("success",Boolean.FALSE);
        }
        return result;
    }
}
