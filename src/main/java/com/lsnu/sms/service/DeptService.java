package com.lsnu.sms.service;

import com.lsnu.sms.model.Dept;

import java.util.List;
import java.util.Map;

public interface DeptService {
    List<Dept> getList(Map<String, Object> queryMap);

    List<Dept> getDept();

    int getTotal(Map<String, Object> queryMap);

    Dept findById(String deptId);

    int add(Dept dept);

    int edit(Dept dept);

    int delete(String deptId);
}
