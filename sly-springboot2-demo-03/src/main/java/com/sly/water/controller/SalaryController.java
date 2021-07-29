package com.sly.water.controller;

import com.sly.water.entities.Salary;
import com.sly.water.service.SalaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/searchSalary")
    public String searchSalary(String startTime, String endTime, Model model) {
        // 调用业务逻辑层的搜索方法
        // 1.有送水记录的员工
        List<Salary> salaryList = salaryService.searchSalary(startTime, endTime);
        // 2.没有送水记录的员工
//        List<Salary> salaries1 = salaryService.noRecord();
        // 3.在搜索时间内没有送水记录的员工
        List<Salary> salaries2 = salaryService.currentNorecord(startTime, endTime);
        for (Salary salary : salaries2) {
            salary.setSendWaterCount(0);
            salary.setFinalSalary(salary.getWorkerSalary());
        }
        // 4.整合到一起
//        salaryList.addAll(salaries1);
        salaryList.addAll(salaries2);

        if (log.isInfoEnabled()) {
            log.info("startTime = "+startTime);;
            log.info("endTime = "+endTime);
            log.info("searchSalary = "+salaryList);
//            log.info("salaries1 = "+salaries1);
            log.info("salaries2 = "+salaries2);
        }
        // 将数据传递给前端页面
        model.addAttribute("salaryList",salaryList);
        model.addAttribute("startTime",startTime);
        model.addAttribute("endTime",endTime);
        return "salaryList";
    }
}
