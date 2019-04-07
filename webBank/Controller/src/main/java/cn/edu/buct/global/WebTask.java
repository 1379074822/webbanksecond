package cn.edu.buct.global;

import cn.edu.buct.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WebTask {
@Autowired
private LoginService loginService;
    // 每天24点刷新次数
    @Scheduled(cron = "0 0 24 * * ?")
    public void TaskJob() {
        loginService.editTimes();
    }
}