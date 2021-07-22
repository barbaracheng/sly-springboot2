package com.sly.springboot.mapper;

import com.sly.springboot.entities.Customer;

import java.util.List;

/**
 * TODO:Mapper用于在数据库表和Java实体类之间做映射，封装了数据库所有的增删改查操作，用于跟数据库交互
 *
 * @author leyuan
 * @date 2021/7/22 12:55
 */
public interface CustomerMapper {
    /**
     获取所有客户信息
     */
    List<Customer> listCustomer();
}
