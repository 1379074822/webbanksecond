package cn.edu.buct.controller;

import cn.edu.buct.entity.Log;
import cn.edu.buct.entity.User;
import cn.edu.buct.global.TransAndSend;
import cn.edu.buct.service.LogService;
import cn.edu.buct.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
//import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.commons.httpclient.methods.RequestEntity;

@Controller("globalController")
public class GlobalController {
    @Autowired
    private LogService logService;
    @Autowired
    private LoginService loginService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "loginUI";
    }

    @RequestMapping("/index")
    public String toIndex() {
        return "index";
    }

    @RequestMapping("/quit")
    public String quit(HttpSession session) {
        User user =(User) session.getAttribute("user");
        String uid = user.getUid();
        Log log = new Log(null, TransAndSend.getsnum(),uid,"登出","成功",new Timestamp(System.currentTimeMillis()),"success");
        logService.add(log);
        session.setAttribute("user", null);
        return "redirect:to_login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> submit(HttpSession session, @RequestParam String requestData, @RequestParam String encryptKey) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map = loginService.login(session, requestData, encryptKey);
        User user =(User) session.getAttribute("user");
        String uid = user.getUid();
        Log log = new Log(null, TransAndSend.getsnum(),uid,"登陆","成功",new Timestamp(System.currentTimeMillis()),"success");
        logService.add(log);
        return map;
    }

}
