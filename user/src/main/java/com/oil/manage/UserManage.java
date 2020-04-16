package com.oil.manage;

import com.oil.entity.Role;
import com.oil.entity.User;
import com.oil.utils.Result;
import org.springframework.web.bind.annotation.RequestBody;

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

     List<User> findByNameLike(String name);

     User findByName(String name);

}
