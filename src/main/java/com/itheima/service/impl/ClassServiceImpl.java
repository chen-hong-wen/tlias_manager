package com.itheima.service.impl;

import com.itheima.mapper.ClassMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;


    // 查询全部班级数据
    @Override
    public List<Dept> findAll() {
        return classMapper.findAll();
    }
}
