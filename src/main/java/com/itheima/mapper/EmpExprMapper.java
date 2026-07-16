package com.itheima.mapper;

import com.itheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 员工经历
 */
@Mapper
public interface EmpExprMapper {
    /**
     * 批量保存
     * @param exprList
     */
    void insertBatch(List<EmpExpr> exprList);

    /**
     * 根据员工id删除员工经历
     * @param ids
     */
    void deleteByEmpIds(List<Integer> empIds);
}
