package com.demo.commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// 没有@Order注解,会按照类名的字母顺序执行
@Component
class Runner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Runner is running");
    }
}
// @Order(1) 会按照数字顺序执行,数字越小,优先级越高
// 数字相同,会按照类名的字母顺序执行
@Component
@Order(1)
class Runner2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Runner2 is running");
    }
}

@Component
@Order(2)
class Runner3 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Runner3 is running");
    }
}