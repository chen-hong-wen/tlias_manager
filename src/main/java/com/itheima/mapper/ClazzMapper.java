package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 班级数据访问层
 */
@Mapper
public interface ClazzMapper {

    /**
     * 根据条件分页查询班级列表
     * @param clazzQueryParam 查询参数
     * @return 班级列表
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    /**
     * 根据id删除班级
     * @param id 班级ID
     */
    void deleteById(Integer id);

    /**
     * 添加班级
     * @param clazz 班级信息
     */
    void insert(Clazz clazz);
}
