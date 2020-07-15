package com.oil.service;

import com.oil.api.TestApi;
import com.oil.manage.TestManage;
import com.oil.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: TestService <br/>
 * Description: <br/>
 * date: 2020/6/24 11:05<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@Slf4j
@RestController
public class TestService implements TestApi {
    @Autowired
    private TestManage testManage;
    @Override
    public Result getNewsList() {
        return Result.success(testManage.getNewsList());
    }
}
