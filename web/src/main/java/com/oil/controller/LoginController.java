package com.oil.controller;

import com.alibaba.fastjson.JSONObject;
import com.oil.constants.Constant;
import com.oil.entity.User;
import com.oil.feign.UserFeign;
import com.oil.utils.Result;
import com.oil.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jcajce.provider.digest.Blake2b;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
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
public class LoginController {
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
        log.info("主页方法");
        return Constant.INDEX;
    }
    /**
    * Description: 功能描述（跳到登录页） <br/>
    * date: 2020/3/26 15:28<br/>
    * @author libd <br/>
    */
    @GetMapping("/login")
    public String login(){

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
    /**
    * Description: 功能描述（404） <br/>
    * date: 2020/3/31 13:01<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/404")
    public String get404(){
        return "error";
    }
    /**
    * Description: 功能描述（403） <br/>
    * date: 2020/3/31 13:01<br/>
    * @author libd <br/>
    */
    @RequestMapping("/403")
    public String get403(){
        return "403";
    }
    /**
    * Description: 功能描述（加载欢迎页） <br/>
    * date: 2020/4/17 10:53<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/welcome")
    public String welcome(Principal principal,Model model){

        Result r = userFeign.findByName(principal.getName());
        JSONObject jb = new JSONObject(r);
        if (null != jb.get("data") && "200".equals(jb.get("code").toString())) {
            User u = jb.getObject("data",User.class);
            model.addAttribute("loginDate",u.getLoginDate());
        }
        return "welcome";
    }
}
