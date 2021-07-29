package com.sly.water.service.impl;

import com.sly.water.entities.WaterDetail;
import com.sly.water.mapper.WaterDetailMapper;
import com.sly.water.service.WaterDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/28 11:00
 */
@Service
public class WaterDetailServiceImpl implements WaterDetailService {
    @Autowired
    private WaterDetailMapper waterDetailMapper;

    @Override
    public List<WaterDetail> listWaterDetail() {
        return waterDetailMapper.listWaterDetail();
    }
}
