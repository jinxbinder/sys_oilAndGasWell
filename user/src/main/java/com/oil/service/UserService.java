package com.oil.service;

import com.oil.api.UserApi;
import com.oil.dao.UserRepository;
import com.oil.entity.User;
import com.oil.utils.DateUtil;
import com.oil.utils.MD5Util;
import com.oil.utils.Result;
import com.oil.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.StyledEditorKit;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
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
    UserRepository userRepository;
    @Override
    public String getUser() {
        User str = userRepository.findUserByUserId(1L);
        System.out.println(str);
        return str.getLoginName();
    }

    @Override
    public Result login(@RequestBody User user) {
        log.info(user.toString());

        User userInfo = userRepository.findByLoginName(user.getLoginName());
        if(StringUtil.isNull(userInfo))
            return Result.error("账号错误");

        String salt = userInfo.getSalt();
        String password = userInfo.getPassword();
        String passSalt = MD5Util.MD5(user.getPassword()+salt);
        if(password!=passSalt)
            return Result.error("密码错误");

        String id = userInfo.getUserId()+"";
        return Result.success("登录成功",id);
    }
    @Override
    public Result userList(){
        List<User> userList = userRepository.findAll();
        for (User user:userList){
            user.setPassword("");
            user.setSalt("");
        }
        return Result.success(userList);
    }
    @Override
    public Result userAdd(User user){
        user.setCreateTime(DateUtil.getTimestamp());
        String salt = MD5Util.RandomSelt();
        String password = MD5Util.MD5(user.getPassword()+salt);
        user.setPassword(password);
        try {
            userRepository.save(user);
        }catch (Exception e){
            log.error("用户新增失败：error#####",e);
            Result.error("用户新增失败");
        }

        return Result.success();
    }
    @Override
    public Result userUpdate(User user){
        if (StringUtil.isNull(user))
            return Result.error("空参");
        user.setUpdateTime(DateUtil.getTimestamp());
        try {
            userRepository.save(user);
        }catch (Exception e){
            log.error("用户信息修改失败：error#####",e);
            Result.error("用户信息修改失败");
        }

        return Result.success();
    }
    @Override
    public Result findByNameLike(String name){

        return Result.success(userRepository.findByLoginNameLike(name));
    }
    @Override
    public Result findByName(String name){
        return Result.success(userRepository.findByLoginName(name));
    }
}
