package com.oil.service;

import com.oil.api.UserApi;
import com.oil.dao.UserRepository;
import com.oil.entity.User;
import com.oil.utils.Result;
import com.oil.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: UserService <br/>
 * Description: <br/>
 * date: 2020/3/25 19:54<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Slf4j
@RestController
public class UserService implements UserApi{
    @Autowired
    UserRepository userRepository;
    @Override
    public String getUser() {
        User str = userRepository.findUserByUserId(1L);
        System.out.println(str);
        return str.getLoginName();
    }

    @Override
    public Result login(@RequestBody User user) {
        log.info(user.toString());
        User userInfo = userRepository.findUserByLoginNameAndPassword(user.getLoginName(),user.getPassword());
        if(StringUtil.isNull(userInfo))
            return Result.error("账号或者密码错误");
        String id = userInfo.getUserId()+"";
        return Result.success("登录成功",id);
    }
    @Override
    public Result userList(){
        List<User> userList = userRepository.findAll();
        return Result.success(userList);
    }
}
