package com.sly.water.service.impl;

import com.sly.water.entities.Worker;
import com.sly.water.mapper.WorkerMapper;
import com.sly.water.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/23 15:58
 */
@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerMapper workerMapper;

    /**
     * 查询所有送水工信息
     *
     * @return
     */
    @Override
    public List<Worker> listWorker() {
        return workerMapper.listWorker();
    }

    @Override
    public Worker getWorkerById(Integer wid) {
        return workerMapper.getWorkerById(wid);
    }

    @Override
    public int saveWorker(Worker worker) {
        return workerMapper.saveWorker(worker);
    }

    @Override
    public int deleteWorkerById(Integer wid) {
        return workerMapper.deleteWorkerById(wid);
    }

    @Override
    public int updateWorker(Worker worker) {
        return workerMapper.updateWorker(worker);
    }

    @Override
    public List<Worker> searchWorker(String workerName) {
        return workerMapper.searchWorker(workerName);
    }
}
