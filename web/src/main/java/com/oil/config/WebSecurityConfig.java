package com.oil.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        //认证重写
        log.info("开始认证--------------------");
        // 使用自定义认证与授权
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
        //重写密码编码
//        auth.inMemoryAuthentication()
//                .withUser("admin").password(bCryptPasswordEncoder().encode("123")).roles("abc");


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        log.info("开始授权---------------------");
        //授权重写
        http.authorizeRequests().anyRequest().authenticated();

//                .antMatchers("/").permitAll()
//                .antMatchers("404").hasRole("404")
//                .antMatchers("403").hasRole("admin");
//                .antMatchers("/le3/**").hasRole(("v3"));

        //配置登录 自动生成登录页 /login
        http.formLogin();
        //开启自动配置的注销 访问 /logout 情况session
        http.logout().logoutSuccessUrl("/login");
        //开启自动配置记住我功能
        http.rememberMe();
    }
}

