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
}
