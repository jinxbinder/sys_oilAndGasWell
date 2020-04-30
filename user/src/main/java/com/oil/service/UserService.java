package com.oil.service;

import com.alibaba.fastjson.JSONObject;
import com.oil.api.UserApi;
import com.oil.entity.Role;
import com.oil.entity.User;
import com.oil.manage.RoleManage;
import com.oil.manage.UserManage;
import com.oil.page.Pages;
import com.oil.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

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
    public Result adminListByPage(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize) {
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
        try {
            Page<User> user = userManage.userListByPage(PageRequest.of(pageNum,pageSize));
            log.info("分页查询成功："+user.getContent());
//            while (user.getContent().iterator().hasNext()){
//                user.getContent().iterator().next().setPassword("");
//            }
//            log.info("page:"+user.getContent());
//            log.info("page:"+user.getNumber());
//            log.info("page:"+user.getSize());
//            log.info("page:"+user.getTotalElements());
            Pages<User> u = new Pages<>();
            u.setContent(user.getContent());
            u.setPageNo(user.getNumber());
            u.setPageSize(user.getSize());
            u.setTotal(user.getTotalElements());
            return Result.success(u);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return Result.error();
    }
    @Override
    public Result roleListByPage(int pageNum, int pageSize) {
        try {
            Page<Role> roles = roleManage.roleListByPage(PageRequest.of(pageNum,pageSize));
            log.info("分页查询成功："+roles.getContent());
            Pages<Role> r = new Pages<>();
            r.setContent(roles.getContent());
            r.setPageNo(roles.getNumber());
            r.setPageSize(roles.getSize());
            r.setTotal(roles.getTotalElements());
            return Result.success(r);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return Result.error();
    }

    @Override
    public Result findRoles() {
        return Result.success(roleManage.findRoles());
    }

    @Override
    public Result roleAdd(@RequestBody Role role) {
        try {
            roleManage.roleAdd(role);
        }catch (Exception e){
            log.error("角色新增失败：error#####",e);
            Result.error("角色新增失败");
        }
        return Result.success();
    }

    @Override
    public Result userAdd(@RequestBody User user){

//        String salt = MD5Util.RandomSelt();
//        String password = MD5Util.MD5(user.getPassword()+salt);
//        String password = bCryptPasswordEncoder.encode(user.getPassword());
//        user.setPassword(password);
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
        try {
            userManage.userUpdate(user);
        }catch (Exception e){
            log.error("用户信息修改失败：error#####",e);
            Result.error("用户信息修改失败");
        }

        return Result.success();
    }
    @Override
    public Result findByNameLike(@RequestBody JSONObject json){
        String loginName = json.getString("username");
        String start = json.getString("start");
        String end = json.getString("end");
        Page<User> user = userManage.findByNameLike(loginName,start,end);
        Pages<User> u = new Pages<>();
        u.setContent(user.getContent());
        u.setPageNo(user.getNumber());
        u.setPageSize(user.getSize());
        u.setTotal(user.getTotalElements());
        return Result.success(u);
    }

    @Override
    public Result adminStatus(Long id,String status) {
        try {
            userManage.adminStatus(id,status);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @Override
    public Result findByName(@RequestBody String name){
        return Result.success(userManage.findByName(name));
    }

    @Override
    public Result findByRoleName(@RequestBody String roleName){
        return Result.success(roleManage.findByRoleName(roleName));
    }


    @Override
    public void setLoginTime(@RequestBody Long id) {
        log.info("进入setLoginTime方法"+DateUtil.getTimestamp());
        userManage.setLoginTime(id,DateUtil.getTimestamp());
    }

    @Override
    public Result adminDeleteOne(@RequestParam("id") Long id) {
        try {
            userManage.adminDeleteOne(id);
            return Result.success();
        } catch (Exception e) {
            log.error("单个管理员删除错误。",e);
            return Result.error();
        }
    }
    @Override
    public Result roleDeleteOne(@RequestParam("id") Long id) {
        try {
            roleManage.roleDeleteOne(id);
            return Result.success();
        } catch (Exception e) {
            log.error("单个角色删除错误。",e);
            return Result.error();
        }
    }
    @Override
    public Result adminDeleteSome(String id) {
        try {
            String[] uid = id.split(",");
            List<Long> ids = new ArrayList<>();
            for(int i=0;i<uid.length;i++){
                if(StringUtil.isNumericzidai(uid[i])){
                    ids.add(Long.parseLong(uid[i]));
                }
            }
            userManage.adminDeleteSome(ids);
            return Result.success();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return Result.error();
        }
    }
    @Override
    public Result roleUpdate(@RequestBody Role role){
        if (StringUtil.isNull(role))
            return Result.error("空参");
        try {
            roleManage.roleUpdate(role);
        }catch (Exception e){
            log.error("角色信息修改失败：error#####",e);
            Result.error("角色信息修改失败");
        }
        return Result.success();

    }
    @Override
    public Result roleStatus(Long id,String status) {
        try {
            roleManage.roleStatus(id,status);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @Override
    public Result newpass(User user) {
        try {
            userManage.newpass(user.getLoginName(),user.getPassword());
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }
}
