package com.oil.controller;

import com.alibaba.fastjson.JSONObject;
import com.oil.constants.Constant;
import com.oil.entity.Role;
import com.oil.entity.User;
import com.oil.feign.UserFeign;
import com.oil.page.Pages;
import com.oil.utils.Result;
import com.oil.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 管理员列表
     * @param model
     * @return
     */
    @RequestMapping("/adminListByPage")
    public String adminList(Model model,@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "2") int pageSize){
        Result r = userFeign.adminListByPage(pageNum, pageSize);
        JSONObject jb = new JSONObject(r);
        if(!Constant.SUCCESS.equals(jb.get("code").toString())){
            return "error";
        }
//        if (null != jb.get("data")) {
        Pages<User> userList = jb.getObject("data",Pages.class);
//            com.oil.page.Pages<User> userList = jb.getObject("data", com.oil.page.Pages.class);
//        System.out.println("总页数" + userList.getTotalPage());
//        System.out.println("当前页数" + userList.getPageNo());
//        System.out.println("总数" + userList.getContentSize());
//        System.out.println("每页条数" + pageSize);
//        System.out.println("数据集" + userList.getContent());
//        System.out.println("当前页是：" + pageNum);
        model.addAttribute("userList", userList);

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
            return "error";
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
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
    public String adminEdit(@RequestParam("loginName") String loginName,Model model){
        log.info("name:"+loginName);
        Result r = userFeign.findByName(loginName);
        if(Constant.SUCCESS.equals(r.get("code").toString())){
            JSONObject jb = new JSONObject(r);
            if (null != jb.get("data")) {
                User u = jb.getObject("data", User.class);
                model.addAttribute("admin",u);
                Result r2 = userFeign.findRoles();
                if(StringUtil.isNull(r2.get("data"))){
                    return "admin-list";
                }
                List<Role> roleList = (List<Role>) r2.get("data");
                model.addAttribute("roleList",roleList);
                return "admin-edit";
            }
        }
        return "admin-list";
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
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Result r = userFeign.userUpdate(user);
        return r;
    }

    /**
     * 删除单个管理员
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/adminDeleteOne")
    public Result adminDeleteOne(@RequestParam("id") String id){
        log.info("id:"+id);
        Result r = userFeign.adminDeleteOne(Long.parseLong(id));
        return r;
    }

}
