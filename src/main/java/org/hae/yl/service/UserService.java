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

    //根据 Id 修改
    public void Update(int id,User user);

    //根据 Id 删除
    public void Delete(int id);

    //根据 Id 批量删除
    public void DeleteBybatch(List<Integer> ids);

    //添加
    public void Insert(User user);
}
