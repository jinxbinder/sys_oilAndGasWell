package com.oil.service;

import com.oil.api.UserApi;
import com.oil.dao.UserRepository;
import com.oil.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserService <br/>
 * Description: <br/>
 * date: 2020/3/25 19:54<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class UserService implements UserApi{
    @Autowired
    UserRepository userRepository;
    @Override
    public String getUser() {
        System.out.println("到达service");
        String str = userRepository.findUserByUserId(1L);
        System.out.println(str);
        return str;
    }
}
