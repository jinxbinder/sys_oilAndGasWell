package com.oil.service;

import com.oil.api.UserApi;
import com.oil.dao.UserRepository;
import com.oil.entity.User;
import com.oil.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        System.out.println("到达service");
        User str = userRepository.findUserByUserId(1L);
        System.out.println(str);
        return str.getLoginName();
    }

    @Override
    public Result login(@RequestBody User user) {
        log.info(user.toString());
        User userInfo = userRepository.findUserByLoginNameAndPassword(user.getLoginName(),user.getPassword());
        if(StringUtils.isEmpty(userInfo))
            return Result.error("账号或者密码错误");

        return Result.success(userInfo.getUserId());
    }
}
