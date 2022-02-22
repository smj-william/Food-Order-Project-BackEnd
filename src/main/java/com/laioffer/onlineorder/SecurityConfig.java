package com.laioffer.onlineorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource; //我们在ApplicationConfig 定义的DataSource

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT email, password, enabled FROM customers WHERE email=?")//在customer这张表里面存在的东西
                .authoritiesByUsernameQuery("SELECT email, authorities FROM authorities WHERE email=?"); // ? is SQL injection

    }

    @Override
    protected  void configure(final HttpSecurity http) throws Exception{
        //攻击手段 "跨域请求伪造" Cross-Site Request Forgery
        http.csrf().disable()
                .formLogin()
                .failureForwardUrl("/login?error=true"); //登陆失败的处理，去singInController定义

        http.authorizeRequests()
                .antMatchers("/order/*", "/cart", "/checkout").hasAuthority("ROLE_USER")
                .anyRequest().permitAll();
    }

    @SuppressWarnings("deprecation")
    @Bean
    // Spring Security 5 之后就自动加密了，这个方法就是取消这个加密
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

}
