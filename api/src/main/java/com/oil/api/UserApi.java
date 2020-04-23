package com.oil.api;

import com.alibaba.fastjson.JSONObject;
import com.oil.entity.User;
import com.oil.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    @RequestMapping("/login_old")
    Result login(@RequestBody User user);
    /**
    * Description: 功能描述（用户列表） <br/>
    * date: 2020/3/28 14:16<br/>
    * @author libd <br/>
    */
    @RequestMapping("/userList")
    Result userList();
    /**
    * Description: 功能描述（用户列表分页查询） <br/>
    * date: 2020/4/18 17:02<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/adminListByPage")
    Result adminListByPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);
    /**
    * Description: 功能描述（用户添加） <br/>
    * date: 2020/3/28 16:46<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/userAdd")
    Result userAdd(@RequestBody User user);
    /**
    * Description: 功能描述（用户修改） <br/>
    * date: 2020/3/28 16:56<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/userUpdate")
    Result userUpdate(@RequestBody User user);
    /**
    * Description: 功能描述（名称查） <br/>
    * date: 2020/3/28 19:23<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/findByName")
    Result findByName(@RequestBody String name);
    /**
    * Description: 功能描述（登录时间） <br/>
    * date: 2020/4/17 10:36<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/setLoginTime")
    void setLoginTime(@RequestBody Long id);
    /**
    * Description: 功能描述（删除单个用户） <br/>
    * date: 2020/4/17 17:03<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/adminDeleteOne")
    Result adminDeleteOne(@RequestParam("id") Long id);
    /**
    * Description: 功能描述（用户批量删除） <br/>
    * date: 2020/4/20 18:10<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/adminDeleteSome")
    Result adminDeleteSome(@RequestParam("ids") String ids);
    /**
     * Description: 功能描述（用户模糊查） <br/>
     * date: 2020/3/28 16:58<br/>
     * @author libd <br/>
     */
    @RequestMapping("/findByNameLike")
    Result findByNameLike(@RequestBody JSONObject jsonObject);
    /**
    * Description: 功能描述（用户状态修改） <br/>
    * date: 2020/4/23 18:24<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/adminStatus")
    Result adminStatus(@RequestParam("id") Long id,@RequestParam("status") String status);
    /**
     * Description: 功能描述（角色列表分页查询） <br/>
     * date: 2020/4/23 17:02<br/>
     * @author libd <br/>
     */
    @RequestMapping("/roleListByPage")
    Result roleListByPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize);
    /**
     * Description: 功能描述（角色列表查询） <br/>
     * date: 2020/4/16 19:22<br/>
     * @author libd <br/>
     */
    @RequestMapping("/findRoles")
    Result findRoles();
}
