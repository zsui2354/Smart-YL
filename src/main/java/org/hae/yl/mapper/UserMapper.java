package org.hae.yl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.hae.yl.entity.User;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询
     */
    @Select("SELECT * FROM user")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "real_name", column = "real_name"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "avatar", column = "avatar"),
            @Result(property = "role_id", column = "role_id"),
            @Result(property = "status", column = "status"),
            @Result(property = "created_at", column = "created_at")
    })
    public List<User> SelectAll();

    //根据 用户Id查询
    @Select("SELECT * FROM user where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "real_name", column = "real_name"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "avatar", column = "avatar"),
            @Result(property = "role_id", column = "role_id"),
            @Result(property = "status", column = "status"),
            @Result(property = "created_at", column = "created_at")
    })
    public User selectByIdUser(int id);

    //根据 用户名查询
    @Select("SELECT * FROM user WHERE username = #{username}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "real_name", column = "real_name"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "avatar", column = "avatar"),
            @Result(property = "role_id", column = "role_id"),
            @Result(property = "status", column = "status"),
            @Result(property = "created_at", column = "created_at")
    })
    public User selectByUsername(String username);

    @Select("SELECT * FROM user WHERE username = 'user';")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "real_name", column = "real_name"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "avatar", column = "avatar"),
            @Result(property = "role_id", column = "role_id"),
            @Result(property = "status", column = "status"),
            @Result(property = "created_at", column = "created_at")
    })
    public User selectSysadmin();
}
