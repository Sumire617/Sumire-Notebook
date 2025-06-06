package org.sumire.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GoodsController {
    // 允许所有人访问(USER ADMIN)
    @GetMapping("/select")
    public String getGoods() {
        return "Goods";
    }
    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public String home() {
        return "Home page";
    }
    // 用ModelAndView返回一个视图
    @GetMapping("/custom-login")
    public ModelAndView customLogin() {
        // custom-login.html是一个自定义的登录页面
        return new ModelAndView("custom-login");
    }
    // 只允许ADMIN访问
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminOnly() {
        return "Admin area";
    }
}
