package com.sly.water.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: 用户信息实体类
 *
 * @author leyuan
 * @date 2021/7/22 15:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Integer id;

    private String userName;

    private String userPwd;
}
