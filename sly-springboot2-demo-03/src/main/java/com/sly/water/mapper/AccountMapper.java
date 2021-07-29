package com.sly.water.mapper;

import com.sly.water.entities.Account;
import org.apache.ibatis.annotations.Param;

/**
 * TODO:用户登录的Mapper接口
 *
 * @author leyuan
 * @date 2021/7/22 15:04
 */
public interface AccountMapper {
    /**
     * 根据用户名查询对应用户
     * @param userName
     * @return
     */
    Account login(@Param("userName")String userName,@Param("userPwd")String userPwd);

    int updateAccount(Account account);
}
