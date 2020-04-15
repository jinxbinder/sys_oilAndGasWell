package com.oil.controller;

import com.oil.constants.Constant;
import com.oil.entity.User;
import com.oil.feign.UserFeign;
import com.oil.utils.Result;
import com.oil.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: UserController <br/>
 * Description: <br/>
 * date: 2020/4/15 18:11<br/>
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

    @RequestMapping("/adminList")
    public String adminList(Model model){
        Result r = userFeign.userList();
        if(StringUtil.isNull(r.get("data"))){
            return "404";
        }
        log.info("data 不为空");
        List<User> userList = (List<User>) r.get("data");
        model.addAttribute("userList",userList);
        return "admin-list";
    }
}
