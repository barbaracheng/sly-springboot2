package com.sly.water.service;

import com.sly.water.entities.Account;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;

/**
 * TODO:AccountService
 *
 * @author leyuan
 * @date 2021/7/22 15:13
 */
public interface AccountService {
    boolean login(@Param("userName")String userName, @Param("userPwd")String userPwd, HttpSession httpSession);

    int updateAccount(Account account);
}
