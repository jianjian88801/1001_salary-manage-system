package com.lsnu.sms.service;

import com.lsnu.sms.model.Staff;
import com.lsnu.sms.model.User;

import java.util.List;
import java.util.Map;

public interface StaffService {
    int edit(Staff staff);

    Staff getInfo(String workId);

    List<Staff> getList(Map<String, Object> queryMap);

    int getTotal(Map<String, Object> queryMap);

    Staff findById(String workId);

    int add(Staff staff);

    int delete(String workId);

    List<Staff> findByDeptId(String deptId);
}
