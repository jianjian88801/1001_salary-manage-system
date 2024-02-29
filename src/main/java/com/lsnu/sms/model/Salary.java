package com.lsnu.sms.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Salary {
    private Integer id;
    private String workId;
    private String trueName;
    private String baseSalary;
    private String subsidy;//津贴
}
