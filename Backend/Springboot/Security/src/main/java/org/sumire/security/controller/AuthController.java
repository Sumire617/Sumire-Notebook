package org.sumire.security.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.sumire.security.model.User;
import org.sumire.security.repository.UserRepository;

// 注册控制器
@RestController
public class AuthController {
    // 注入UserRepository和PasswordEncoder
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    // 用ModelAndView显示注册页面register.html
    @GetMapping("/register-page")
    public ModelAndView registerPage() {
        return new ModelAndView("register");
    }
    // 用ModelAndView返回登录页面login.html
    @PostMapping("/register")
    public ModelAndView register(@RequestParam String username,
                                 @RequestParam String password,
                                 HttpServletRequest request, RedirectAttributes redirectAttributes) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // 加密密码
        user.setRoles("USER"); // 默认角色
        userRepository.save(user); // 保存用户
        // 自动登录
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
        // 手动设置认证信息到SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 重定向到登录页面
        redirectAttributes.addFlashAttribute("message", "注册成功,请登录...");
        return new ModelAndView("redirect:/custom-login");
    }
}
