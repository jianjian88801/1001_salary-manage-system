package com.lsnu.sms.service;

import com.lsnu.sms.model.Salary;

import java.util.List;
import java.util.Map;

public interface SalaryService {
    List<Salary> getList(Map<String, Object> queryMap);

    int getTotal(Map<String, Object> queryMap);

    int edit(Salary salary);

}
