package cn.buct.edu;

import cn.edu.buct.dao.LogDao;
import cn.edu.buct.entity.Log;
import cn.edu.buct.global.TransAndSend;
import cn.edu.buct.service.LogService;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RunWith(Arquillian.class)
public class LogDaoTest extends UnitTestBase {
    @Autowired
    private LogDao logDao;

    private TransAndSend transAndSend;
    @Autowired
    private LogService logService ;
//    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//                .addClass(LogDao.class)
//                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//    }
//    @Test
//    @Transactional
//    public void testselectUser(){
//        List<Log> user= logDao.selectAllByUserId("3");
//        System.out.println(user);//直接打印在控制台
//    }
//    @Test
//    @Transactional
//    @Rollback(true)
//    public void testinsertUser() {
//        Log log = new Log();
//        log.setUid("2");
//        log.setSta("succc");
//        log.setSnum("asdasd");
//        log.setRs("sad");
//        log.setFctp("error");
//        log.setLid(88);
//        log.setLc(Timestamp.valueOf("1993-11-13 12:12:12"));
//        int num = logDao.insert(log);
//        assert (num == 1);
//        System.out.println("insert ：" + num + " rows!");
//    }

//    }
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void testupdateUser() {
//        Log log = new Log();
//        log.setUid("57");
//        log.setLid(1);
//
//        int num = logDao.update(log);
//        assert(num==1);
//        System.out.println("update ：" + num + " rows!");
//    }
        @Test
        @Transactional
        public void testHttp(){
            String rs= transAndSend.createGetAccountBalanceJson("6218129087231776 ");
            String ll = "{\"Header\":" +
                    "{\"MESSNO\":\"BF4CE7ACC9AA402CAC6BE5CE6E4186CB\"," +
                    "\"RQ-TIME\":\"2019-30-31 16:30:01\"," +
                    "\"PKG\":\"GetBalance\"}," +
                    "\"Body\":" +
                    "{\"accountNo\":\"6218129084844853\"}" +
                    "}";


            String returnStr = TransAndSend.post("http://118.25.143.204:8080/HostSimulator/GetAccountBalance.do",ll);
            JSONObject jsonObject = JSONObject.parseObject(returnStr);
            Map<String,Object> returnJson = (Map<String,Object>)jsonObject;
            Map<String,Object> return2=(Map<String,Object>) returnJson.get("Body");
            System.out.println(return2.get("BALANCE"));
        }
//    @Test
//    @Transactional
//    public void testselectUser(){
//        Timestamp startDate = Timestamp.valueOf("1998-1-1 12:00:00");
//        Timestamp endDate =Timestamp.valueOf("2000-1-1 12:00:00");
//        List<Log> user= logDao.selectAllByTime(startDate,endDate);
//        System.out.println(user);//直接打印在控制台
//    }
//@Test
//@Transactional
//public void testselectUser1(){
//
//    List<Log> user= logDao.selectAllByType("error");
//    System.out.println(user);//直接打印在控制台
//}

    @Test
    @Transactional
    public void testselectUser(){
        List<Log> user= logService.getType("error");
        System.out.println(user);//直接打印在控制台
    }



    @Test
    @Transactional
    public void testselectcoll(){
            int page = 1;
            int limit = 20;
            String fctp ="登出";
        Timestamp startDate=Timestamp.valueOf("1900-1-1 12:12:12");
        Timestamp endDate=Timestamp.valueOf("2000-1-1 12:12:12");


        Map<String,Object> map = new HashMap<>();
        List<Log> logs = logService.getUid();
        System.out.println(logs);
        List logs2;
        //如果内容为空改为Null,date是必选的
        if(fctp=="") {
            fctp=null;
        }
//        System.out.println(fctp+"controll层");
        //没有操作类型则返回所选时间内所有数据
        if(fctp==null){
            logs = logService.getTime(startDate,endDate);
//            System.out.println("所有数据。controller");

        }
        else {

            logs = logService.getTypeAndTime(fctp,startDate,endDate);
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

        System.out.println(map);//直接打印在控制台
    }
}
