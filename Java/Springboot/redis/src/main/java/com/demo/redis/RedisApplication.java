package com.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class RedisApplication {


    public static void main(String[] args) {
        /*
        * @Autowired
        * private RedisTemplate<String, Object> redisTemplate;
        * ConfigurableApplicationContext是Spirng框架的一个接口,代表spring应用程序的上下文
        * 是IoC容器的核心接口,负责管理Bean声明周期和配置
        * 通过SpringApplication.run方法返回,可以用来获取应用程序中定义的bean
        * 在这里用来获取RedisApplication类的实例,以便访问其中的redisTemplate

        * ConfigurableApplicationContext context = SpringApplication.run(RedisApplication.class, args);
        * RedisApplication app = context.getBean(RedisApplication.class);
        * app.redisTemplate.opsForValue().set("testkey", "Hello Redis");
        * System.out.println("Value from Redis: " + app.redisTemplate.opsForValue().get("testkey"));
        */
        SpringApplication.run(RedisApplication.class, args);

    }

}
