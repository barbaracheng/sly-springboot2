package com.sly.water.controller;

import cn.hutool.core.date.DateTime;
import com.sly.water.entities.Customer;
import com.sly.water.entities.History;
import com.sly.water.entities.Worker;
import com.sly.water.service.CustomerService;
import com.sly.water.service.HistoryService;
import com.sly.water.service.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/24 9:52
 */
@Controller
@Slf4j
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private WorkerService workerService;

    /**
     * 显示所有送水历史
     * @param model
     * @return
     */
    @RequestMapping("/historyList")
    public String listHistory(Model model) {
        List<History> historyList = historyService.listHistory();

        if (log.isInfoEnabled()) {
            log.info("历史列表："+historyList.toString());
        }
        // 历史列表共享到前端页面
        model.addAttribute("historyList",historyList);
        // 跳转到送水历史列表
        return "historyList";
    }

    /**
     * 根据开始时间和结束时间搜索送水记录
     * @param startTime
     * @param endTime
     * @param model
     * @return
     */
    @RequestMapping("/searchHistory")
    public String searchHistory(String startTime ,String endTime,Model model) {
        if (log.isInfoEnabled()) {
            log.info("startTime:"+startTime);
            log.info("endTime:"+endTime);
        }
        List<History> historyList = historyService.searchHistory(startTime, endTime);
        model.addAttribute("historyList",historyList);
        model.addAttribute("startTime",startTime);
        model.addAttribute("endTime",endTime);
        return "historyList";
    }

    /**
     * 点击“修改”按钮跳转到这里
     * @param hid
     * @param model
     * @return
     */
    @RequestMapping("/preUpdateHistory/{hid}")
    public String preUpdateHistory(@PathVariable("hid") Integer hid,Model model) {
        if (log.isInfoEnabled()) {
            log.info("preUpdateHistory hid = "+hid);
        }
        History history = historyService.getHistoryById(hid);
        List<Customer> customerList = customerService.listCustomer();
        List<Worker> workerList = workerService.listWorker();
        model.addAttribute("history",history);
        model.addAttribute("customerList",customerList);
        model.addAttribute("workerList",workerList);

        return "historyUpdate";
    }

    /**
     * 点提交按钮，提交修改
     * @param history
     * @param custId
     * @param workerId
     * @return
     */
    @RequestMapping(value = "/updateHistory",method = RequestMethod.POST)
    public String updateHistory(History history,Integer custId,Integer workerId) {
        if (log.isInfoEnabled()) {
            log.info("updateHistory = "+history);
            log.info("workerId = "+workerId);
            log.info("custId = "+custId);
        }

        int rows = historyService.updateHistory(history,custId,workerId);
        if (log.isInfoEnabled()) {
            log.info("update history rows = "+rows);
        }
        return "redirect:/history/historyList";
    }

    /**
     * 点击添加跳转到这里来执行方法
     * @return
     */
    @RequestMapping("/preSaveHistory")
    public String preSaveHistory(Model model) {
        // 查询所有员工和客户信息
        List<Customer> customerList = customerService.listCustomer();
        List<Worker> workerList = workerService.listWorker();
        // 将数据共享到前端
        model.addAttribute("customerList",customerList);
        model.addAttribute("workerList",workerList);
        return "historySave";
    }


    @RequestMapping(value = "/saveHistory",method = RequestMethod.POST)
    public String saveHistory(History history, Integer custId, Integer workerId) {
        if (log.isInfoEnabled()) {
            log.info("custId:"+custId);
            log.info("workerId:"+workerId);
            log.info("history:"+history.toString());
        }
        int rows = historyService.saveHistory(history,custId,workerId);
        if (log.isInfoEnabled()) {
            log.info("rows = "+rows);
        }
        return "redirect:/history/historyList";
    }


    /**
     * 删除送水历史
     * @param hid
     * @return
     */
    @RequestMapping("/delHistory/{hid}")
    public String deleteHistory(@PathVariable("hid") Integer hid) {
        if(log.isInfoEnabled()) {
            log.info("delete History hid = "+hid);
        }
        Integer rows = historyService.deleteHistoryById(hid);
        if(log.isInfoEnabled()) {
            log.info("delete History rows = "+rows);
        }
        return "redirect:/history/historyList";
    }
}
