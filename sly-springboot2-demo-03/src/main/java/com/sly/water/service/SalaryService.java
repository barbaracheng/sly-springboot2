package com.sly.water.service;

import com.github.pagehelper.PageInfo;
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
    // 显示所有人的工资
    List<Salary> listSalary();

    // 根据开始时间和结束时间搜索员工工资信息(当月有送水记录的员工)
    List<Salary> searchSalary(String startTime, String endTime);

    List<Salary> noRecord();

    // 根据开始时间和结束时间搜索员工工资信息(当月有送水记录，但在搜索时间内没有送水记录的员工)
    List<Salary> currentNorecord(String startTime, String endTime);

    // 每页数量
    public final static int PAGE_SiZE = 5;

    /**
     * 工资列表分页
     * @param pageNum 当前页码
     * @return 分页对象
     */
    public PageInfo<Salary> listSalaryForPage(Integer pageNum);

    /**
     * 搜索工资列表的分页
     * @param pageNum
     * @param startTime
     * @param endTime
     * @return
     */
    PageInfo<Salary> searchSalary(Integer pageNum,String startTime, String endTime);


}
