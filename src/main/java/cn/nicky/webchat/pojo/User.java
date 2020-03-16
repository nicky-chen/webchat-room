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
@Repository(value = "user")
public class User {
    private String userid;      //用户名
    private String password;    //密码
    private String nickname;    //昵称
    private int sex;            //性别
    private int age;            //年龄
    private String profilehead; //头像
    private String profile;     //简介
    private String firsttime;   //注册时间
    private String lasttime;    //最后登录时间
    private int status;      //账号状态(1正常 0禁用)
}
