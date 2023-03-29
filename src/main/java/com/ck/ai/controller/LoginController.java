package com.ck.ai.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    // 登录
//     @RequestMapping("/login")
//     public String login(){
//     	return "redirect:/login.html";
//     }

    // 成功后跳转页面
    //方法级别控制
//    @Secured("ROLE_abc")
    //参数可以是任何 access()支持的表达式
    @PreAuthorize("hasRole('ROLE_abc')")
    @RequestMapping(value = "/toMain",method = RequestMethod.POST)
    public String toMain(){
        System.out.println("到这里了吗？");
        return "redirect:main.html";
    }

    /**
     * 失败后跳转页面
     */
    @RequestMapping(value = "/toError",method = RequestMethod.POST)
    public String toError(){
        return "redirect:error.html";
    }
}
