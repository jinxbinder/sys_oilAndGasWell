package com.oil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * ClassName: GateWayService <br/>
 * Description: <br/>
 * date: 2020/4/10 15:40<br/>
 *
 * @author libd<br />
 * @version 1.0
 * @since JDK 1.8
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class GateWayService {
    public static void main(String[] args) {
        SpringApplication.run(GateWayService.class,args);
    }
}
