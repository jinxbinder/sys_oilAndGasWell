package com.oil.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * ClassName: UserController <br/>
 * Description: <br/>
 * date: 2020/4/10 16:44<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@RestController
public class UserController {
    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
