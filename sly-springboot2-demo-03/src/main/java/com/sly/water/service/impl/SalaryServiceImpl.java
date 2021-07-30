package com.sly.water.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    /**
     * ================================
     * 以下是分页的代码
     * ================================
     */

    /**
     * 工资列表分页
     * @param pageNum 当前页码
     * @return 分页对象
     */
    @Override
    public PageInfo<Salary> listSalaryForPage(Integer pageNum) {
        PageHelper.startPage(pageNum,PAGE_SiZE);
        List<Salary> list = this.listSalary();
        PageInfo<Salary> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 搜索工资列表的分页
     * @param pageNum
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public PageInfo<Salary> searchSalary(Integer pageNum, String startTime, String endTime) {
        PageHelper.startPage(pageNum,PAGE_SiZE);
        // 获取搜索到的用户信息
        List<Salary> list = this.searchSalary(startTime, endTime);
        List<Salary> list1 = this.currentNorecord(startTime, endTime);
        for (Salary salary : list1) {
            salary.setSendWaterCount(0);
            salary.setFinalSalary(salary.getWorkerSalary());
        }
        // 整合到一起
        list.addAll(list1);
        // 将工资信息注入到PageInfo对象
        PageInfo<Salary> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


}
