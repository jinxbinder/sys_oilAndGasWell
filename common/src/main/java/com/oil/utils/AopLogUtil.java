package com.oil.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * ClassName: AopLogUtil <br/>
 * Description: <br/>
 * date: 2020/3/26 13:08<br/>
 *
 * @author libd<br   />
 * @version 1.0
 * @since JDK 1.8
 */

@Aspect
@Slf4j
@Component
public class AopLogUtil {
    private JSONObject jsonObject = new JSONObject();

    // 申明一个切点 里面是 execution表达式
    @Pointcut("execution(* com.oil.service.*.*(..))")
    private void serviceAspect() {
    }
    @Pointcut("execution(* com.oil.controller.*.*(..))")
    private void controllerAspect() {
    }

    // 请求method前打印内容
    @Before(value = "controllerAspect()||serviceAspect()")
    public void methodBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("=============request start===============");
        try {
            // 打印请求内容
            log.info("请求地址:" + request.getRequestURL().toString());
            log.info("请求方式:" + request.getMethod());
            log.info("请求类方法:" + joinPoint.getSignature());
            log.info("请求类方法参数:" + Arrays.toString(joinPoint.getArgs()));
        } catch (Exception e) {
            log.error("###AopLogUtil.class methodBefore() ### ERROR:", e);
        }
        log.info("===============request end===============");
    }

    // 在方法执行完结后打印返回内容
    @AfterReturning(returning = "o", pointcut = "controllerAspect()||serviceAspect()")
    public void methodAfterReturing(Object o) {
        log.info("-------------response start---------------");
        try {
            log.info("Response内容:" + jsonObject.toJSONString(o));
        } catch (Exception e) {
            log.error("###AopLogUtil.class methodAfterReturing() ### ERROR:", e);
        }
        log.info("--------------response end----------------");
    }
}

