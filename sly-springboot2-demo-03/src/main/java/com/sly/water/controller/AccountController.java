package com.sly.water.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.sly.water.entities.Account;
import com.sly.water.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * TODO: 用户账户的控制器类
 *
 * @author leyuan
 * @date 2021/7/22 15:46
 */
@Controller
@RequestMapping("/account")
@Slf4j
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    HttpSession httpSession;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String userName, String userPwd, Model model, HttpServletRequest req, HttpServletResponse resp) {
        boolean loginResult = accountService.login(userName, userPwd,httpSession);
        // 条件成立：登录成功，跳转到主页面
        if(loginResult){
            // 将用户名共享到前端
            httpSession.setAttribute("userName",userName);

            Account user = accountService.getAccount(userName);
            httpSession.setAttribute("system_user_session",user);
            Cookie cookie_userName = new Cookie("cookie_userName", userName);
            // 设置cookie的持久化时间，30天
            cookie_userName.setMaxAge( 10 * 60);
            // 设置为当前项目下都携带这个cookie
            cookie_userName.setPath(req.getContextPath()+"/");
            // 向客户端发送cookie
            resp.addCookie(cookie_userName);
            return "waterMainMenu";
        } else {
            model.addAttribute("loginFail","用户名或密码错误");
            return "index";
        }
    }

    @RequestMapping("/preUpdatePwd")
    public String preUpdatePwd(Model model) {
        Account loginUser = (Account) httpSession.getAttribute("userInfo");
        if (log.isInfoEnabled()) {
            log.info("当前登录用户信息："+httpSession.getAttribute("userInfo").toString());
        }
        model.addAttribute("loginUser",loginUser);
        return "pwdUpdate";
    }


    @RequestMapping(value = "/updatePwd",method = RequestMethod.POST)
    public String updatePwd(Account account) {
        String userName = account.getUserName();
        String userPwd = account.getUserPwd();
        // 如果用户名和密码都没有填，则不修改
        if (Objects.equals(userName,null) && Objects.equals(userPwd,null)) {
            return "redirect:/account/preUpdatePwd";
        }
        // 将密码用md5加密后存入数据库
        String newPwd = DigestUtil.md5Hex(userPwd);
        account.setUserPwd(newPwd);
        int rows = accountService.updateAccount(account);
        // 如果修改成功，则更新当前用户的信息到前端页面
        if (rows > 0) {
            httpSession.setAttribute("userInfo",account);
        }
        if (log.isInfoEnabled()) {
            log.info("update rows = "+rows);
        }
        return "redirect:/account/preUpdatePwd";
    }
}
