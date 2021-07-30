package com.sly.water.controller;

import com.github.pagehelper.PageInfo;
import com.sly.water.entities.Salary;
import com.sly.water.service.SalaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/28 9:58
 */
@Controller
@Slf4j
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @RequestMapping("/salaryList")
    public String listSalary(Model model) {
        List<Salary> salaryList = salaryService.listSalary();
        if (log.isInfoEnabled()) {
            log.info("salaryList = " + salaryList.toString());
        }
        model.addAttribute("salaryList",salaryList);
        return "salaryList";
    }

    @RequestMapping("/salaryListPage")
    public String listSalaryForPage(@RequestParam(value = "pageNum",defaultValue = "1")
                                                Integer pageNum, Model model) {
        PageInfo<Salary> pageInfo = salaryService.listSalaryForPage(pageNum);
        List<Salary> salaryList = pageInfo.getList();
        if (log.isInfoEnabled()) {
            log.info("pageInfo = "+pageInfo);
            log.info("salaryList = " + salaryList.toString());
        }
        model.addAttribute("salaryList",salaryList);
        model.addAttribute("pageInfo",pageInfo);
        // 加一个属性pageData，表示是普通的分页查询，不是根据条件搜索
        model.addAttribute("pageData","listSalary");
        return "salaryList";
    }


    @RequestMapping("/searchSalary")
    public String searchSalary(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                               String startTime, String endTime, Model model) {
        // 调用业务逻辑层的搜索方法
        PageInfo<Salary> pageInfo = salaryService.searchSalary(pageNum, startTime, endTime);

        if (log.isInfoEnabled()) {
            log.info("startTime = "+startTime);;
            log.info("endTime = "+endTime);
            log.info("pageInfo = "+pageInfo);
            log.info("salaryList = "+pageInfo.getList());
        }

        // 将数据传递给前端页面
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("salaryList",pageInfo.getList());
        model.addAttribute("startTime",startTime);
        model.addAttribute("endTime",endTime);
        // 按条件搜索分页
        model.addAttribute("pageData","searchData");
        return "salaryList";
    }

}
