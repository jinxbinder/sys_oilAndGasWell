package com.oil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName: SkApplication <br/>
 * Description: <br/>
 * date: 2020/4/25 13:24<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SkApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkApplication.class,args);
    }
}
