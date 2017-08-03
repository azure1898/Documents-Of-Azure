package com.its.common.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.its.common.utils.MyFDFSClientUtils;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@Controller
public class UploadFileServlet extends HttpServlet {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "${adminPath}/UploadFile.do", method = RequestMethod.POST)
	@ResponseBody
	public String upload(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			// 上传文件名
			MultipartFile mf = entity.getValue();
			try {
				JSONObject json = new JSONObject();
				json.put("error", 0);
				json.put("url", MyFDFSClientUtils.get_fdfs_file_url(request,
						MyFDFSClientUtils.uploadFile(request, mf)));
				// json.put("url",
				// "http://192.168.1.99:8888/group1/M00/00/00/wKgBY1l5hdGAKODUAAl5WLU-YRY488.jpg");
				return json.toString();
			} catch (Exception ex) {
				JSONObject json = new JSONObject();
				json.put("error", 1);
				json.put("message", "上传失败");
				return json.toString();
			}

		}
		return null;
	}
}
