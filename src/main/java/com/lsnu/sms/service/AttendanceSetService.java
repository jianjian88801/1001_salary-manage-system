package com.lsnu.sms.service;

import com.lsnu.sms.model.AttendanceSet;

public interface AttendanceSetService {
    int edit(AttendanceSet attendanceSet);

    AttendanceSet getList();
}
