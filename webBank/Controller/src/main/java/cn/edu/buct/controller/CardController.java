package cn.edu.buct.controller;

import cn.edu.buct.entity.Account;
import cn.edu.buct.entity.Log;
import cn.edu.buct.entity.User;
import cn.edu.buct.global.Content;
import cn.edu.buct.global.Sha;
import cn.edu.buct.global.TransAndSend;
import cn.edu.buct.service.AccountListService;
import cn.edu.buct.service.LogService;
import cn.edu.buct.service.LoginService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
//import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.commons.httpclient.methods.RequestEntity;

@Controller("cardController")
public class CardController {
    @Autowired
    private LogService logService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private AccountListService accountListService;
    private  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/addcard")
    @ResponseBody
    public Map addCard(HttpSession session, @RequestParam String cardNumber,@RequestParam String password) {
        System.out.println("in");
        User user =(User) session.getAttribute("user");
        System.out.println();
        String uid = user.getUid();
        Log log = new Log(null,TransAndSend.getsnum(),uid,"加挂账户","成功",new Timestamp(System.currentTimeMillis()),"success");
        logService.add(log);
        System.out.println(cardNumber+password);
        Map map = new HashMap();

        String passWord = Sha.SHA(password+"BUCT", "SHA-256");
        String json = TransAndSend.createAccountPasswordValidateJson(uid,passWord,cardNumber);
        String returnStr = TransAndSend.post("http://118.25.143.204:8080/HostSimulator/AccountPasswordValidate.do",json);
        JSONObject jsonObject = JSONObject.parseObject(returnStr);
        Map<String,Object> returnJson = (Map<String,Object>)jsonObject;
        Map<String,Object> header =(Map<String, Object>) returnJson.get("Header");
        boolean success = true;
        if(header.get("RS-CODE").equals("000000")) {
            Account account = new Account(null, uid, user.getUn(), password, cardNumber,null);
            success = accountListService.addNewCard(account);
        }
        else{
            log.setRs("失败");
            logService.edit(log);
        }
        if(!success){
            log.setRs("失败");
            logService.edit(log);
        }
        map.put("success",success);
        return map;
    }
    @RequestMapping("/allowance")
    @ResponseBody
    public Map allowance(@RequestParam String cardNumber) {
        System.out.println(cardNumber);
        Map map = new HashMap();
//        User user =(User) session.getAttribute("user");
//        String uid = user.getUid();
//        String passWord = Sha.SHA(password+"BUCT", "SHA-256");
        String json = TransAndSend.createGetAccountBalanceJson(cardNumber);
        String returnStr = TransAndSend.post("http://118.25.143.204:8080/HostSimulator/GetAccountBalance.do",json);
        JSONObject jsonObject = JSONObject.parseObject(returnStr);
        Map<String,Object> returnJson = (Map<String,Object>)jsonObject;
        Map<String,Object> body =(Map<String, Object>) returnJson.get("Body");
        boolean success = true;
        String balance =(String) body.get("BALANCE");
        map.put("balance",balance);
        return map;
    }

}
