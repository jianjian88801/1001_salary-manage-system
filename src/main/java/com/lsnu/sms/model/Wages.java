package com.lsnu.sms.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Wages {
    private Integer id;
    private String workId;
    private String trueName;
    private String times;//时间
    private String baseSalary;//底薪
    private String subsidy;//津贴
    private String late;//迟到
    private String early;//早退
    private String leave;//请假
    private String overtimes;//加班
    private String endowment;//养老
    private String unemployment;//失业
    private String injury;//工伤
    private String maternity;//生育
    private String medical;//医疗
    private String accumulation;//公积金
    private String finalSalary;//实发工资
}
