package com.oil.manage.impl;

import com.oil.dao.UserRepository;
import com.oil.entity.User;
import com.oil.manage.UserManage;
import com.oil.utils.DateUtil;
import com.oil.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<User> findByNameLike(String loginName,String start ,String end) {
        Timestamp startTime = null;
        Timestamp endTime = null;
        if(StringUtil.isNotEmpty(start)){
             startTime = new Timestamp(DateUtil.stringToDate(start,DateUtil.DATE_TO_STRING_SHORT_PATTERN).getTime());
        }
        if(StringUtil.isNotEmpty(end)){
             endTime = new Timestamp(DateUtil.stringToDate(end,DateUtil.DATE_TO_STRING_SHORT_PATTERN).getTime());

        }


        return userRepository.findByLoginNameLike(loginName,startTime,endTime,PageRequest.of(0,5));


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

        userRepository.userDel(id);
    }

    @Override
    public Page<User> userListByPage(Pageable pageable) {
        return userRepository.findUseable(pageable);

    }

    @Override
    public void adminDeleteSome(List<Long> ids) {
        userRepository.userDelSome(ids);
    }

    @Override
    public void adminStatus(Long id,String status) {
        userRepository.adminStatus(id,status);
    }

}
