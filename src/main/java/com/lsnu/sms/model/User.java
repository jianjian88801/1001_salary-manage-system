package com.lsnu.sms.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {
    private Integer id;
    private String workId;
    private String password;
    private Integer state;
    private String createTime;
    private String updateTime;
}
