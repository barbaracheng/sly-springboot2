package com.sly.water.mapper;

import cn.hutool.core.date.DateTime;
import com.sly.water.entities.History;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/24 9:48
 */
public interface HistoryMapper {
    /**
     * 显示所有送水历史信息
     * @return 送水历史列表
     */
    List<History> listHistory();

    int deleteHistoryById(Integer hid);

    int saveHistory(History history);

    List<History> searchHistory(@Param("startTime") String startTime, @Param("endTime") String endTime);

    History getHistoryById(Integer hid);

    int updateHistory(History history);


}
