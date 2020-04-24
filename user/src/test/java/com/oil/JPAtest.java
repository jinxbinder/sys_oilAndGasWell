package com.oil;

import com.oil.dao.RoleRepository;
import com.oil.dao.UserRepository;
import com.oil.entity.Role;
import com.oil.entity.User;
import com.oil.manage.UserManage;
import com.oil.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: com.oil.JPAtest <br/>
 * Description: <br/>
 * date: 2020/4/13 18:58<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JPAtest {

    @Autowired
     UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Test
    public void cc(){
        User u = new User();
        u =  userRepository.findByLoginName("123");
        if(!StringUtils.isEmpty(u)){
            System.out.println(u.toString());
        }
    }
    @Test
    public void dd(){
        System.out.println(userRepository.findUserByUserId(3l));
    }
    /**
     * 级联操作测试
     */
    @Test
    public void jlSave(){
        User u = new User();
        u.setLoginName("李四");
//        u.setUserId(8l);
        Role r = new Role();
        r.setRoleName("李四用户");
//        r.setRoleId(3l);
        List<Role> roles = new ArrayList<>();
        roles.add(r);
        u.setRoleList(roles);
        userRepository.save(u);
    }
    @Test
    public void pageTest(){
        Page<User> user = userRepository.findUseable(PageRequest.of(0,5));
//                userManage.userListByPage(PageRequest.of(pageNum,pageSize));
//        log.info("分页查询成功："+user.getContent());
        System.out.println(user);
    }
    @Test
    public void roleAddTest(){
        Role role = new Role();
        role.setCreateTime(DateUtil.getTimestamp());
        role.setRoleName("test1");
        role.setRoleKey("test");
        role.setDelFlag("0");
        roleRepository.save(role);
    }

}
