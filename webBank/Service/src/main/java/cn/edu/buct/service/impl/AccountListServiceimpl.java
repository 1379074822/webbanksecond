package cn.edu.buct.service.impl;

import cn.edu.buct.dao.AccountListDao;
import cn.edu.buct.entity.*;

import cn.edu.buct.service.AccountListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountListService")
public class AccountListServiceimpl implements AccountListService{
    @Autowired
    @Qualifier(value = "accountListDao")
    private AccountListDao accountListDao;

    @Override
    public boolean update(Account account) {
        int i = accountListDao.update(account);
        return i>0;
    }

    @Override
    public List<Account> get(String cid) {

        return accountListDao.select(cid);
    }

    @Override
    public List<Account> getByUserName(String un) {
        return accountListDao.selectByUserName(un);
    }

    @Override
    public boolean addNewCard(Account account) {
        int i = accountListDao.addCard(account);
        return i>0;
    }
}
