package cn.edu.buct.service;

import cn.edu.buct.entity.User;
import cn.edu.buct.entity.Verification;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface LoginService {
    Verification Decode(String enData, String enKey);
    boolean edit(User user);
    User get(String uid);
    User getbyUserName(String un);
    boolean editTimes();
    Map<String, Object> login(HttpSession session, String requestData, String encryptKey);
}
