package cn.nicky.webchat.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.nicky.webchat.dao.IUserDao;
import cn.nicky.webchat.pojo.User;
import cn.nicky.webchat.service.IUserService;

/**
 * @author nicky_chin
 * @description:
 * @date: 2020/3/12 上午9:58
 * @since JDK 1.8
 */
@Service(value = "userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public List<User> selectAll(int page, int pageSize) {
        return userDao.selectAll(page, pageSize);
    }

    @Override
    public User selectUserByUserid(String userid) {
        return userDao.selectUserByUserid(userid);
    }

    @Override
    public int selectCount(int pageSize) {
        int pageCount = Integer.parseInt(userDao.selectCount().getUserid());
        return pageCount % pageSize == 0 ? pageCount / pageSize : pageCount / pageSize + 1;
    }

    @Override
    public boolean insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(String userid) {
        return userDao.delete(userid);
    }
}
