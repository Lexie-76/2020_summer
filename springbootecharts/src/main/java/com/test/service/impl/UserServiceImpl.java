package com.test.service.impl;

import com.test.dao.UserDao;
import com.test.domain.User;
import com.test.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Override
    public List<User> findAll() {
        List<User> users = userDao.findAll();
        return users;
    }

    @Override
    public User findById(Integer id) {
        User user = userDao.getOne(id);
        return null;
    }

    @Override
    public User findByName(String name) {
        User user = userDao.findByName(name);
        return user;
    }

    @Override
    public User register(User user) {

        User dbuser = userDao.saveAndFlush(user);
        return dbuser;
    }
}
