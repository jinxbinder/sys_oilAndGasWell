package com.oil.controller;

import com.oil.constants.Constant;
import com.oil.entity.User;
import com.oil.feign.UserFeign;
import com.oil.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jcajce.provider.digest.Blake2b;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * ClassName: UserController <br/>
 * Description: <br/>
 * date: 2020/3/25 20:01<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Slf4j
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
    @RequestMapping("/login")
    public String login(User user, Model model){
        Result r = userFeign.login(user);
        if (null!=r.get("data")){
            Object res = r.get("data");
            log.info(res.toString());
        }
        model.addAllAttributes(r);
        if(Constant.SUCCESS.equals(r.get("code")+"")){
            return "index";
        }
        return Constant.LOGIN;
    }
}
