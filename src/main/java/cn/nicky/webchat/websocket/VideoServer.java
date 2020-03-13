package cn.nicky.webchat.websocket;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @author nicky_chin
 * @description:websocket服务
 * @date: 2020/3/12 上午9:58
 * @since JDK 1.8
 */
@ServerEndpoint(value = "/videoServer", configurator = HttpSessionConfigurator.class)
public class VideoServer {
    private static final int MAX_CONNECTION = 20;
    private static final long MAX_TIMEOUT = 2 * 60 * 1000;
    private static final Map<String, String> usermap = Collections.synchronizedMap(new HashMap<String, String>());
    private static final Map<String, Session> sessions = Collections.synchronizedMap(new HashMap<String, Session>());

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen");
    }

    @OnMessage
    public void onMessage(String message, Session session) {

    }

    @OnClose
    public void onClose(Session session) {

    }

    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }
}
