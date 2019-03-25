package com.xxin.chat.socket;


import com.xxin.chat.entity.Message;
import com.xxin.chat.util.JsonUtil;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket")
@Component
public class ChatSocket {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    private String chatId;
    private String from;
    private String to;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<ChatSocket> webSocketSet = new CopyOnWriteArraySet<ChatSocket>();
    private static ConcurrentHashMap<String,ChatSocket>socketMap = new ConcurrentHashMap<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    @OnOpen
    public void onOpen(Session session) {
        try {
           String[] param = session.getQueryString().split("&");
           String from = URLDecoder.decode(param[0],"UTF-8").replace("from=","" );
           String to = URLDecoder.decode(param[1], "UTF-8").replace("to=","" );
           String id = UUID.randomUUID().toString();
           this.chatId=id;
           this.from=from;
           this.to=to;
            if (socketMap.get(from)!=null){
                System.out.println("连接已存在");
                return;
            }
            System.out.println("有新连接加入！"+"***"+from+"***"+"当前在线人数为" + getOnlineCount());
            this.session = session;
            webSocketSet.add(this);     //加入set中
            addOnlineCount();           //在线数加1
            socketMap.put(from,this );
            Message message = new Message();
            message.setFrom("server");
            message.setMsg("成功连接到服务器,建立聊天通道-"+from+"=>"+to);
            message.setTo(to);
            message.setType("text");
            message.setChatId(id);
            sendMessage(JsonUtil.objectToJson(message));
        } catch (IOException e) {
            System.out.println("IO异常");
        }
    }
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        Message msg = JsonUtil.jsonToPojo(message, Message.class);
        for (String id:socketMap.keySet()){
            if (id.equals(msg.getTo())){
                try {
                    socketMap.get(id).sendMessage(message);
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        //   this.session.getBasicRemote().sendText(message);
        this.session.getAsyncRemote().sendText(message);
    }
    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message) throws IOException {
        for (ChatSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        ChatSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        ChatSocket.onlineCount--;
    }
}
