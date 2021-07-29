package com.sly.water.mapper;

import com.sly.water.entities.WaterDetail;

import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/28 10:51
 */
public interface WaterDetailMapper {

    List<WaterDetail> listWaterDetail();

    List<WaterDetail> searchDetail(Integer min, Integer max);



}
