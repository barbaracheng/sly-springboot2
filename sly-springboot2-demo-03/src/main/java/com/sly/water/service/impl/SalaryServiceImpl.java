package com.sly.water.service.impl;

import com.sly.water.entities.Salary;
import com.sly.water.mapper.SalaryMapper;
import com.sly.water.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/28 9:56
 */
@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    private SalaryMapper salaryMapper;

    @Override
    public List<Salary> listSalary() {
        return salaryMapper.listSalary();
    }

    @Override
    public List<Salary> searchSalary(String startTime, String endTime) {
        return salaryMapper.searchSalary(startTime,endTime);
    }

    @Override
    public List<Salary> noRecord() {
        return salaryMapper.noRecord();
    }

    @Override
    public List<Salary> currentNorecord(String startTime, String endTime) {
        return salaryMapper.currentNorecord(startTime, endTime);
    }


}
