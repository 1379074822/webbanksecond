package cn.edu.buct.global;

//import org.omg.PortableServer.LIFESPAN_POLICY_ID;
//import sun.rmi.log.LogInputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Content {
    public final static Integer MAX_LOGIN_AMOUNT = 3;

    public final static String LOGIN = "登陆";
    public final static String LOGOUT = "登出";
    public final static String ADD_CARD = "加挂";
    public final static String AccountBalance = "查询余额";
    public static List getStatus(){
        List list = new ArrayList();
        list.add(LOGIN);
        list.add(LOGOUT);
        list.add(ADD_CARD);
        list.add(AccountBalance);
        return list;
    }
}
