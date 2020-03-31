package com.oil.controller;

import com.oil.constants.Constant;
import com.oil.entity.User;
import com.oil.feign.UserFeign;
import com.oil.utils.Result;
import com.oil.utils.StringUtil;
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
import java.util.List;
import java.util.Map;

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
        if (!StringUtil.isNull(r.get("data"))){
            String id =r.get("data")+"";
            log.info("用户id："+id);
            model.addAttribute("id",id);
            return Constant.INDEX;
        }
        log.info(r.get("msg")+"vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
        model.addAttribute("msg",r.get("msg"));
        return Constant.LOGIN;
    }
    /**
    * Description: 功能描述（用户列表） <br/>
    * date: 2020/3/28 14:13<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/userList")
    public String userList(Model model){
        Result r = userFeign.userList();
        if(StringUtil.isNull(r.get("data"))){
            return Constant.INDEX;
        }
        log.info("data 不为空");
        List<User> userList = (List<User>) r.get("data");
        model.addAttribute("userList",userList);
        return Constant.INDEX;
    }
}
