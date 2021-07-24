package com.sly.water.service.impl;
import com.sly.water.entities.Customer;
import com.sly.water.mapper.CustomerMapper;
import com.sly.water.service.CustomerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/22 17:22
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> listCustomer() {
        return customerMapper.listCustomer();
    }

    /**
     * 根据用户名称获取用户信息
     *
     * @param custName
     * @return
     */
    @Override
    public List<Customer> searchCustomer(@Param("custName") String custName) {
        return customerMapper.searchCustomer(custName);
    }

    /**
     * 添加客户信息
     * 约定优于配置优于编码：save or  insert + 实体类名称
     *
     * @param customer 要添加的客户信息
     * @return 受影响行数。大于0：添加成功。否则：添加失败
     */
    @Override
    public int saveCustomer(Customer customer) {
        return customerMapper.saveCustomer(customer);
    }

    /**
     * 更新客户信息
     * 约定优于配置优于编码：update+实体类名称
     *
     * @param customer 要修改的客户信息
     * @return 受影响行数。大于0：修改成功。否则：修改失败
     */
    @Override
    public int updateCustomer(Customer customer) {
        return customerMapper.updateCustomer(customer);
    }

    /**
     * 根据客户编号删除客户信息
     *
     * @param cid 客户编号
     * @return 受影响行数。大于0：删除成功。否则：删除失败
     */
    @Override
    public int deleteCustomerById(Integer cid) {
        return customerMapper.deleteCustomerById(cid);
    }

    /**
     * 根据用户id获取用户信息
     *
     * @param cid 用户id
     * @return 用户信息
     */
    @Override
    public Customer getCustomerById(Integer cid) {
        return customerMapper.getCustomerById(cid);
    }
}

