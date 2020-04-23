package com.oil.feign;

import com.oil.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * ClassName: UserFeign <br/>
 * Description: <br/>
 * date: 2020/3/25 20:00<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@FeignClient("user")
public interface UserFeign extends UserApi {

}
