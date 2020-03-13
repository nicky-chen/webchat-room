package cn.nicky.webchat.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.nicky.webchat.dao.ILogDao;
import cn.nicky.webchat.pojo.Log;
import cn.nicky.webchat.service.ILogService;
import cn.nicky.webchat.utils.StringUtil;

/**
 * @description:
 *
 * @author nicky_chin
 * @date: 2020/3/12 上午9:58
 *
 * @since JDK 1.8
 */
@Service(value = "logService")
public class LogServiceImpl implements ILogService {

    @Resource private ILogDao logDao;
    @Resource private Log log;

    @Override
    public List<Log> selectAll(int page, int pageSize) {
        return logDao.selectAll(page, pageSize);
    }

    @Override
    public List<Log> selectLogByUserid(String userid, int page, int pageSize) {
        int start = 1;
        int end = pageSize;
        if(page != 1) {
            start = pageSize * (page - 1) + 1;
            end = pageSize * page;
        }
        return logDao.selectLogByUserid(userid, start, end);
    }

    @Override
    public int selectCount(int pageSize) {
        int pageCount = Integer.parseInt(logDao.selectCount().getUserid());
        return pageCount % pageSize == 0 ? pageCount/pageSize : pageCount/pageSize + 1;
    }

    @Override
    public int selectCountByUserid(String userid, int pageSize) {
        int pageCount = Integer.parseInt(logDao.selectCountByUserid(userid).getUserid());
        return pageCount % pageSize == 0 ? pageCount/pageSize : pageCount/pageSize + 1;
    }


    @Override
    public boolean insert(Log log) {
        log.setId(StringUtil.getGuid());
        return logDao.insert(log);
    }

    @Override
    public boolean delete(String id) {
        return logDao.delete(id);
    }

    @Override
    public boolean deleteThisUser(String userid) {
        return logDao.deleteThisUser(userid);
    }

    @Override
    public boolean deleteAll() {
        return logDao.deleteAll();
    }
}
