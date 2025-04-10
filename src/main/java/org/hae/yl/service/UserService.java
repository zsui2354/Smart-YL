package org.hae.yl.service;

import org.hae.yl.entity.User;

import java.util.List;

public interface UserService {

    //查询所有
    List<User> SelectAll();

    //根据 Id 查询
    User SelectById(int id);

    //根据用户名查询
    User SelectByUsername(String username);

    User selectSysadmin();
}
