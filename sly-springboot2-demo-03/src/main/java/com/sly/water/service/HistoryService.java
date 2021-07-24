package com.sly.water.service;

import cn.hutool.core.date.DateTime;
import com.sly.water.entities.History;

import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/24 9:49
 */
public interface HistoryService {
    /**
     * 显示所有送水历史信息
     * @return 送水历史列表
     */
    List<History> listHistory();

    int deleteHistoryById(Integer hid);

    int saveHistory(History history, Integer custId, Integer workerId);

    List<History> searchHistory(String startTime, String endTime);

    History getHistoryById(Integer hid);

    int updateHistory(History history, Integer custId, Integer workerId);
}
