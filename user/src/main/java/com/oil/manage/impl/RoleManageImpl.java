package com.oil.manage.impl;

import com.oil.dao.RoleRepository;
import com.oil.entity.Role;
import com.oil.manage.RoleManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: RoleManageImpl <br/>
 * Description: <br/>
 * date: 2020/4/16 10:56<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Service
public class RoleManageImpl implements RoleManage{
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<Role> findRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Page<Role> roleListByPage(Pageable pageable) {
        return roleRepository.findRoleable(pageable);
    }

    @Override
    public void roleAdd(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void roleUpdate(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void roleDeleteOne(Long id) {

        roleRepository.roleDel(id);
    }

    @Override
    public void roleStatus(Long id,String status) {
        roleRepository.roleStatus(id,status);
    }
}
