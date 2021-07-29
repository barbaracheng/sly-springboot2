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

    /**
     * 批量删除历史信息
     * @param idList 要删除的历史id。例如：1,2,3,5,6,7
     * @return 大于0批量删除成功，否则批量删除失败
     */
    int deleteBatchHistory(@Param("idList") List<Integer> idList);


}
