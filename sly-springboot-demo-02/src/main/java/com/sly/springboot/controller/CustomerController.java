package com.sly.springboot.controller;

import com.sly.springboot.entities.Customer;
import com.sly.springboot.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO: 显示客户信息的控制器
 *
 * @author leyuan
 * @date 2021/7/22 13:31
 */
@RestController
@Slf4j
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/custList")
    public String listCusomter() {
        List<Customer> customers = customerService.listCustomer();
        if (log.isInfoEnabled()) {
            log.info(customers.toString());
        }
        return customers.toString();
    }
}
