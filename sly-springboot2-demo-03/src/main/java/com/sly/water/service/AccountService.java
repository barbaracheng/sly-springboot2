package com.sly.water.service;

import org.apache.ibatis.annotations.Param;

/**
 * TODO:AccountService
 *
 * @author leyuan
 * @date 2021/7/22 15:13
 */
public interface AccountService {
    boolean login(@Param("userName")String userName,@Param("userPwd")String userPwd);
}
