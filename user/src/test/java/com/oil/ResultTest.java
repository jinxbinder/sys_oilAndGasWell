package com.oil;

import com.oil.utils.Result;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName: ResultTest <br/>
 * Description: <br/>
 * date: 2020/4/16 14:33<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@SpringBootTest
public class ResultTest {
    @Test
    public void rt(){
        System.out.println(Result.success().get("code"));
        String code = Result.success().get("code")+"";
        System.out.println("200".equals(Result.success().get("code").toString()));
    }
}
