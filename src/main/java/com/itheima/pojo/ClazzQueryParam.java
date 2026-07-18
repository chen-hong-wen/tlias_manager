package com.itheima.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 班级查询参数
 */
@Data
public class ClazzQueryParam {
    private Integer page = 1; //页码，默认1
    private Integer pageSize = 10; //每页记录数，默认10
    private String name; //班级名称
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin; //范围匹配的开始时间(结课时间)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; //范围匹配的结束时间(结课时间)
}
