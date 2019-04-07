package cn.edu.buct.service.impl;

import cn.edu.buct.dao.LoginDao;
import cn.edu.buct.entity.*;
import cn.edu.buct.global.*;
import cn.edu.buct.service.LoginService;
import com.alibaba.fastjson.*;
import com.google.code.kaptcha.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service("loginService")
public class LoginServiceimpl implements LoginService {
    @Autowired
    @Qualifier(value = "loginDao")
    private LoginDao loginDao;

    public Verification Decode(String enData, String enKey) {
        Verification user = new Verification();
        try {
            String key = RSAUtils.decryptByPrivateKey(enKey);
            String plaionText = AESUtils.decryptData(key, enData);
            System.out.println(plaionText);
            //HashMap<String, Object> hashMap=JSON.parseObject(plaionText, HashMap.class);
            JSON json;
            json = JSON.parseObject(plaionText);
            Map map = (Map) json;
            String userName = (String) map.get("userName");
            String passWord = (String) map.get("passWord");
            String identifyingCode = (String) map.get("identifyingCode");
            user = new Verification(userName, passWord, identifyingCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean edit(User user) {
        int i = loginDao.update(user);
        return i>0;
    }

    @Override
    public User get(String uid) {
        return loginDao.select(uid);
    }

    @Override
    public User getbyUserName(String un) {
        return loginDao.selectByUserName(un);
    }

    @Override
    public boolean editTimes() {
        int i = loginDao.updateTimes();
        return i>0;
    }

    @Override
    public Map<String, Object> login(HttpSession session, String requestData, String encryptKey) {
        Map<String, Object> map = new HashMap<>();
        String generateCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        System.out.println(requestData);
        System.out.println(encryptKey);
        Verification decodeVerification = new Verification();
        decodeVerification = Decode(requestData, encryptKey);
        String userName = decodeVerification.getUserName();
        String passWord = decodeVerification.getPassWord();
        String secureCode = decodeVerification.getIdentifyingCode();
        System.out.println(userName + passWord + secureCode);
        User user = getbyUserName(userName);//搜索数据库返回密码
        System.out.println("user:" + user);
        String pwFromDatabase = null;
        if (!secureCode.equals(generateCode)) {
            System.out.println("验证码错误，请重试");
            String s = "验证码错误，请重试";
            map.put("msg", s);
            return map;
        }
        if (user == null) {
            Integer countRealateUser = (Integer) session.getAttribute("countRealateUser");
            if (countRealateUser == null) {
                session.setAttribute("countRealateUser", Content.MAX_LOGIN_AMOUNT);//为不存在的用户创建临时登陆次数
                System.out.println("用户名或密码错误，您还有" + Content.MAX_LOGIN_AMOUNT + "次尝试机会");
                String s = "账户或密码错误，本日还剩" + Content.MAX_LOGIN_AMOUNT + "次尝试机会";
                map.put("msg", s);
                return map;
            } else if (countRealateUser == 0) {
                System.out.println("账户锁定，请明日再试");
                String s = "账户锁定，请明日再试";
                map.put("msg", s);
                return map;
            } else {
                countRealateUser--;
                session.setAttribute("countRealateUser", countRealateUser);
                System.out.println("用户名或密码错误，您还有" + countRealateUser + "次尝试机会");
                String s = "用户名或密码错误，您还有" + countRealateUser + "次尝试机会";
                map.put("msg", s);
                return map;
            }
        } else {
            pwFromDatabase = user.getPw();
            if (pwFromDatabase.equals(passWord)) {
                session.setAttribute("user", user);
                System.out.println("成功");
                String s = "成功";
                map.put("msg", s);
                return map;
            } else {
                Integer countRealateUser = user.getTm();
                Integer countRealateSession = (Integer) session.getAttribute("countRealateUser");
                if (countRealateSession != null) {
                    if (countRealateUser >= 0) {
                        if (countRealateUser <= countRealateSession) {
                            countRealateUser = countRealateUser-1;
                            session.setAttribute("countRealateUser", countRealateUser);
                            user.setTm(countRealateUser);
                        } else {
                            if(countRealateSession>0)
                            {
                                countRealateUser=countRealateUser-1;
                                user.setTm(countRealateUser);
                                countRealateUser = countRealateSession-1;
                                if (countRealateSession-1 == 0)
                                {
                                    String s = "账户锁定，请明日再试";
                                    map.put("msg", s);
                                    return map;
                                }
                            }else{
                                if (countRealateUser>0) {
                                    countRealateUser = countRealateUser - 1;
                                    user.setTm(countRealateUser);
                                }
                                countRealateUser=0;
                                String s = "账户锁定，请明日再试";
                                map.put("msg", s);
                                return map;
                            }
                            session.setAttribute("countRealateUser", countRealateUser);
                        }
                    }
                    edit(user);
                    String s = "账户或密码错误，本日还剩" + countRealateUser + "次尝试机会";
                    map.put("msg", s);
                    return map;
                } else {
                    String s = "账户锁定，请明日再试";
                    map.put("msg", s);
                    return map;
                }
            }
        }
    }
}
