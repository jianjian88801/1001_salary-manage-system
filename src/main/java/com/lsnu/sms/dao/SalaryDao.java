package com.lsnu.sms.dao;

import com.lsnu.sms.model.Salary;
import com.lsnu.sms.model.Staff;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@ResponseBody
public interface SalaryDao {
    int add(String workId);

    List<Salary> getList(Map<String, Object> queryMap);

    int getTotal(Map<String, Object> queryMap);

    int delete(String workId);

    int edit(Salary salary);

    String getValue(String workId);

}
