package cn.edu.buct.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface CaptchaService {
    void getCaptchaCode(HttpServletRequest request, HttpServletResponse response);
}
