package cn.edu.buct.controller;

import cn.edu.buct.entity.Log;
import cn.edu.buct.global.Content;
import cn.edu.buct.global.TransAndSend;
import cn.edu.buct.service.LogService;
import cn.edu.buct.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
//import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.commons.httpclient.methods.RequestEntity;

@Controller("logController")
public class LogController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private LogService logService;

    @RequestMapping("/getStatus")
    public Map getStatus() {
        Map map = new HashMap();
        List<String> list = Content.getStatus();
        list.add(0, "全部");
        map.put("status", list);
        return map;
    }
    @RequestMapping("/logSearch")
    @ResponseBody
    public Map<String, Object> search(@RequestParam int page, @RequestParam int limit, String logType, String startDate, String endDate) {
        String startDate1 = startDate+" 00:00:00";
        String endDate1 = endDate+" 00:00:00";
        Timestamp startDate2=Timestamp.valueOf(startDate1);
        Timestamp endDate2=Timestamp.valueOf(endDate1);
        System.out.println("Imin");
        Map<String, Object> map = new HashMap<>();
        List<Log> logs;
        List logs2;
        //如果内容为空改为Null,date是必选的
        if (logType == "") {
            logType = null;
        }

//        System.out.println(logType+"controll层");
        //没有操作类型则返回所选时间内所有数据
        if(logType==null){
            logs = logService.getTime(startDate2,endDate2);
//            System.out.println("所有数据。controller");

        }
        else {

            logs = logService.getTypeAndTime(logType,startDate2,endDate2);
//            System.out.println("controller层接收到"+logs);
        }
        //分页操作，直接复制
        if(page * limit -1<logs.size()-1) {
            logs2 = logs.subList((page - 1)*limit,page * limit -1);
        }
        else {
            logs2 = logs.subList((page - 1)*limit,logs.size());
        }
        //渲染表，直接复制
        map.put("code",0);
        map.put("msg","");
        map.put("count",logs.size());
        map.put("data",logs2);
//        System.out.println("student2"+logs2);
        //返回list渲染
        System.out.println(map);
        return map;

    }
    @RequestMapping("/logSearchAll")
    @ResponseBody
    public Map<String, Object> searchAll(@RequestParam int page, @RequestParam int limit) {

        Map<String, Object> map = new HashMap<>();
        List<Log> logs = logService.getUid();
        System.out.println(logs+"!!!");
        List logs2;

        //分页操作，直接复制
        if(page * limit -1<logs.size()-1) {
            logs2 = logs.subList((page - 1)*limit,page * limit -1);
        }
        else {
            logs2 = logs.subList((page - 1)*limit,logs.size());
        }
        //渲染表，直接复制
        map.put("code",0);
        map.put("msg","");
        map.put("count",logs.size());
        map.put("data",logs2);

        //返回list渲染
        System.out.println(map);
        return map;

    }

}
