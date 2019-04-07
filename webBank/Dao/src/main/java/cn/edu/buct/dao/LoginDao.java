package cn.edu.buct.dao;

import cn.edu.buct.entity.User;
import org.springframework.stereotype.Repository;


@Repository("loginDao")
public interface LoginDao {
    Integer update(User user);
    User select(String uid);
    User selectByUserName(String un);
    Integer updateTimes();
}
