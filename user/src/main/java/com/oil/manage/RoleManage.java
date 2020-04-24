package com.oil.manage;

import com.oil.entity.Role;
import com.oil.entity.User;
import com.oil.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    Page<Role> roleListByPage(Pageable pageable);

    void roleAdd(Role role);

    Role findByRoleName(String roleName);

    void roleUpdate(Role role);

    void roleDeleteOne(Long id);
}
