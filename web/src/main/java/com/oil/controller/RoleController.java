package com.oil.controller;

import com.alibaba.fastjson.JSONObject;
import com.oil.constants.Constant;
import com.oil.entity.Role;
import com.oil.entity.User;
import com.oil.feign.UserFeign;
import com.oil.page.Pages;
import com.oil.utils.DateUtil;
import com.oil.utils.Result;
import com.oil.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

/**
 * ClassName: RoleController <br/>
 * Description: <br/>
 * date: 2020/4/23 19:51<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Slf4j
@Controller
public class RoleController {
    @Resource
    UserFeign userFeign;
    /**
     * 角色列表
     * @param model
     * @return
     */
    @RequestMapping("/roleListByPage")
    public String adminList(Model model, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize){
        Result r = userFeign.roleListByPage(pageNum, pageSize);
        JSONObject jb = new JSONObject(r);
        if(!Constant.SUCCESS.equals(jb.get("code").toString())){
            return "error";
        }
        Pages<Role> roleList = jb.getObject("data",Pages.class);

        model.addAttribute("roleList", roleList);

        return "admin-role";
    }
    /**
     * 角色添加页面跳转
     * @param model
     * @return
     */
    @RequestMapping("/roleAddPage")
    public String roleAdd(Model model){
        /*Result r = userFeign.findRoles();
        if(StringUtil.isNull(r.get("data"))){
            return "error";
        }
//        log.info("data 不为空");
        List<Role> roleList = (List<Role>) r.get("data");
        model.addAttribute("roleList",roleList);*/
        return "role-add";
    }
    /**
     * 角色添加  ajax 返回字符串
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping("/roleAdd")
    public Result roleAdd(@RequestBody Role role, Principal principal){
        role.setCreateTime(DateUtil.getTimestamp());
        role.setCreateBy(principal.getName());
        log.info("用户信息"+role.toString());
        Result r = userFeign.roleAdd(role);
        return r;
    }
    /**
     * 管理员修改页面跳转
     * @return
     */
    @RequestMapping("/roleEditPage/{roleName}")
    public String roleEditPage(@PathVariable("roleName") String roleName,Model model){
        log.info("name:"+roleName);
        Result r = userFeign.findByRoleName(roleName);
        if(Constant.SUCCESS.equals(r.get("code").toString())){
            JSONObject jb = new JSONObject(r);
            if (null != jb.get("data")) {
                Role role = jb.getObject("data", Role.class);
                model.addAttribute("role",role);
                /*Result r2 = userFeign.findRoles();
                if(StringUtil.isNull(r2.get("data"))){
                    return "admin-list";
                }
                List<Role> roleList = (List<Role>) r2.get("data");
                model.addAttribute("roleList",roleList);*/
                return "role-edit";
            }
        }
        return "admin-role";
    }
    /**
     * 角色修改
     * @param role
     * @return
     */
    @ResponseBody
    @PostMapping("/roleUpdate")
    public Result roleEdit(@RequestBody Role role,Principal principal){
        role.setUpdateTime(DateUtil.getTimestamp());
        role.setUpdateBy(principal.getName());
        log.info("用户信息"+role.toString());
        Result r = userFeign.roleUpdate(role);
        return r;
    }
    /**
     * 删除单个角色
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/roleDeleteOne/{id}")
    public Result roleDeleteOne(@PathVariable("id") String id){
        log.info("id:"+id);
        Result r = userFeign.roleDeleteOne(Long.parseLong(id));
        return r;
    }
    /**
     * 角色状态修改
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/roleStatus")
    public Result adminStatus(@RequestParam("id") String id,@RequestParam("status") String status){

        Result r = userFeign.roleStatus(Long.parseLong(id),status);
        return r;

    }
}
