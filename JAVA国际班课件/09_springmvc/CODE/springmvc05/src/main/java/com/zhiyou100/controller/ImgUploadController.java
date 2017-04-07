package com.zhiyou100.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class ImgUploadController {

    @ResponseBody
    @RequestMapping(value = "/imgupload", method = RequestMethod.POST)
    public Map<String, Object> upload(@RequestParam("uoloadFile") MultipartFile[] fileList,
                                      HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        // 这个路径相对当前应用的目录
        String uploadPath = request.getServletContext().getRealPath("") + File.separator + "upload";
        List<String> url = new ArrayList<>();
        try {
            for (MultipartFile file : fileList) {
                //此处MultipartFile[]表明是多文件,如果是单文件MultipartFile就行了
                if (file.isEmpty()) {
                    System.out.println("文件未上传!");
                } else {
                    //得到上传的文件名
                    String fileName = file.getOriginalFilename();
                    String filePath = uploadPath + File.separator + fileName;
                    File localFile = new File(filePath);
                    file.transferTo(localFile);
                    url.add("/upload/" + fileName);
                }
            }
            result.put("url",url);
            result.put("success",Boolean.TRUE);
        } catch (IOException e) {
            result.put("success",Boolean.FALSE);
            e.printStackTrace();
        }
        return result;
    }
}
