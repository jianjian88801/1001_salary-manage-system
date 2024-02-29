package com.lsnu.sms.dao;

import com.lsnu.sms.model.Dept;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@ResponseBody
public interface DeptDao {
    List<Dept> getList(Map<String, Object> queryMap);

    int getTotal(Map<String, Object> queryMap);

    Dept findById(String deptId);

    int add(Dept dept);

    int edit(Dept dept);

    int delete(String deptId);

    List<Dept> getDept();
}
