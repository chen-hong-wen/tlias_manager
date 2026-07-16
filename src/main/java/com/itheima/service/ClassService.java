package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface ClassService {
    //查询全部班级数据
    List<Dept> findAll();
}
