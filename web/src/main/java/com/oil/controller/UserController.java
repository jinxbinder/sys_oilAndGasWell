package com.oil.controller;

import com.oil.constants.Constant;
import com.oil.entity.Role;
import com.oil.entity.User;
import com.oil.feign.UserFeign;
import com.oil.utils.Result;
import com.oil.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.math.raw.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * 管理员列表
     * @param model
     * @return
     */
    @RequestMapping("/adminList")
    public String adminList(Model model){
        Result r = userFeign.userList();
        if(StringUtil.isNull(r.get("data"))){
            return "404";
        }
//        log.info("data 不为空");
        List<User> userList = (List<User>) r.get("data");
        model.addAttribute("userList",userList);
        return "admin-list";
    }

    /**
     * 管理员添加页面跳转
     * @param model
     * @return
     */
    @RequestMapping("/adminAdd")
    public String adminAdd(Model model){
        Result r = userFeign.findRoles();
        if(StringUtil.isNull(r.get("data"))){
            return "404";
        }
//        log.info("data 不为空");
        List<Role> roleList = (List<Role>) r.get("data");
        model.addAttribute("roleList",roleList);
        return "admin-add";
    }

    /**
     * 管理员添加  ajax 返回字符串
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping("/userAdd")
    public Result userAdd(@RequestBody User user){
        log.info("用户信息"+user.toString());
        Result r = userFeign.userAdd(user);
        return r;
        /*if("200".equals(r.get("code").toString())){
            return "添加成功";
        }
        return "添加失败";*/
    }

    /**
     * 管理员修改页面跳转
     * @return
     */
    @RequestMapping("/adminEdit")
    public String adminEdit(){
        return "admin-edit";
    }

    /**
     * 管理员修改
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("/userUpdate")
    public Result userEdit(@RequestBody User user){
        log.info("用户信息"+user.toString());
        Result r = userFeign.userUpdate(user);
        return r;
    }

    /**
     * 删除单个管理员
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/adminDeleteOne")
    public Result adminDeleteOne(@RequestBody String id){
        Result r = userFeign.adminDeleteOne(Long.parseLong(id));
        return r;
    }

}
