package cn.edu.buct.controller;

import cn.edu.buct.entity.Account;
import cn.edu.buct.entity.User;
import cn.edu.buct.service.AccountListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("accountListController")

public class AccountListController {
    @Autowired
    private AccountListService accountListService;

    @RequestMapping("/cardList")
    @ResponseBody
    public Map<String,Object> cardList(HttpSession session, @RequestParam Integer page,@RequestParam Integer limit)
    {
        System.out.println("inininininin");
        User user = (User) session.getAttribute("user");
           Map<String, Object> map = new HashMap<>();
        List<Account> accountList = accountListService.get(user.getUid());
        List accountList2;
        if(page * limit -1<accountList.size()-1)
        {
            accountList2 = accountList.subList((page - 1)*limit,page * limit -1);
        }
        else {
            accountList2 = accountList.subList((page - 1)*limit,accountList.size());
        }

//        String str1 = "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":[{\"cardId\":10000,\"currencyType\":\"user-0\",\"sex\":\"***\"},{\"cardId\":12000,\"currencyType\":\"user-0\",\"sex\":\"***\"}]}";
//        map.put("list",str1);

        map.put("code",0);
        map.put("msg","");
        map.put("count",10);
        map.put("data",accountList2);
        System.out.println(map);
        return map;
    }
}
