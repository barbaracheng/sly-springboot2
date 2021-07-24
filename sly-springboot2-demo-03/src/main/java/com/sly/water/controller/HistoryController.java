package com.sly.water.controller;

import com.sly.water.entities.History;
import com.sly.water.service.HistoryService;
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
 * @date 2021/7/24 9:52
 */
@Controller
@Slf4j
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

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


    @RequestMapping("/preSaveHistory")
    public String preSaveHistory() {
        return "historySave";
    }
    @RequestMapping(value = "/saveHistory",method = RequestMethod.POST)
    public String saveHistory(History history) {
        if (log.isInfoEnabled()) {
            log.info("history:"+history);
        }
        return "redirect:/history/historyList";
    }




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
