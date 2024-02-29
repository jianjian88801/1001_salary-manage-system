package com.lsnu.sms.service;

import com.lsnu.sms.model.Attendance;

import java.util.List;
import java.util.Map;

public interface AttendanceService {
    int add(Map<String, Object> queryMap);

    List<Attendance> getList(Map<String, Object> queryMap);

    int getTotal(Map<String, Object> queryMap);

    int edit(Attendance attendance);
}
