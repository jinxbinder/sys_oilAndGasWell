package com.oil.dao;

import com.oil.entity.Role;
import com.oil.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * ClassName: RoleRepository <br/>
 * Description: <br/>
 * date: 2020/4/16 10:53<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Transactional
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query("select r from Role r where r.delFlag <> 1")
    Page<Role> findRoleable(Pageable pageable);

    Role findByRoleName(String roleName);

    @Modifying
    @Query("update Role r set r.delFlag = 1 where r.roleId = ?1")
    void roleDel(Long id);

    @Modifying
    @Query("update Role r set r.status = ?2 where r.roleId = ?1")
    void roleStatus(Long id,String status);
}
