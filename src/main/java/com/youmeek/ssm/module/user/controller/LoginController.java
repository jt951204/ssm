package com.youmeek.ssm.module.user.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
    @RequestMapping("/login")
    public ModelAndView login(String loginname, String password,ModelAndView mv) {

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(loginname, password);
        token.setRememberMe(true);

        try {
            subject.login(token);

            mv.setViewName("redirect:/main");
        } catch (ExcessiveAttemptsException e) {
            // 设置登录失败提示信息
            mv.addObject("message", "失败超过5次，暂停账号登录");
            // 服务器内部跳转到登录页面
            mv.setViewName("forward:/loginForm");
        } catch (AuthenticationException e) {
            // 设置登录失败提示信息
            mv.addObject("message", "登录名或密码错误!请重新输入");
            // 服务器内部跳转到登录页面
            mv.setViewName("forward:/loginForm");
        }
        return mv;
    }
}
