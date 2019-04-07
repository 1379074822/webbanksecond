package cn.edu.buct.dao;

import cn.edu.buct.entity.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dao.xml")
public class AccountListDaoTest {
    @Autowired
    @Qualifier(value = "accountListDao")
    private AccountListDao accountListDao;

    @Test
    public void test() {
        List<Account> account = accountListDao.select("2015014270");
        System.out.println(account);
    }
}