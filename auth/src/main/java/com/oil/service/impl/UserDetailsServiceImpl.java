package com.oil.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.oil.entity.Permission;
import com.oil.entity.Role;
import com.oil.service.UserService;
import com.oil.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义用户认证与授权
 * <p>
 * Description:
 * </p>
 *
 * @author Lusifer
 * @version v1.0.0
 * @date 2019-04-04 23:57:04
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("进入LoadUserByUsername()方法，开始登录用户校验");
        Result user = userService.findByName(username);
        JSONObject jb = new JSONObject(user);
//        JSONObject jsonObject = JSONObject.toJSON()
        if (null != jb.get("data")) {
            com.oil.entity.User u = jb.getObject("data", com.oil.entity.User.class);

            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            if (u == null) {
                throw new UsernameNotFoundException("用户名或密码错误");
            }
            for (Role role : u.getRoleList()) {
                log.info("获取用户角色：" + role.getRoleName());
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleKey());
                grantedAuthorities.add(grantedAuthority);
                for (Permission permission : role.getPermissions()) {
                    GrantedAuthority pm = new SimpleGrantedAuthority(permission.getUrl());
                    grantedAuthorities.add(pm);
                    log.info("获取用户权限：" + permission.getUrl());
                }
            }
            return new User(u.getLoginName(), u.getPassword(), grantedAuthorities);
        }
        return null;
    }
}
