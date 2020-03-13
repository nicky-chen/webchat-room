package cn.nicky.webchat.utils;

import cn.nicky.webchat.pojo.Log;

/**
 * @author nicky_chin
 * @description:
 * @date: 2020/3/12 上午9:58
 * @since JDK 1.8
 */
public class LogUtil {

    public Log setLog(String userid, String time, String type, String detail, String ip) {
        Log log = new Log();
        log.setUserid(userid);
        log.setTime(time);
        log.setType(type);
        log.setDetail(detail);
        log.setId(ip);
        return log;
    }

}
