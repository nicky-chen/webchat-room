package com.amayadream.webchat.service;

import java.util.List;

import com.amayadream.webchat.pojo.User;

/**
 * @author nicky_chin
 * @description:
 * @date: 2020/3/12 上午9:58
 * @since JDK 1.8
 */
public interface IUserService {
    List<User> selectAll(int page, int pageSize);

    User selectUserByUserid(String userid);

    int selectCount(int pageSize);

    boolean insert(User user);

    boolean update(User user);

    boolean delete(String userid);
}
