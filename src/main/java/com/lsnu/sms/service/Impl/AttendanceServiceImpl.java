package com.lsnu.sms.service.Impl;

import com.lsnu.sms.dao.AttendanceDao;
import com.lsnu.sms.dao.StaffDao;
import com.lsnu.sms.model.Attendance;
import com.lsnu.sms.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceDao attendanceDao;
    @Autowired
    private StaffDao staffDao;

    @Override
    public int add(Map<String, Object> queryMap) {
        int res = 0;
        Object workId = queryMap.get("workId");
        Object times = queryMap.get("times");
        if(attendanceDao.findByTimes(times) == 0){
            if (workId.equals("")) {
                List<String> list = staffDao.getAllWorkId();
                for (int i=0;i<list.size();i++){
                    queryMap.replace("workId",list.get(i));
                    res = attendanceDao.add(queryMap);
                }
            }
        }
        return res;
    }

    @Override
    public List<Attendance> getList(Map<String, Object> queryMap) {
        return attendanceDao.getList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return attendanceDao.getTotal(queryMap);
    }

    @Override
    public int edit(Attendance attendance) {
        return attendanceDao.edit(attendance);
    }
}
