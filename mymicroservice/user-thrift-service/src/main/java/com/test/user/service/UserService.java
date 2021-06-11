package com.test.user.service;

import com.test.thrift.user.UserInfo;

public interface UserService {
    void registerUser(UserInfo userInfo);

    UserInfo getUserById(int id);

    UserInfo getUserByName(String username);
}
