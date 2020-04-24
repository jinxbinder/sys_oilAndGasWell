package com.oil;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.security.acl.PrincipalImpl;

import java.security.Principal;

/**
 * ClassName: SecurityTest <br/>
 * Description: <br/>
 * date: 2020/4/24 17:59<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@SpringBootTest
public class SecurityTest {
    @Test
    public void sTest(){
        Principal principal = new PrincipalImpl("admin");
        System.out.println(principal.getName());
    }
}
