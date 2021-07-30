package com.sly.water.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sly.water.entities.History;
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

    /**
     * 在删除员工之前先检查在历史表中是否有送水记录
     * @param wid
     * @return
     */
    @Override
    public List<History> searchWorkerInHistoryById(Integer wid) {
        return workerMapper.searchWorkerInHistoryById(wid);
    }

    @Override
    public int updateWorker(Worker worker) {
        return workerMapper.updateWorker(worker);
    }

    @Override
    public List<Worker> searchWorker(String workerName) {
        return workerMapper.searchWorker(workerName);
    }

    /**
     * 调整工资
     * @param wid          工人id
     * @param workerSalary 送水工工资
     * @return 大于0调整工资成功，否则调整工资失败
     */
    @Override
    public int adjustSalary(Integer wid, Integer workerSalary) {
        return workerMapper.adjustSalary(wid,workerSalary);
    }

    /**
     * 查询未送水员工
     *
     * @return
     */
    @Override
    public List<Worker> workerSendNoWater() {
        return workerMapper.workerSendNoWater();
    }

/**
 * ================================
 * 下面是分页的功能
 * ================================
 */
    /**
     * 查询列表分页
     * @param pageNum 当前页码
     * @return 分页对象
     */
    @Override
    public PageInfo<Worker> listWorkerForPage(Integer pageNum) {
        // 分页的核心：从第pageNum页开始，每页显示5条记录
        PageHelper.startPage(pageNum,PAGE_SiZE);
        List<Worker> list = this.listWorker();
        // 分页Bean，封装了分页查询的数据，将查询结果注入到分页对象(Bean)
        PageInfo<Worker> pageInfo =  new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 表单搜索分页
     *
     * @param pageNum    当前页码
     * @param workerName 员工名称
     * @return 分页对象
     */
    @Override
    public PageInfo<Worker> searchWorker(Integer pageNum, String workerName) {
        PageHelper.startPage(pageNum,PAGE_SiZE);
        List<Worker> custList = this.searchWorker(workerName);
        PageInfo<Worker> pageInfo = new PageInfo<>(custList);
        return pageInfo;
    }

    @Override
    public PageInfo<Worker> searchworkerSendNoWater(Integer pageNum) {
        PageHelper.startPage(pageNum,PAGE_SiZE);
        List<Worker> workerList = this.workerSendNoWater();
        PageInfo<Worker> pageInfo = new PageInfo<>(workerList);
        return pageInfo;
    }


}
