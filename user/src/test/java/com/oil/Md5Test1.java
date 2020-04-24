package com.oil;

import com.oil.dao.UserRepository;
import com.oil.utils.MD5Util;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: com.oil.Md5Test1 <br/>
 * Description: <br/>
 * date: 2020/3/30 22:17<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Rollback
@SpringBootTest(classes = {Md5Test1.class})
public class Md5Test1 {
    @Resource
    private UserRepository userRepository;
    private MockMvc mockMvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp(){
        System.out.println("1111111111111");
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void md1(){
        System.out.println(MD5Util.MD5("123"));
    }


    @Test
    public void UserTest(){
        String url = "/user/findByName";
        Map<Object,Object> objectMap =  new HashMap<>();
        objectMap.put("name","admin");
        try {
           MvcResult r =  mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .param("name","admin")
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print()).andReturn();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
