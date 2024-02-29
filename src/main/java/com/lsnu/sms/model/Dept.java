package com.lsnu.sms.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Dept {
    private Integer id;
    private String deptId;
    private String deptName;
    private String workId;
    private String deptManager;
    private String deptPhone;
    private String remark;
    private String createTime;
    private String updateTime;
}
