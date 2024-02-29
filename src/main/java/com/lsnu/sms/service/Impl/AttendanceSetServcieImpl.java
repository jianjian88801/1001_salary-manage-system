package com.lsnu.sms.service.Impl;

import com.lsnu.sms.dao.AttendanceSetDao;
import com.lsnu.sms.model.AttendanceSet;
import com.lsnu.sms.service.AttendanceSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceSetServcieImpl implements AttendanceSetService {
    @Autowired
    private AttendanceSetDao attendanceSetDao;

    @Override
    public int edit(AttendanceSet attendanceSet) {
        return attendanceSetDao.edit(attendanceSet);
    }

    @Override
    public AttendanceSet getList() {
        return attendanceSetDao.getList();
    }
}
