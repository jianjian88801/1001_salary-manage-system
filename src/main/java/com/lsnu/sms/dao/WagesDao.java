package com.lsnu.sms.dao;

import com.lsnu.sms.model.Wages;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@ResponseBody
public interface WagesDao {
    int findByTimes(Object times);

    int create(Wages wages);

    List<Wages> getlist(Map<String, Object> queryMap);

    int getTotal(Map<String, Object> queryMap);

    int delete(String workId);
}
