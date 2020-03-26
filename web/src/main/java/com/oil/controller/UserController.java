package com.oil.controller;

import com.oil.constants.Constant;
import com.oil.entity.User;
import com.oil.feign.UserFeign;
import com.oil.utils.Result;
import org.bouncycastle.jcajce.provider.digest.Blake2b;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * ClassName: UserController <br/>
 * Description: <br/>
 * date: 2020/3/25 20:01<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */

@Controller
public class UserController {
    @Resource
    UserFeign userFeign;
    @ResponseBody
    @RequestMapping("/aaaa")
    public String getUser(){
        return userFeign.getUser();
    }
    /**
    * Description: 功能描述（登录页跳转） <br/>
    * date: 2020/3/26 15:26<br/>
    * @author libd <br/>
    */
    @RequestMapping("/")
    public String loginPage(){
        return Constant.LOGIN;
    }
    /**
    * Description: 功能描述（登录） <br/>
    * date: 2020/3/26 15:28<br/>
    * @author libd <br/>
    */
    @PostMapping("/login")
    public String login(User user){
        Result.success(user);
        return "index";
    }
}
