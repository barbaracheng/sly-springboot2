package com.sly.water.mapper;

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

    int deleteWorkerById(Integer wid);

    int updateWorker(Worker worker);

    List<Worker> searchWorker(@Param("workerName") String workerName);

}
