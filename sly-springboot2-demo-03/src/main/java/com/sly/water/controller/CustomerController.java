package com.sly.water.controller;

import com.sly.water.entities.Customer;
import com.sly.water.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/22 17:33
 */
@Controller
@RequestMapping("/cust")
@Slf4j
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/listCust")
    public String listCust(Model model) {
        List<Customer> custList = customerService.listCustomer();
        // 数据传入到前端
        model.addAttribute("custList",custList);
        // 跳转到客户列表页面
        return "custList";
    }

    @RequestMapping("/searchCust")
    public String searchCust(@Param("custName") String custName, Model model) {
        List<Customer> customers = customerService.searchCustomer(custName);
        if (log.isInfoEnabled()) {
            log.info(customers.toString());
        }
        model.addAttribute("custList",customers);
        model.addAttribute("data",custName);
        return "custList";
    }

    @RequestMapping("/preSaveCust")
    public String preSaveCust() {
        return "custSave";
    }

    @RequestMapping(value = "/saveCust",method = RequestMethod.POST)
    public String saveCust(Customer customer) {
        if (log.isInfoEnabled()) {
            log.info("customer:"+customer);
        }
        int row = customerService.saveCustomer(customer);
        if (log.isInfoEnabled()) {
            log.info("受影响的行数："+row);
        }
        return "redirect:/cust/listCust";
    }

    @RequestMapping("/preUpdateCust/{cid}")
    public String preUpdateCust(@PathVariable("cid") Integer cid, Model model) {
        Customer customer = customerService.getCustomerById(cid);
        if (log.isInfoEnabled()) {
            log.info("查询的用户信息："+customer);
        }
        model.addAttribute("customer",customer);

        return "custUpdate";
    }

    @RequestMapping(value = "/updateCust",method = RequestMethod.POST)
    public String updateCust(Customer customer) {
        log.info(customer.toString());
        int rows = customerService.updateCustomer(customer);
        if (log.isInfoEnabled()) {
            log.info("更新的行数为："+rows);
        }

        return "redirect:/cust/listCust";
    }

    @RequestMapping("/deleteCust/{cid}")
    public String deleteCust(@PathVariable("cid")Integer cid) {
        Integer rows = customerService.deleteCustomerById(cid);
        if (log.isInfoEnabled()) {
            log.info("删除的行数为："+rows);
        }
        return "redirect:/cust/listCust";
    }


}
