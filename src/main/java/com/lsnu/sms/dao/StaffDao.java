package com.lsnu.sms.dao;

import com.lsnu.sms.model.Staff;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@ResponseBody
public interface StaffDao {
    int edit(Staff staff);

    Staff getInfo(String workId);

    List<Staff> getList(Map<String, Object> queryMap);

    int getTotal(Map<String, Object> queryMap);

    Staff findById(String workId);

    int add(Staff staff);

    int delete(String deptId);

    List<Staff> findByDeptId(String deptId);

    List<String> getAllWorkId();
}
