package com.sly.water.service.impl;

import com.sly.water.entities.History;
import com.sly.water.mapper.HistoryMapper;
import com.sly.water.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/24 9:50
 */
@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryMapper historyMapper;
    /**
     * 显示所有送水历史信息
     * @return 送水历史列表
     */
    @Override
    public List<History> listHistory() {
        return historyMapper.listHistory();
    }

    @Override
    public int deleteHistoryById(Integer hid) {
        return historyMapper.deleteHistoryById(hid);
    }
}
