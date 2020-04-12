package com.oil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName: AuthCenter <br/>
 * Description: <br/>
 * date: 2020/4/10 15:44<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class AuthCenter {
    public static void main(String[] args) {
        SpringApplication.run(AuthCenter.class,args);
    }
}
