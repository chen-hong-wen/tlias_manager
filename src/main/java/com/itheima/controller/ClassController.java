package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.ClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询: {}",empQueryParam);
        PageResult<Dept> pageResult = classService.page(empQueryParam);
        return Result.success(pageResult);
    }
}
