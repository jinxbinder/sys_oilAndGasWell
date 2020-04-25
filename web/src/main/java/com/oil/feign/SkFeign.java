package com.oil.feign;

import com.oil.api.SkApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * ClassName: SkFeign <br/>
 * Description: <br/>
 * date: 2020/4/25 13:34<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@FeignClient("sk")
public interface SkFeign extends SkApi{
}
