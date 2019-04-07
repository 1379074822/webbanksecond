package cn.edu.buct.service;

import cn.edu.buct.entity.Log;

import java.sql.Timestamp;
import java.util.List;

public interface LogService {
    List<Log> getUid();
    boolean edit(Log log);
    int add(Log log);
    List<Log>  getTime(Timestamp startDate, Timestamp endDate);
    List<Log>  getType(String fctp);
    List<Log>  getTypeAndTime(String fctp, Timestamp startDate, Timestamp endDate);

}
