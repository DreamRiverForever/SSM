package com.itheima.dao;

import com.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface IUserDao {

    @Select("select * from users where username=#{userName}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findByUsername(String userName);

    @Select("select * from users")
    List<UserInfo> findAll();

    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo info);
}
