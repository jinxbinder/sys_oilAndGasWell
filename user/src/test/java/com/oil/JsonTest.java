package com.oil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.oil.entity.Role;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: JsonTest <br/>
 * Description: <br/>
 * date: 2020/4/22 12:42<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@SpringBootTest
public class JsonTest {
    @Test
    public void jaTest(){
        String str2 = "[{\"delFlag\":\"0\",\"roleKey\":\"doc\",\"roleName\":\"医生\",\"status\":\"0\"},{\"delFlag\":\"0\",\"roleKey\":\"nav\",\"roleName\":\"护士\",\"status\":\"0\"}]";
        String str1 = "[\"{roleId=2, roleName=施工员, roleKey=worker, status=null, delFlag=null, createBy=null, createTime=null, updateBy=null, updateTime=null, remark=null, permissions=[]}\"]";
        System.out.println(str1);
        str1 = str1.replace("\\", "").replace("\"{", "{").replace("}\"", "}");
        System.out.println(str1);
        List<Role> roles = JSONArray.parseArray(str2, Role.class);
//        List<Role> roles = JSON.parseArray(str1,Role.class);
        System.out.println(roles.toString());
    }
    @Test
    public void asTest(){
        Role r1 = new Role();
        r1.setRoleName("医生");
        r1.setRoleKey("doc");
        Role r2 = new Role();
        r2.setRoleName("护士");
        r2.setRoleKey("nav");
        List<Role> roles = new ArrayList<>();
        roles.add(r1);
        roles.add(r2);
        String rolestr = JSON.toJSONString(roles);
        System.out.println(rolestr);
    }
}
