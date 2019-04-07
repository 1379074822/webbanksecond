package cn.edu.buct.service;

import cn.edu.buct.entity.Account;

import java.util.List;

public interface AccountListService {
    //       Account Decode(String enData, String enKey);
    boolean update(Account account);

    List<Account> get(String cid);

    List<Account> getByUserName(String un);

    boolean addNewCard(Account account);
}
