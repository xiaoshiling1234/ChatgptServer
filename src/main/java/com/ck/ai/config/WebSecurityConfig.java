package com.ck.ai.config;

import com.ck.ai.service.SpringSecurityUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 将自定义的拒绝访问处理器注入进来
     */
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    /**
     * 将自定义的登录成功处理器注入进来
     */
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    /**
     * 将自定义的登录失败处理器注入进来
     */
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    PersistentTokenRepository persistentTokenRepository;

    @Autowired
    SpringSecurityUserServiceImpl userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //设置访问受限
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        // 登录成功或者失败走自己的处理器
        http.formLogin().successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler);
        // 关闭csrf防护
        http.csrf().disable();

        // 匹配哪些 url，需要哪些权限才可以访问 当然我们也可以使用链式编程的方式
        http.authorizeRequests()
                .antMatchers("/query").hasAnyAuthority("sys:query")
                .antMatchers("/save").hasAnyAuthority("sys:save")
                .antMatchers("/del").hasAnyAuthority("sys:del")
                .antMatchers("/update").hasAnyAuthority("sys:update")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated(); // 其他所有的请求都需要登录才能进行
    }

}

