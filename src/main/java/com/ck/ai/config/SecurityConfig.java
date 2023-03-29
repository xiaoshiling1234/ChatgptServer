package com.ck.ai.config;

import com.ck.ai.service.SpringSecurityUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    PersistentTokenRepository persistentTokenRepository;

    @Autowired
    SpringSecurityUserServiceImpl userService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表单提交
        http.formLogin()
                // 自定义登录页面
                .loginPage("/login.html")
                // 当发现/login时认为是登录，必须和表单提交的地址一样。去执行UserServiceImpl
                .loginProcessingUrl("/login")
                // 登录成功后跳转页面，POST请求
//                .successForwardUrl("/toMain")
                .successHandler(new MyAuthenticationSuccessHandler("http://www.baidu.com"))
                // 登录失败后跳转页面，POST请求
//                .failureForwardUrl("/toError")
                .failureHandler(new MyForwardAuthenticationFailureHandler("/toError"))
                .usernameParameter("myusername")
                .passwordParameter("mypassword");

        http.authorizeRequests()
                // login.html不需要被认证
                .antMatchers("/login.html").permitAll()
                // error.html不需要被认证
                .antMatchers("/error.html").permitAll()
//                .antMatchers("/main.html").hasAuthority("admin")
//                .antMatchers("/main.html").hasAuthority("abc")
//        如果请求是指定的 IP 就运行访问。可以通过 request.getRemoteAddr() 获取 ip 地址。需要注意的是在本机进行测试时 localhost 和 127.0.0.1 输出的 ip地址是不一样的。
//                .antMatchers("/main.html").hasIpAddress("127.0.0.1")
                .antMatchers("/main.html").access("hasRole(\"abd\")")
//                .anyRequest().access("@myServiceImpl.hasPermission(request,authentication)")
                // 所有请求都必须被认证，必须登录后被访问
                .anyRequest().authenticated()
        ;
        // 关闭csrf防护
        http.csrf().disable();
        //设置访问受限
        http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);
        //Spring Security 中 Remember Me 为“记住我”功能，用户只需要在登录时添加 remember-me复选框，取值为true。Spring Security 会自动把用户信息存储到数据源中，以后就可以不登录进行访问
        http.rememberMe()
                //失效时间，单位秒
                .tokenValiditySeconds(120)
                //登录逻辑交给哪个对象
                .userDetailsService(userService)
                // 持久层对象
                .tokenRepository(persistentTokenRepository);


    }

}

