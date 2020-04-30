package com.oil.manage;

import com.oil.entity.Role;
import com.oil.entity.User;
import com.oil.page.Pages;
import com.oil.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.util.List;

/**
 * ClassName: UserManage <br/>
 * Description: <br/>
 * date: 2020/4/16 10:26<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
public interface UserManage {

     String getUser();

     User login(User user);

     List<User> userList();

     void userAdd(User user);

     void userUpdate(User user);

     Page<User> findByNameLike(String loginName,String start,String end);

     User findByName(String name);

     void setLoginTime(Long id,Timestamp time);

     void adminDeleteOne(Long id);

     Page<User> userListByPage(Pageable pageable);

     void adminDeleteSome(List<Long> ids);

     void adminStatus(Long id,String status);

     void newpass(String loginName,String password);
}
