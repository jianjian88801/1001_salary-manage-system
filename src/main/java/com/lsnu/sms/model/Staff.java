package com.lsnu.sms.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Staff extends User {
    private String workId;//工号
    private String trueName;//姓名
    private String idCard;//身份证号
    private String birthday;//生日
    private String politics;//政治面貌
    private String sex;//性别
    private String marry;//婚姻状况
    private String nativePlace;//籍贯
    private String phone;//电话
    private String graduatedSchool;//毕业院校
    private String education;//学历
    private String entryTime;//入职时间
    private String deptId;//部门编号
    private String deptName;//部门名称
    private String address;//住址
    private String remark;
    private String updateTime;
    private String timeMin;
    private String timeMax;
}
