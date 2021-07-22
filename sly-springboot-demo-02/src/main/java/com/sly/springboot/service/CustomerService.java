package com.sly.springboot.service;

import com.sly.springboot.entities.Customer;

import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/22 13:26
 */

public interface CustomerService {

    /**
     获取所有客户信息
     */
    List<Customer> listCustomer();
}
