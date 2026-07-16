package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    //private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    @GetMapping
    //@RequestMapping(value = "/depts", method = RequestMethod.GET)
    public Result list() {
        //System.out.println("查询全部部门数据");
        log.info("查询全部部门数据");
        List<Dept> deptlist = deptService.findAll();
        return Result.success(deptlist);
    }

    @DeleteMapping
    public Result delete(Integer id) {
        //System.out.println("根据id删除部门数据：" + id);
        log.info("根据id删除部门数据：" + id);
        deptService.deleteById(id);
        return Result.success();
    }

    // 添加部门数据
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        //System.out.println("添加部门数据：" + dept);
        log.info("添加部门数据：" + dept);
        deptService.add(dept);
        return Result.success();
    }

    //根据id查询部门数据
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        //System.out.println("根据id查询部门数据：" + id);
        log.info("根据id查询部门数据：{}",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    //修改部门数据
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        //System.out.println("修改部门数据：" + dept);
        log.info("修改部门数据：{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
