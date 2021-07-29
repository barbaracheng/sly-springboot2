package com.sly.water.mapper;

import com.sly.water.entities.Salary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO: 计算工资的Mapper层
 *
 * @author leyuan
 * @date 2021/7/28 9:33
 */
public interface SalaryMapper {
    List<Salary> listSalary();

    List<Salary> searchSalary(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<Salary> noRecord();

    List<Salary> currentNorecord(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
