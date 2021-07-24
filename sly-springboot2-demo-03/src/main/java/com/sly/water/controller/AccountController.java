package com.sly.water.controller;

import com.sly.water.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * TODO: 用户账户的控制器类
 *
 * @author leyuan
 * @date 2021/7/22 15:46
 */
@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String userName, String userPwd, Model model, HttpSession session) {
        boolean loginResult = accountService.login(userName, userPwd);
        // 条件成立：登录成功，跳转到主页面
        if(loginResult){
            session.setAttribute("userName",userName);
            return "waterMainMenu";
        } else {
            model.addAttribute("loginFail","用户名或密码错误");
            return "index";
        }
    }
}
