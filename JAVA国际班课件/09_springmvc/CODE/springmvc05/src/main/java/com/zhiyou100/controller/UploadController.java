package com.zhiyou100.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping
public class UploadController {

    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 这个路径相对当前应用的目录
            String uploadPath = request.getServletContext().getRealPath("") + File.separator + "upload";
            // 获取文件名
            String fileName = file.getOriginalFilename();
            String filePath = uploadPath + File.separator + fileName;
            // 创建文件
            File localFile = new File(filePath);
            // 将文件写入磁盘
            file.transferTo(localFile);
            result.put("url", filePath);
            result.put("success", Boolean.TRUE);
        } catch (IOException e) {
            result.put("success", Boolean.FALSE);
            e.printStackTrace();
        }
        return result;
    }
}
