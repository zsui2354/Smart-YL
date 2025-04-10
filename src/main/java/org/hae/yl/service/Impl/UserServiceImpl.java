package org.hae.yl.service.Impl;

import org.hae.yl.entity.User;
import org.hae.yl.mapper.UserMapper;
import org.hae.yl.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper usermapper;


    @Override
    public List<User> SelectAll() {
        return usermapper.SelectAll();
    }

    @Override
    public User SelectById(int id) {
        return usermapper.selectById(id);
    }

    @Override
    public User SelectByUsername(String username) {
        return usermapper.selectByUsername(username);
    }

    @Override
    public User selectSysadmin() {
        return usermapper.selectSysadmin();
    }
}
