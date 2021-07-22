package com.sly.springboot.service.impl;

import com.sly.springboot.entities.Customer;
import com.sly.springboot.mapper.CustomerMapper;
import com.sly.springboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/22 13:28
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> listCustomer() {
        return customerMapper.listCustomer();
    }
}
