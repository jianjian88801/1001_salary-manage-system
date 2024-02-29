package com.lsnu.sms.dao;

import com.lsnu.sms.model.Attendance;
import com.lsnu.sms.model.Wages;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@ResponseBody
public interface AttendanceDao {
    int add(Map<String, Object> queryMap);

    List<Attendance> getList(Map<String, Object> queryMap);

    int getTotal(Map<String, Object> queryMap);

    int findByTimes(Object times);

    int edit(Attendance attendance);

    Attendance getValues(Wages wages);

    List<String> getAllWorkId(String times);

    int delete(String workId);
}
