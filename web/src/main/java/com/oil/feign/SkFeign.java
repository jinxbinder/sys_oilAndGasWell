package com.oil.feign;

import com.oil.api.SkApi;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName: SkFeign <br/>
 * Description: <br/>
 * date: 2020/4/25 13:34<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@FeignClient(value = "sk",contextId = "1")
public interface SkFeign extends SkApi{

}
