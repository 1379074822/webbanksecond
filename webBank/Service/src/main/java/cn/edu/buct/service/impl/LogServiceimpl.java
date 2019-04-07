package cn.edu.buct.service.impl;

import cn.edu.buct.dao.LogDao;
import cn.edu.buct.entity.Log;
import cn.edu.buct.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
@Service("LogService")
public class LogServiceimpl implements LogService {
    @Autowired
    @Qualifier(value = "logDao")
    private LogDao logDao ;
    @Override
    public List<Log> getUid() {
        return logDao.selectAllByUserId();
    }

    @Override
    public boolean edit(Log log) {
        int i = logDao.update(log);
        return i>0;
    }

    @Override
    public int add(Log log) {
        int i = logDao.insert(log);

        return i;
    }

    @Override
    public List<Log> getTime(Timestamp startDate, Timestamp endDate) {
        return logDao.selectAllByTime(startDate,endDate);
    }

    @Override
    public List<Log> getType(String fctp) {
        return logDao.selectAllByType(fctp);
    }

    @Override
    public List<Log> getTypeAndTime(String fctp, Timestamp startDate, Timestamp endDate) {
        return logDao.selectAllByTypeAndTime(fctp,startDate,endDate);
    }

}
