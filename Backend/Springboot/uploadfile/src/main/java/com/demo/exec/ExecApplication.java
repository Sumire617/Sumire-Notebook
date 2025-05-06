package com.demo.exec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// 启动定时任务
@EnableScheduling
@SpringBootApplication
public class ExecApplication {

    public static void main(String[] args) {
        // 为什么打印两次Application started,可以看到来自不同线程main和restartMain
        // 因为SpringApplication.run()方法会启动一个新的线程来执行应用程序
        // 所以会打印两次Application started
        System.out.println("Application started - Thread: " + Thread.currentThread().getName());
        SpringApplication.run(ExecApplication.class, args);
        System.out.println("Application ended - Thread: " + Thread.currentThread().getName());
    }

}
