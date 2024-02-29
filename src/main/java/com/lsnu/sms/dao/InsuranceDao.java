package com.lsnu.sms.dao;

import com.lsnu.sms.model.Insurance;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface InsuranceDao {
    Insurance getList();

    int edit(Insurance insurance);
}
