package com.lsnu.sms.dao;

import com.lsnu.sms.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    User findByIdAndPassword(User user);

    int edit_pwd(User user);
}
