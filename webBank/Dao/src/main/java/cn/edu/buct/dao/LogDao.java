package cn.edu.buct.dao;


import cn.edu.buct.entity.Log;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface LogDao {
    Integer update(Log log);
    List<Log> selectAllByUserId();
    Integer insert(Log log);
    List<Log> selectAllByTime(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);
    List<Log> selectAllByType(String fctp);
    List<Log> selectAllByTypeAndTime(@Param("fctp") String fctp,@Param("startDate") Timestamp startDate, @Param("endDate")Timestamp endDate);
}
