package com.lsnu.sms.service;

import com.lsnu.sms.model.Wages;

import java.util.List;
import java.util.Map;

public interface WagesService {
    int create(Map<String, Object> queryMap);

    List<Wages> getList(Map<String, Object> queryMap);

    int getTotal(Map<String, Object> queryMap);
}
