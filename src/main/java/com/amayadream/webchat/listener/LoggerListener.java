package com.amayadream.webchat.listener;

import javax.servlet.ServletContextEvent;

import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.web.util.Log4jConfigListener;

/**
 * @author nicky_chin
 * @description:
 * @date: 2020/3/12 上午9:58
 * @since JDK 1.8
 */
public class LoggerListener extends Log4jConfigListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        installJulToSlf4jBridge();
        super.contextInitialized(event);
    }

    private void installJulToSlf4jBridge() {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }
}
