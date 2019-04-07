package cn.edu.buct.controller;

import cn.edu.buct.service.CaptchaService;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Controller("captchaController")
@RequestMapping("/CaptchaCode")
public class CaptchaController {
    public static Logger logger=LoggerFactory.getLogger( CaptchaController.class);
    @Autowired
    private CaptchaService captchaService;

    @RequestMapping("/getCaptchaCode")
    public ModelAndView getCaptchaCode(HttpServletRequest request, HttpServletResponse response) throws IOException{

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        captchaService.getCaptchaCode(request,response);

        return null;
    }
}
