package com.lsnu.sms.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Attendance {
    private Integer id;
    private String workId;
    private String trueName;
    private String times;//日期
    private Integer lateTimes;//迟到次数
    private Integer earlyTimes;//早退次数
    private Integer leaveTimes;//请假天数
    private Integer overtimeHours;//加班时长
}
