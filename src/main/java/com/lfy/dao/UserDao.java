package com.lfy.dao;

import com.lfy.pojo.User;

import java.util.List;

//dao 接口
public interface UserDao {
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    User findById(Integer id);
    List<User> findUserByName(String name);
    List<User> findUserByNameBySQL(String name);
    List<User> findUserByNameByQBC(String name);
}
