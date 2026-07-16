package com.itheima.service;

import com.itheima.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计员工职位数据
     * @return
     */
    JobOption getEmpJobData();

    /**
     * 统计各性别的员工数量。
     *
     * @return 每项包含性别名称和员工数量
     */
    List<Map<String, Object>> getEmpGenderData();
}
