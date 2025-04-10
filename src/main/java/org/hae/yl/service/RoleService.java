package org.hae.yl.service;

import org.hae.yl.entity.Role;

import java.util.List;

public interface RoleService {

     List<Role> SelectAll();

     Role SelectById(int id);

}
