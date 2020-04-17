package com.oil.manage.impl;

import com.oil.dao.UserRepository;
import com.oil.entity.Role;
import com.oil.entity.User;
import com.oil.manage.UserManage;
import com.oil.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * ClassName: UserManageImpl <br/>
 * Description: <br/>
 * date: 2020/4/16 10:29<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class UserManageImpl implements UserManage {
    @Autowired
    UserRepository userRepository;

    @Override
    public String getUser() {
        User str = userRepository.findUserByUserId(1L);
        System.out.println(str);
        return str.getLoginName();
    }

    @Override
    public User login(User user) {
        User user1 = userRepository.findByLoginName(user.getLoginName());
        return user1;
    }

    @Override
    public List<User> userList() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public void userAdd(User user) {
        userRepository.save(user);
    }

    @Override
    public void userUpdate(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findByNameLike(String name) {
        return userRepository.findByLoginNameLike(name);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByLoginName(name);
    }

    @Override
    public void setLoginTime(Long id, Timestamp time) {
        userRepository.setLoginTime(id,time);
    }

    @Override
    public void adminDeleteOne(Long id) {
        userRepository.deleteById(id);
    }

}
