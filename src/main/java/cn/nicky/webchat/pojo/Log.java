package cn.nicky.webchat.pojo;

import org.springframework.stereotype.Repository;

import lombok.Data;

/**
 * @author nicky_chin
 * @description:
 * @date: 2020/3/12 上午9:58
 * @since JDK 1.8
 */
@Data
@Repository(value = "log")
public class Log {
    private String id;      //日志编号
    private String userid;  //用户名
    private String time;    //时间
    private String type;    //类型
    private String detail;  //详情
    private String ip;      //ip地址
}
