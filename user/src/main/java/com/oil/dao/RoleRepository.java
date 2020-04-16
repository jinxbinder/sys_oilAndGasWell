package com.oil.dao;

import com.oil.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ClassName: RoleRepository <br/>
 * Description: <br/>
 * date: 2020/4/16 10:53<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
