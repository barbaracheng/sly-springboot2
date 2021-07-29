package com.sly.water.service;

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
}
