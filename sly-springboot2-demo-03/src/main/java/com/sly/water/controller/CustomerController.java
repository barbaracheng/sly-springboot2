package com.sly.water.controller;

import com.github.pagehelper.PageInfo;
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
import org.springframework.web.bind.annotation.RequestParam;

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


    /**
     * 分页列出客户信息
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("/custListPage")
    public String listCustomerForPage(@RequestParam(value = "pageNum",defaultValue = "1")
                                                  Integer pageNum, Model model) {
        // 调用业务逻辑层，获取分页数据
        PageInfo<Customer> pageInfo = customerService.listCustomerForPage(pageNum);
        // 获取当前页的客户列表
        List<Customer> custList = pageInfo.getList();
        // 客户列表、分页对象传入前端页面
        model.addAttribute("custList",custList);
        model.addAttribute("pageInfo",pageInfo);
        // 表示普通的分页查询，不是根据条件搜索
        model.addAttribute("pageData","listCustomer");
        return "custList";
    }

    /**
     * 搜索分页
     * 步骤：
     * 1 调  客户管理的搜索功能
     * 2 转
     *   将搜索的客户列表返回给前端(数据共享)
     *   跳转到客户列表页面
     * @param custName
     * @param model
     * @return
     */
    @RequestMapping("/searchCust")
    public String searchCustomer(String custName,
            @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
            Model model) {
        if(log.isInfoEnabled()) {
            log.info("searchCustomer name = "+ custName);
        }
        PageInfo<Customer> pageInfo = customerService.searchCustomer(pageNum,custName);
        // 数据传入到前端
        model.addAttribute("custList",pageInfo.getList());
        model.addAttribute("pageInfo",pageInfo);
        // 按条件搜索分页查询
        model.addAttribute("pageData","searchData");
        model.addAttribute("data",custName);

        // 跳转到客户列表页面
        return "custList";
    }


}
