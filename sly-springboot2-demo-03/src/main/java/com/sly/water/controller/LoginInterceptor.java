package com.sly.water.controller;

import cn.hutool.core.util.StrUtil;
import com.sly.water.entities.Account;
import com.sly.water.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/30 13:33
 */

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private AccountService accountService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        Cookie[] cookies = request.getCookies();
        // 没有cookie则重定向到登录界面
        if(null == cookies){
            response.sendRedirect("/");
            return false;
        }

        // 获取cookies里面的信息
        String cookie_userName = null;
        for (Cookie cookie : cookies) {
            // 判断cookie中是否存储所需用户名
            // 存在，则保存值
            if(Objects.equals("cookie_userName",cookie.getName())){
                cookie_userName = cookie.getValue();
            }
        }
        // 判断是否存储了所需的用户名，没有则重定向到登录界面
        if(StrUtil.isEmpty(cookie_userName)){
            response.sendRedirect("/");
            return false;
        }
        if(log.isInfoEnabled()){
            log.info("cookies::"+cookies);
        }
        HttpSession session = request.getSession();
        Object user = session.getAttribute("system_user_session");
        if(null == user){
            Account currentUser = accountService.getAccount(cookie_userName);
            session.setAttribute("system_user_session",currentUser);
        }
        return true;
    }
}
