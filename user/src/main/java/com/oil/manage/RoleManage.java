package com.oil.manage;

import com.oil.entity.Role;
import com.oil.utils.Result;

import java.util.List;

/**
 * ClassName: RoleManage <br/>
 * Description: <br/>
 * date: 2020/4/16 10:56<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
public interface RoleManage {

    List<Role> findRoles();
}