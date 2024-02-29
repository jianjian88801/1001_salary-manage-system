package com.lsnu.sms.service;

import com.lsnu.sms.model.User;

public interface UserService {
    User findByIdAndPassword(User user);

    int edit_pwd(User user);
}
