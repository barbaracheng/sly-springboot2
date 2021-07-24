package com.sly.water.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.sly.water.entities.Account;
import com.sly.water.mapper.AccountMapper;
import com.sly.water.service.AccountService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * TODO: 业务逻辑层的实现类
 *
 * @author leyuan
 * @date 2021/7/22 15:18
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 判断用户输入的用户名和密码是否有效
     * @param userName 用户名
     * @param userPwd 密码
     * @return
     */
    @Override
    public boolean login(@Param("userName")String userName, @Param("userPwd")String userPwd) {
        Account account = accountMapper.login(userName, userPwd);
        if (null == account) {
            return false;
        }
        // 使用MD5加密算法将用户密码加密再与从数据库查出来的用户密码进行比较
        userPwd = DigestUtil.md5Hex(userPwd);
        return Objects.equals(userPwd, account.getUserPwd());
    }
}
