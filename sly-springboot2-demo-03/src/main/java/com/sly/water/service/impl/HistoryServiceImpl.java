package com.sly.water.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sly.water.entities.Customer;
import com.sly.water.entities.History;
import com.sly.water.entities.Worker;
import com.sly.water.mapper.HistoryMapper;
import com.sly.water.service.CustomerService;
import com.sly.water.service.HistoryService;
import com.sly.water.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private CustomerService customerService;

    @Autowired
    private WorkerService workerService;
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

    @Override
    public int saveHistory(History history, Integer custId, Integer workerId) {
        history.setCustomer(customerService.getCustomerById(custId));
        history.setWorker(workerService.getWorkerById(workerId));
        return historyMapper.saveHistory(history);
    }

    @Override
    public List<History> searchHistory(String startTime, String endTime) {
        return historyMapper.searchHistory(startTime,endTime);
    }

    @Override
    public History getHistoryById(Integer hid) {
        return historyMapper.getHistoryById(hid);
    }

    @Override
    public int updateHistory(History history, Integer custId, Integer workerId) {
        Customer customer = new Customer();
        customer.setCid(custId);

        Worker worker = new Worker();
        worker.setWid(workerId);

        history.setWorker(worker);
        history.setCustomer(customer);

        return historyMapper.updateHistory(history);
    }

    /**
     * 批量删除
     *
     * @param ids 送水历史编号
     * @return 大于0删除成功，否则删除失败
     * rollbackFor：触发回滚的异常。此时发生Exception异常或者它的子类就会触发事务回滚。
     */
    @Override
    @Transactional(rollbackFor = {Exception.class,Error.class})
    public int deleteBatchHistory(String ids) {
        ids = ids.replaceFirst(",", "");
        String[] split = StrUtil.split(ids, ",");
        ArrayList<Integer> idList = new ArrayList<>();
        for (String id : split) {
            idList.add(Integer.parseInt(id));
        }
        int rows = historyMapper.deleteBatchHistory(idList);
//        System.out.println(1/0);
        return rows;
    }

    /**
     * 查询历史列表分页
     *
     * @param pageNum 当前页码
     * @return 分页对象
     */
    @Override
    public PageInfo<History> listHistoryForPage(Integer pageNum) {
        PageHelper.startPage(pageNum,PAGE_SiZE);
        List<History> list = this.listHistory();
        PageInfo<History> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 搜索历史列表的分页
     *
     * @param pageNum 当前页码
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    @Override
    public PageInfo<History> searchHistory(Integer pageNum, String startTime, String endTime) {
        PageHelper.startPage(pageNum,PAGE_SiZE);
        List<History> historyList = this.searchHistory(startTime, endTime);
        PageInfo<History> pageInfo = new PageInfo<>(historyList);
        return pageInfo;
    }
}
