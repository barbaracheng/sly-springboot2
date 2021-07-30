package com.sly.water.service;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.PageInfo;
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

    /**
     * 批量删除
     * @param ids 送水历史编号
     * @return 大于0删除成功，否则删除失败
     */
    int deleteBatchHistory(String ids);

    /**
     * 每页数量
     */
    public final static int PAGE_SiZE = 5;

    /**
     * 查询历史列表分页
     * @param pageNum 当前页码
     * @return 分页对象
     */
    public PageInfo<History> listHistoryForPage(Integer pageNum);

    /**
     * 搜索历史列表的分页
     * @param pageNum
     * @param startTime
     * @param endTime
     * @return
     */
    PageInfo<History> searchHistory(Integer pageNum,String startTime, String endTime);
}
