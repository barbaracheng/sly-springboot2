package com.sly.water.service;

import com.github.pagehelper.PageInfo;
import com.sly.water.entities.Customer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/22 17:30
 */

public interface CustomerService {

    List<Customer> listCustomer();

    /**
     * 根据用户名称获取用户信息
     * @param custName
     * @return
     */
    List<Customer> searchCustomer(@Param("custName")String custName);

    /**
     * 添加客户信息
     * 约定优于配置优于编码：save or  insert + 实体类名称
     * @param customer 要添加的客户信息
     * @return 受影响行数。大于0：添加成功。否则：添加失败
     */
    public int saveCustomer(Customer customer);

    /**
     * 更新客户信息
     * 约定优于配置优于编码：update+实体类名称
     * @param customer 要修改的客户信息
     * @return 受影响行数。大于0：修改成功。否则：修改失败
     */
    public int updateCustomer(Customer customer);

    /**
     * 根据客户编号删除客户信息
     * @param cid 客户编号
     * @return 受影响行数。大于0：删除成功。否则：删除失败
     */
    public int deleteCustomerById(Integer cid);

    /**
     * 根据用户id获取用户信息
     * @param cid 用户id
     * @return 用户信息
     */
    public Customer getCustomerById(Integer cid);


    /**
     * 每页数量
     */
    public final static int PAGE_SiZE = 5;

    /**
     * 查询列表分页
     * @param pageNum 当前页码
     * @return 分页对象
     */
    public PageInfo<Customer> listCustomerForPage(Integer pageNum );

    /**
     * 表单搜索分页
     * @param pageNum 当前页码
     * @param custName 客户名称
     * @return 分页对象
     */
    PageInfo<Customer> searchCustomer(Integer pageNum,String custName);
}
