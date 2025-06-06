package org.sumire.mail.controller;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.sumire.mail.Service.EmailService;
import org.sumire.mail.model.CodeStorage;
import org.sumire.mail.utils.CodeUtil;
@RestController
public class AuthController {

    @Autowired
    private EmailService emailService;

    // 发送验证码
    @PostMapping("/send-code")
    public ResponseEntity<String> sendCode(@RequestParam String email) {
        String code = CodeUtil.generateCode();
        try {
            CodeStorage.saveCode(email, code);
            emailService.sendVerificationCode(email, code);
            return ResponseEntity.ok("验证码已发送");
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("发送失败");
        }
    }

    // 验证登录
    @PostMapping("/login-with-code")
    public ResponseEntity<String> loginWithCode(@RequestParam String email, @RequestParam String code) {
        if (CodeStorage.verifyCode(email, code)) {
            return ResponseEntity.ok("登录成功");
        } else {
            return ResponseEntity.status(401).body("验证码错误或已过期");
        }
    }
}
