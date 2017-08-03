package com.its.modules.websocket.web;

import com.alibaba.druid.support.json.JSONUtils;
import com.its.modules.websocket.MyWebSocket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/websocket/socket")
public class SocketController {


    /**
     * 发送消息
     *“http://localhost:8080/websocket/socket/sendMsg.json?businessId=2ee44bde50084d899a70c83a59918bad&type=1&title=%E6%A0%87%E9%A2%98&content=%3Ca%20class=%22menu%22%20%20%20%20onclick=%22addTabByMy(%27s%27,%27/goods/goodsInfo%27)%22%3E%E5%86%85%E5%AE%B9%3C/a%3E&href=/goods/goodsInfo”
     * @param businessId 商家ID
     * @param type 类型(其中类型为1表示订单提示，2表示库存提示)
     * @param title 标题
     * @param content 消息内容 例如(<a class="menu" onclick="addTabByMy('tab标题-消息内容','/goods/goodsInfo')">内容</a>)
     * @param href 按钮跳转链接 例如（/goods/goodsInfo）
     */
    @ResponseBody
    @RequestMapping("sendMsg")
    public String sendMsg(String businessId,String type,String title,String content,String href){
        Map<String,Object> map= new HashMap<String,Object>();
        try {
            Session session = (Session) MyWebSocket.userMap.get(businessId);
            map.put("type",type);
            map.put("title",title);
            map.put("content",content);
            map.put("href",href);
            MyWebSocket.sendMessage(session,JSONUtils.toJSONString(map));
            map.clear();
            map.put("result","SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            map.clear();
            map.put("result","ERROR");
        }
        return JSONUtils.toJSONString(map);
    }




    /**
     * 发送消息
     *
     * @param businessId 商家ID  例如（2ee44bde50084d899a70c83a59918bad）
     * @param msgTitle 弹窗的标题 例如（您有新订单了： ；  您有商品即将售空：）
     * @param content 消息内容 例如(<a class="menu" onclick="addTabByMy('tab标题-消息内容','/goods/goodsInfo')">内容</a>)
     * @param buttonName 跳转按钮的名称 例如(进入订单列表;进入商品列表)
     * @param buttonTabTitle 点击按钮时候新建选项卡的标题 例如（tab标题-按钮）
     * @param buttonUrl 按钮跳转链接 例如（/goods/goodsInfo）
     */
    @ResponseBody
    @RequestMapping("sendMsgAuto")
    public String sendMsgAuto(String businessId,String msgTitle,String content,String buttonName,String buttonTabTitle,String buttonUrl){
        Map<String,Object> map= new HashMap<String,Object>();
        try {
            Session session = (Session) MyWebSocket.userMap.get(businessId);
            map.put("msgTitle",msgTitle);
            map.put("buttonTabTitle",buttonTabTitle);
            map.put("content",content);
            map.put("buttonName",buttonName);
            map.put("buttonUrl",buttonUrl);
            MyWebSocket.sendMessage(session,JSONUtils.toJSONString(map));
            map.clear();
            map.put("result","SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            map.clear();
            map.put("result","ERROR");
        }
        return JSONUtils.toJSONString(map);
    }
}
