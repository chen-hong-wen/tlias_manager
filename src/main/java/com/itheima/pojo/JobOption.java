package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobOption {

    /** 图表横轴的职位标签。 */
    private List jobList;

    /** 与职位标签一一对应的员工数量。 */
    private List dataList;
}
