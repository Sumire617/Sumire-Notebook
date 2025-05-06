package com.demo.commandlinerunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommandLineRunnerApplication {

    public static void main(String[] args) {
        // 如果SpringApplication.run()方法启动了一个新的线程来执行应用程序,就会打印两次Application started
        // 可以看到这两句Application started来自不同线程main和restartMain
        System.out.println("Application started - Thread: " + Thread.currentThread().getName());
        SpringApplication.run(CommandLineRunnerApplication.class, args);
        System.out.println("Application ended - Thread: " + Thread.currentThread().getName());
    }

}
