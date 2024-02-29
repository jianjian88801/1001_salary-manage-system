package com.lsnu.sms.service;

import com.lsnu.sms.model.Insurance;

public interface InsuranceService {
    Insurance getList();

    int edit(Insurance insurance);
}
