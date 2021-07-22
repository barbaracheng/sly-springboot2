package com.sly.springboot.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO:Customer实体类
 *
 * @author leyuan
 * @date 2021/7/22 12:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Integer cid;

    private String custName;

    private String custMobile;

    private String custAddress;

    private Integer custTicket;
}
