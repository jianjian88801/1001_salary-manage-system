package com.lsnu.sms.dao;

import com.lsnu.sms.model.AttendanceSet;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface AttendanceSetDao {
    int edit(AttendanceSet attendanceSet);

    AttendanceSet getList();
}
