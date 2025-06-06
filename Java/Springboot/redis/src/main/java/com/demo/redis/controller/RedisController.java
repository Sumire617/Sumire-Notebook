package com.demo.redis.controller;


import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;

    // 设置值
    // @RequestParam注解用于将请求参数绑定到方法参数上
    @PostMapping("/set")
    public ResponseEntity<?> setValue(@RequestParam String key, @RequestParam String value) {
        if (redisTemplate.hasKey(key)) {
            return ResponseEntity.badRequest().body("Key already exists");
        }
        // opsForValue()方法返回一个ValueOperations对象,用于操作字符串类型的值
        // set()方法用于设置键值对,并设置过期时间为60秒
        redisTemplate.opsForValue().set(key, value, 60, TimeUnit.SECONDS);
        /*
        * ResponseEntity.ok().build():仅返回200状态码
        * ResponseEntity.ok("success"):返回200状态码和"success"字符串
        * ResponseEntity.ok().status(HTTPStatus.CREATED).build():返回201状态码
        * ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found"):返回404状态码和响应体
        *
        * HttpHeaders headers = new HttpHeaders();
        * headers.add("Custom-Header", "value");
        * ResponseEntity.oki.headers(headers).body("success"):返回带有响应头的响应
        * */
        return ResponseEntity.ok("Key: " + key + " set successfully");
    }
    // 获取值
    @GetMapping("/get")
    public ResponseEntity<Map<String, Object>> getValue(@RequestParam String key) {
        // 查看键是否存在, 如果不存在, 返回404状态码和错误信息
        if (!redisTemplate.hasKey(key)) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Key not found");
            return ResponseEntity.status(404).body(response);
        }
        // 设置一个value接收获取的键值
        Object value = redisTemplate.opsForValue().get(key);
        Map<String, Object> response = new HashMap<>();
        // 设置一个response接收返回的状态码和数据
        response.put("status", "success");
        response.put("data", value);
        return ResponseEntity.ok(response);
    }
    // 删除值
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam String key) {
        Map<String, Object> response = new HashMap<>();
        // 查看键是否存在
        if (!redisTemplate.hasKey(key)) {
            response.put("status", "error");
            response.put("message", "Key not found");
            return ResponseEntity.status(404).body(response);
        }
        response.put("status", "success");
        response.put("data", redisTemplate.opsForValue().get(key));
        return ResponseEntity.ok(response);
    }
    // 检查键是否存在
    @GetMapping("/exists")
    public Boolean keyExists(@RequestParam String key) {
        return redisTemplate.hasKey(key);
    }
}
