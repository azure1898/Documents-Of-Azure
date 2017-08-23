package com.its.test.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.its.common.utils.StringUtils;

import net.sf.json.JSONObject;

public class testComparator2 {
    public static void main(String[] args) {

        int type = 1;
        String userId = "2";
        String userNickname = "3";
        String atUserNickname = "ffff";
        String content = "ttt";
        String toUserId = "1";
        //sendSms(type, userId, userNickname, atUserNickname, content, toUserId);
        String p= "type="+type+"&userId="+userId+ "&userNickname="+userNickname+ "&atUserNickname="+atUserNickname+ "&content="+content+"&toUserId="+toUserId;
     System.out.println((   sendPost("http://192.168.1.56:8080/ehomeapp/app/msgSend/commentMsg",p)));
    }

  

    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";

        String charset = "UTF-8";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

            if (StringUtils.isBlank(result)) {
                Map<String, String> mm = new HashMap<String, String>();
                mm.put("status", "0");
                mm.put("msg", "服务器异常，请稍后再试！");
                System.out.println(mm);
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}