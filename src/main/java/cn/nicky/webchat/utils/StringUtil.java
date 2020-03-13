package cn.nicky.webchat.utils;

import java.util.UUID;

/**
 * @author nicky_chin
 * @description:
 * @date: 2020/3/12 上午9:58
 * @since JDK 1.8
 */
public class StringUtil {

    public static String getGuid() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "").toLowerCase();
    }

}
