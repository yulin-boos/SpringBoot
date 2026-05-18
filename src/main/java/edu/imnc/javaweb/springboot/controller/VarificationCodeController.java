package edu.imnc.javaweb.springboot.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class VarificationCodeController {

    @GetMapping("/varificationcode")
    public void varificationCode(HttpSession session, HttpServletResponse response) throws IOException {
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(100, 40, 4, 8);
        session.setAttribute("captcha", captcha.getCode());
        response.setContentType("image/png");
        captcha.write(response.getOutputStream());
    }
}
