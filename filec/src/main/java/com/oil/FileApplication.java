package com.oil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName: FileService <br/>
 * Description: <br/>
 * date: 2020/5/12 14:36<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@EnableDiscoveryClient
@SpringBootApplication
public class FileApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class,args);
    }
}
