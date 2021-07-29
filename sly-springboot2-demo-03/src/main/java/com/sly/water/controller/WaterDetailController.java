package com.sly.water.controller;

import com.sly.water.entities.WaterDetail;
import com.sly.water.service.WaterDetailService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/28 10:56
 */
@Controller
@Slf4j
@RequestMapping("/detail")
public class WaterDetailController {
    @Autowired
    private WaterDetailService waterDetailService;

    @RequestMapping("/detailList")
    public String listDetail(Model model) {

        List<WaterDetail> detailList = waterDetailService.listWaterDetail();
        if (log.isInfoEnabled()) {
            log.info("detailList = "+detailList);
        }
        model.addAttribute("detailList",detailList);

        return "detailList";
    }
}
