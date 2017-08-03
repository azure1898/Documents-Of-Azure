/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/"></a> All rights reserved.
 */
package com.its.modules.module.web;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.its.common.config.Global;
import com.its.common.web.BaseController;

import net.sf.json.JSONObject;

/**
 * 文件上传
 *
 * @author ThinkGem
 * @version 2013-5-29
 */
@Controller
@RequestMapping(value = "${frontPath}/front")
public class FrontUploadController extends BaseController {

    /*
     * 采用file.Transto 来保存上传的文件
     */
    @RequestMapping("fileUpload")
    public void fileUpload2(@RequestParam(value = "file", required = false) CommonsMultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, Object> map = new HashMap<>();
        // String path =
        // request.getSession().getServletContext().getRealPath("/frontUpload");
        // 文件名后缀
        String ext = "";
        String orginalName = file.getOriginalFilename();
        int pot = orginalName.lastIndexOf(".");
        if (pot != -1) {
            ext = orginalName.substring(pot);
        }
        String fileName = new Date().getTime() + ext;
        String frontUploadtPath = Global.getConfig("frontUpload") + Global.getConfig("userfiles.basedir");
        File targetFile = new File(frontUploadtPath, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        // 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(targetFile);
        map.put("fileUrl", Global.getConfig("userfiles.basedir") + fileName);
        String result = JSONObject.fromObject(map).toString();
        response.getWriter().write(result);
    }
}
