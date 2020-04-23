package com.oil.controller;

import com.alibaba.fastjson.JSONObject;
import com.oil.constants.Constant;
import com.oil.entity.Role;
import com.oil.entity.User;
import com.oil.feign.UserFeign;
import com.oil.page.Pages;
import com.oil.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

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
}
