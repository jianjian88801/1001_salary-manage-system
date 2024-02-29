package com.lsnu.sms.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AttendanceSet {
    private Integer id;
    private String late;//迟到
    private String earlyLeave;//早退
    private String leave;//请假
    private String overtime;//加班
}
