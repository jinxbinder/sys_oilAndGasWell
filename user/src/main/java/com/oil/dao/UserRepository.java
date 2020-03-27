package com.oil.dao;

import com.oil.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * ClassName: UserRepository <br/>
 * Description: <br/>
 * date: 2020/3/25 19:48<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByUserId(Long id);

    User findUserByLoginNameAndPassword(String loginName,String password);


}
