package com.oil;

import com.oil.utils.DateUtil;
import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName: DateUtilTest <br/>
 * Description: <br/>
 * date: 2020/4/17 12:15<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@SpringBootTest
public class DateUtilTest {
    @Test
    public void dut(){
        System.out.println(DateUtil.getTimestamp());
    }
}
