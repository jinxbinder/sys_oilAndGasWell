package com.oil.service;

import com.oil.api.UserApi;
import com.oil.dao.UserRepository;
import com.oil.entity.User;
import com.oil.manage.RoleManage;
import com.oil.manage.UserManage;
import com.oil.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.StyledEditorKit;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: UserService <br/>
 * Description: <br/>
 * date: 2020/3/25 19:54<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Slf4j
@RestController
public class UserService implements UserApi{
    @Autowired
    private UserManage userManage;
    @Autowired
    private RoleManage roleManage;
    @Override
    public String getUser() {
        return userManage.getUser();
    }

    @Override
    public Result login(@RequestBody User user) {
        log.info(user.toString());

        User userInfo = userManage.login(user);
        if(StringUtil.isNull(userInfo))
            return Result.error("账号错误");

        String salt = userInfo.getSalt();
        String password = userInfo.getPassword();
        log.info("密码:"+password);
        log.info("盐:"+salt);
        String passSalt = MD5Util.MD5(user.getPassword()+salt);
        log.info("密盐："+passSalt);
        if(!password.equals(passSalt))
            return Result.error("密码错误");

        String id = userInfo.getUserId()+"";
        return Result.success("登录成功",id);
    }
    @Override
    public Result userList(){
        List<User> userList = userManage.userList();
        for (User user:userList){
            user.setPassword("");
            user.setSalt("");
        }
        return Result.success(userList);
    }
    @Override
    public Result userAdd(@RequestBody User user){
        user.setCreateTime(DateUtil.getTimestamp());
        String salt = MD5Util.RandomSelt();
        String password = MD5Util.MD5(user.getPassword()+salt);
        user.setPassword(password);
        try {
            userManage.userAdd(user);
        }catch (Exception e){
            log.error("用户新增失败：error#####",e);
            Result.error("用户新增失败");
        }

        return Result.success();
    }
    @Override
    public Result userUpdate(@RequestBody User user){
        if (StringUtil.isNull(user))
            return Result.error("空参");
        user.setUpdateTime(DateUtil.getTimestamp());
        try {
            userManage.userUpdate(user);
        }catch (Exception e){
            log.error("用户信息修改失败：error#####",e);
            Result.error("用户信息修改失败");
        }

        return Result.success();
    }
    @Override
    public Result findByNameLike(@RequestBody String name){

        return Result.success(userManage.findByNameLike(name));
    }
    @Override
    public Result findByName(@RequestBody String name){
        return Result.success(userManage.findByName(name));
    }

    @Override
    public Result findRoles() {
        return Result.success(roleManage.findRoles());
    }

    @Override
    public void setLoginTime(@RequestBody Long id) {
        log.info("进入setLoginTime方法"+DateUtil.getTimestamp());
        userManage.setLoginTime(id,DateUtil.getTimestamp());
    }

    @Override
    public Result adminDeleteOne(@RequestBody Long id) {
        try {
            userManage.adminDeleteOne(id);
            return Result.success();
        } catch (Exception e) {
            log.error("单个管理员删除错误。",e);
            return Result.error();
        }
    }
}
