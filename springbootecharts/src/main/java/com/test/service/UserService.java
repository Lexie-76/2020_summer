package com.test.service;


import com.test.domain.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Integer id);
    User findByName(String name);

    User register(User user);
}
