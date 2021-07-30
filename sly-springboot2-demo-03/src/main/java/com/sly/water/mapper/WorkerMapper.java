package com.sly.water.mapper;

import com.sly.water.entities.History;
import com.sly.water.entities.Worker;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/23 15:25
 */
public interface WorkerMapper {

    /**
     * 查询所有送水工信息
     * @return
     */
    List<Worker> listWorker();

    Worker getWorkerById(Integer wid);

    int saveWorker(Worker worker);

    /**
     * 根据id删除员工
     * @param wid
     * @return
     */
    int deleteWorkerById(Integer wid);

    /**
     * 在删除员工之前先检查在历史表中是否有送水记录
     * @param wid
     * @return
     */
    List<History> searchWorkerInHistoryById(Integer wid);

    int updateWorker(Worker worker);

    /**
     * 根据员工名称搜索员工信息
     * @param workerName
     * @return
     */
    List<Worker> searchWorker(@Param("workerName") String workerName);

    /**
     * 调整工资
     * @param wid 工人id
     * @param workerSalary 送水工工资
     * @return 大于0调整工资成功，否则调整工资失败
     */
    int adjustSalary(@Param("wid") Integer wid, @Param("workerSalary") Integer workerSalary);

    /**
     * 查询未送水员工
     * @return
     */
    List<Worker> workerSendNoWater();

}
