package cn.edu.buct.dao;

import cn.edu.buct.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("accountListDao")
public interface AccountListDao {
    Integer update(Account account);
    List<Account> select(String customerID);
    List<Account> selectByUserName(String userName);
    Integer addCard(Account account);
}