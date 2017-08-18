package com.its.modules.websocket;

import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket/remind")
public class WebSocket {
	AtomicInteger atomicInteger = new AtomicInteger(0);// 记录链接个数
	public static final ConcurrentHashMap<String, WebSocket> connections = new ConcurrentHashMap<String, WebSocket>();//存储WebSocket
	public static final ConcurrentHashMap<String, Vector<String>> businessUsers = new ConcurrentHashMap<String,  Vector<String>>();//存储商家用户

	private Session session;
	private String userid;// 记录登录用户
	private String businessId;// 记录登录用户

	@OnOpen
	public void start(Session session) {
		this.session = session;
		atomicInteger.getAndIncrement();
	}

	@OnClose
	public void end() {
		atomicInteger.getAndDecrement();
		connections.remove(userid);
		businessUsers.get(businessId).remove(userid);
	
	}

	/**
	 * 收到消息
	 * @param message
	 */
	@OnMessage
	public void RecvMessage(String message) {
		String[] M = message.split(",");
		this.userid = M[0];//用户
		this.businessId = M[1];
		if(businessUsers.get(businessId)==null)
			businessUsers.put(businessId, new Vector<String>());
		businessUsers.get(businessId).add(userid);
		connections.put(userid, this);
	}

	@OnError
	public void onError(Throwable t) throws Throwable {
		//System.out.println(t.getMessage());
	}

	/**
	 * 消息发送方法
	 * 
	 * @param msg
	 */
	public void sendMessage(String message) {
		try {
			this.session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			connections.remove(userid);
			businessUsers.get(businessId).remove(userid);
			try {
				this.session.close();
			} catch (IOException e1) {
			}
		}
	}
}
