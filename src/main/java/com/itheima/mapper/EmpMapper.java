package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {

    //------------------------------原始分页查询----------------------------
    /**
     * 查询总记录数
     * @return
     */
//    @Select("SELECT COUNT(*) FROM emp e left join dept d on e.dept_id = d.id")
//    public Long count();

    /**
     * 分页查询所有员工信息
     * @return
     */
//    @Select("SELECT e.*, d.name deptName FROM emp e left join dept d on e.dept_id = d.id order by e.update_time desc limit #{start}, #{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);

    //---------------------------------------------
    //@Select("SELECT e.*, d.name deptName FROM emp e left join dept d on e.dept_id = d.id order by e.update_time desc")
//    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);
    /**
     * 根据条件查询员工信息
     * @param empQueryParam
     * @return
     */
    public List<Emp> list(EmpQueryParam empQueryParam);


    /**
     * 添加员工信息
     * @param emp
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp (username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);


    /**
     * 根据id删除员工信息
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据id查询员工信息以及工作经历信息
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 根据id修改员工信息
     * @param emp
     */
    void updateById(Emp emp);

    /**
     * 查询员工职位数据
     * @return
     */
    List<Map<String, Object>> countEmpJobData();

    /**
     * 查询员工性别及各性别对应的人数。
     *
     * @return 每项包含 gender（性别）和 num（人数）
     */
    List<Map<String, Object>> countEmpGenderData();
}
