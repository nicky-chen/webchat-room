package cn.nicky.webchat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import cn.nicky.webchat.pojo.Log;

/**
 * @description:
 *
 * @author nicky_chin
 * @date: 2020/3/12 上午9:58
 *
 * @since JDK 1.8
 */
@Service(value = "logDao")
public interface ILogDao {
    List<Log> selectAll(@Param("offset") int offset, @Param("limit") int limit);

    List<Log> selectLogByUserid(@Param("userid") String userid, @Param("offset") int offset, @Param("limit") int limit);

    Log selectCount();

    Log selectCountByUserid(@Param("userid") String userid);

    boolean insert(Log log);

    boolean delete(String id);

    boolean deleteThisUser(String userid);

    boolean deleteAll();
}
