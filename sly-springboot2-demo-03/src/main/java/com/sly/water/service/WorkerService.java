package com.sly.water.service;

import com.github.pagehelper.PageInfo;
import com.sly.water.entities.Worker;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/23 15:57
 */
public interface WorkerService {
    /**
     * 查询所有送水工信息
     * @return
     */
    List<Worker> listWorker();

    Worker getWorkerById(Integer wid);

    int saveWorker(Worker worker);

    int deleteWorkerById(Integer wid);

    int updateWorker(Worker worker);

    List<Worker> searchWorker(String workerName);

    /**
     * 调整工资
     * @param wid 工人id
     * @param workerSalary 送水工工资
     * @return 大于0调整工资成功，否则调整工资失败
     */
    int adjustSalary(Integer wid, Integer workerSalary);

    /**
     * 查询未送水员工
     * @return
     */
    List<Worker> workerSendNoWater();


    /**
     * 每页数量
     */
    public final static int PAGE_SiZE = 5;

    /**
     * 查询列表分页
     * @param pageNum 当前页码
     * @return 分页对象
     */
    public PageInfo<Worker> listWorkerForPage(Integer pageNum);

    /**
     * 表单搜索分页
     * @param pageNum 当前页码
     * @param workerName 客户名称
     * @return 分页对象
     */
    PageInfo<Worker> searchWorker(Integer pageNum,String workerName);

    PageInfo<Worker> searchworkerSendNoWater(Integer pageNum);
}
