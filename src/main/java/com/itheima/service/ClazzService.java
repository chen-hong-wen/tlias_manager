package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;

/**
 * 班级服务接口
 */
public interface ClazzService {

    /**
     * 分页查询班级列表
     * @param clazzQueryParam 查询参数
     * @return 分页结果
     */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    /**
     * 根据id删除班级
     * @param id 班级ID
     */
    void delete(Integer id);

    /**
     * 添加班级
     * @param clazz 班级信息
     */
    void save(Clazz clazz);
}
