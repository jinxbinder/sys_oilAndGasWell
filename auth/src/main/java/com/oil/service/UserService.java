package com.oil.service;

import com.oil.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * ClassName: UserService <br/>
 * Description: <br/>
 * date: 2020/4/11 12:40<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@FeignClient(name = "user")
public interface UserService extends UserApi{
}
