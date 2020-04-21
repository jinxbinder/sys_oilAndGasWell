package com.oil;

import com.oil.utils.DateUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

/**
 * ClassName: TimeTest <br/>
 * Description: <br/>
 * date: 2020/4/21 12:59<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@SpringBootTest
public class TimeTest {
    @Test
    public void time(){
        Timestamp startTime = new Timestamp(DateUtil.stringToDate("2019-12-12",DateUtil.DATE_TO_STRING_SHORT_PATTERN).getTime());
        System.out.println(startTime);

    }
}
