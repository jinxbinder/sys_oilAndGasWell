package com.oil.dao;

import com.oil.entity.User;
import com.oil.page.Pages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

/**
 * ClassName: UserRepository <br/>
 * Description: <br/>
 * date: 2020/3/25 19:48<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Transactional
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByUserId(Long id);

    @Query("select u from User u where u.loginName like CONCAT('%',?1,'%')")
    List<User> findByLoginNameLike(String loginName);

    User findByLoginName(String loginName);
    @Modifying
    @Query("update User u set u.loginDate = ?2 where u.userId = ?1")
    void setLoginTime(Long id, Timestamp time);

    @Query("select u from User u where u.delFlag <> 1")
    Page<User> findUseable(Pageable pageable);

    @Modifying
    @Query("update User u set u.delFlag = 1 where u.userId = ?1")
    void userDel(Long id);

    @Modifying
    @Query("update User u set u.delFlag = 1 where u.userId in (?1) ")
    void userDelSome(List<Long> ids);


}
