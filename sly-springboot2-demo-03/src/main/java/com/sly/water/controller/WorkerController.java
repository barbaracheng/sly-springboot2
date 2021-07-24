package com.sly.water.controller;

import com.sly.water.entities.Worker;
import com.sly.water.service.WorkerService;
import lombok.extern.slf4j.Slf4j;
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
 * @date 2021/7/23 16:06
 */
@Controller
@Slf4j
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @RequestMapping("/workerList")
    public String listWorker(Model model) {
        List<Worker> list = workerService.listWorker();
        // 数据传入到前端
        model.addAttribute("workerList",list);
        // 跳转到客户列表页面
        return "workerList";
    }

    @RequestMapping("/searchWorker")
    public String searchWorker(String workerName,Model model) {
        if(log.isInfoEnabled()) {
            log.info("searchWorker name = "+ workerName);
        }
        List<Worker> workerList = workerService.searchWorker(workerName);
        // 数据传入到前端
        model.addAttribute("workerList",workerList);
        // 跳转到客户列表页面
        return "workerList";
    }

    @RequestMapping("/preSaveWorker")
    public String preSaveWorker() {
        return "workerSave";
    }

    @RequestMapping(value="/saveWorker",method = RequestMethod.POST)
    public String saveWorker(Worker worker) {
        if(log.isInfoEnabled()) {
            log.info("save worker "+worker);
        }
        int rows = workerService.saveWorker(worker);
        if(log.isInfoEnabled()) {
            log.info("save worker rows = "+rows);
        }
        return "redirect:/worker/workerList";
    }

    @RequestMapping("/delWorker/{wid}")
    public String deleteWorker(@PathVariable("wid") Integer wid) {
        if(log.isInfoEnabled()) {
            log.info("delete worker wid = "+wid);
        }
        Integer rows = workerService.deleteWorkerById(wid);
        if(log.isInfoEnabled()) {
            log.info("delete worker rows = "+rows);
        }
        return "redirect:/worker/workerList";
    }


    @RequestMapping("/preUpdateWorker/{wid}")
    public String  preUpdateWorker(@PathVariable("wid") Integer wid,Model model) {
        if (log.isInfoEnabled()) {
            log.info("update worker wid ="+wid);
        }
        Worker worker = workerService.getWorkerById(wid);
        model.addAttribute("worker",worker);
        return "workerUpdate";
    }

    @RequestMapping(value = "/updateWorker",method = RequestMethod.POST)
    public String updateWorker(Worker worker) {
        if(log.isInfoEnabled()) {
            log.info("update worker = "+worker.toString());
        }
        int rows = workerService.updateWorker(worker);
        if(log.isInfoEnabled()) {
            log.info("rows = "+rows);
        }
        return "redirect:/worker/workerList";
    }
}
