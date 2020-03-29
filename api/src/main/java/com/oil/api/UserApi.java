package com.oil.api;

import com.oil.entity.User;
import com.oil.utils.Result;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: UserApi <br/>
 * Description: <br/>
 * date: 2020/3/25 19:51<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */

@RequestMapping("/user")
public interface UserApi {
    @ResponseBody
    @RequestMapping("/getUser")
    String getUser();
    /**
    * Description: 功能描述（登录） <br/>
    * date: 2020/3/26 15:23<br/>
    * @author libd <br/>
    */
    @RequestMapping("/login")
    Result login(@RequestBody User user);
    /**
    * Description: 功能描述（用户列表） <br/>
    * date: 2020/3/28 14:16<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/userList")
    Result userList();
    /**
    * Description: 功能描述（用户添加） <br/>
    * date: 2020/3/28 16:46<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/userAdd")
    Result userAdd(User user);
    /**
    * Description: 功能描述（用户修改） <br/>
    * date: 2020/3/28 16:56<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/userUpdate")
    Result userUpdate(User user);
    /**
    * Description: 功能描述（用户模糊查） <br/>
    * date: 2020/3/28 16:58<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/findByNameLike")
    Result findByNameLike(String name);
    /**
    * Description: 功能描述（名称查） <br/>
    * date: 2020/3/28 19:23<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/findByName")
    Result findByName(String name);
}
