package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;



//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        //1.
//        Long total = empMapper.count();
//
//        //2. 调用 Mapper 查询当前页数据。
//        List<Emp> rows = empMapper.list((page - 1) * pageSize, pageSize);
//
//        //3. 返回分页结果。
//        return new PageResult<Emp>(total, rows);
//    }


   /* @Override
    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
        //1. 设置分页参数。
        PageHelper.startPage(page, pageSize);

        //2. 执行查询。
        List<Emp> empList = empMapper.list(name, gender, begin, end);

        //3. 解析分页查询结果。
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }*/

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1. 调用 PageHelper 插件完成分页参数的设置。
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        //2. 执行查询。
        List<Emp> empList = empMapper.list(empQueryParam);

        //3. 解析分页查询结果。
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    @Transactional(rollbackFor = {Exception.class}) // 发生异常时回滚整个新增操作。
    @Override
    public void save(Emp emp) {
        try {
            //1. 保存员工基本信息，并由数据库回填员工 ID。
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //2. 为每段工作经历关联员工 ID 后批量保存。
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)){
                // 遍历工作经历并设置所属员工 ID。
                for (EmpExpr expr : exprList) {
                    expr.setEmpId(emp.getId());
                }
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //3. 无论新增是否成功，都记录本次操作日志。
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "添加员工: " + emp.getUsername());
            empLogService.insertLog(empLog);
        }

    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        // 先删除员工基本信息。
        empMapper.deleteByIds(ids);

        // 再删除该员工关联的工作经历。
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //1. 根据员工 ID 更新基本信息。
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2. 采用“先删除、再新增”的方式更新工作经历。
        //2.1 先删除原有工作经历。
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //2.2 再保存新的工作经历。
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)){
            for (EmpExpr expr : exprList) {
                expr.setEmpId(emp.getId());
            }
            empExprMapper.insertBatch(exprList);
        }
    }
}
