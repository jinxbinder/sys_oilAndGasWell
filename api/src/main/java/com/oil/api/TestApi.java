package com.oil.api;

import com.oil.utils.Result;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName: TestApi <br/>
 * Description: <br/>
 * date: 2020/6/24 11:03<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@RequestMapping("/test")
public interface TestApi {
    /**
    * Description: 功能描述（获取新闻列表） <br/>
    * date: 2020/6/24 11:05<br/>
    * @author libd <br/>  
    */
    @RequestMapping("/getNewsList")
    Result getNewsList();
}
