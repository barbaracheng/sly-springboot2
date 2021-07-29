package com.sly.water.service;

import com.sly.water.entities.Salary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/28 9:56
 */
public interface SalaryService {
    List<Salary> listSalary();

    List<Salary> searchSalary(String startTime, String endTime);

    List<Salary> noRecord();

    List<Salary> currentNorecord(String startTime, String endTime);
}
