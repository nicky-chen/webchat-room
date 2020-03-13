package com.amayadream.webchat.service;

import java.util.List;

import com.amayadream.webchat.pojo.Log;

/**
 * @author nicky_chin
 * @description:
 * @date: 2020/3/12 上午9:58
 * @since JDK 1.8
 */
public interface ILogService {
    List<Log> selectAll(int page, int pageSize);

    List<Log> selectLogByUserid(String userid, int page, int pageSize);

    int selectCount(int pageSize);

    int selectCountByUserid(String userid, int pageSize);

    boolean insert(Log log);

    boolean delete(String id);

    boolean deleteThisUser(String userid);

    boolean deleteAll();
}
