package com.oil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * ClassName: UserApplication <br/>
 * Description: <br/>
 * date: 2020/3/15 13:14<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@SpringBootApplication
@EnableEurekaClient
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
