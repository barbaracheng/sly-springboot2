package com.sly.water.service.impl;

import cn.hutool.core.date.DateTime;
import com.sly.water.entities.Customer;
import com.sly.water.entities.History;
import com.sly.water.entities.Worker;
import com.sly.water.mapper.HistoryMapper;
import com.sly.water.service.CustomerService;
import com.sly.water.service.HistoryService;
import com.sly.water.service.WorkerService;
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
}
