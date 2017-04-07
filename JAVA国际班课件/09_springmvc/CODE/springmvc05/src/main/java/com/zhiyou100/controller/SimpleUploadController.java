package com.zhiyou100.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by lijike 2016/12/16.
 */
@Controller
@RequestMapping
public class SimpleUploadController {

    /**
     * 单个文件上传
     * @param uploadFile
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/upload1", method = RequestMethod.POST)
    public String upload(@RequestParam("uploadFile") MultipartFile uploadFile, HttpServletRequest request)
            throws Exception {

        // 获取文件名
        String fileName = uploadFile.getOriginalFilename();
        String filePath = "D:\\upload\\" + fileName;
        // 创建文件
        File newFile = new File(filePath);
        // 将文件写入磁盘
        uploadFile.transferTo(newFile);
        return "success";
    }

    @RequestMapping(value = "/upload2", method = RequestMethod.POST)
    public String upload(@RequestParam MultipartFile[] myfiles,HttpServletRequest request)  {

        for(MultipartFile file : myfiles){
            //此处MultipartFile[]表明是多文件,如果是单文件MultipartFile就行了
            if(file.isEmpty()){
                System.out.println("文件未上传!");
            }
            else{
                //得到上传的文件名
                String fileName = file.getOriginalFilename();
                String filePath = "D:\\upload\\" + fileName;
                File localFile = new File(filePath);
                try {
                    file.transferTo(localFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "success";
    }
}
