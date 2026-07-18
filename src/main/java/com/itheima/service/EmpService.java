package com.itheima.service;

import com.itheima.pojo.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface EmpService {

    /**
     * 分页统计
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    //PageResult<Emp> page(Integer page, Integer pageSize,String name, Integer gender,LocalDate begin,LocalDate end);


    /**
     * 增加员工
     */
    void save(Emp emp);


    /**
     * 根据id删除员工
     */
    void delete(List<Integer> ids);


    /**
     * 根据id获取员工信息
     */
    Emp getInfo(Integer id);

    /**
     * 修改员工
     */
    void update(Emp emp);

    /**
     * 查询全部员工
     * @return 员工列表
     */
    List<Emp> listAll();

    /**
     * 登录
     */
    LoginInfo login(Emp emp);
}
