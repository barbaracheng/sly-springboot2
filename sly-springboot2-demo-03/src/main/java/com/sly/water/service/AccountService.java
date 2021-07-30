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
    boolean login(String userName,String userPwd, HttpSession httpSession);

    Account getAccount(String userName);

    int updateAccount(Account account);


}
