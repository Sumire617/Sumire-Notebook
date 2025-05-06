package org.sumire.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sumire.learning.model.People;
import org.sumire.learning.service.PeopleService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    private PeopleService peopleService;

    // 获取所有people
    @GetMapping
    public ResponseEntity<List<People>> getPeople() {
        // 返回一个包含所有people的响应实体
        // 响应实体是Spring MVC提供的一个类,用于封装响应信息
        return ResponseEntity.ok(peopleService.getAllPeople());
    }
    // 根据id获取people
    @GetMapping("/{id}")
    public ResponseEntity<People> getPeopleById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(peopleService.getPeopleById(id));
    }
    // 注册
    @PostMapping
    public ResponseEntity<People> createPeople(@RequestBody People people) {
        return ResponseEntity.ok(peopleService.savePeople(people));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePeople(@PathVariable("id") Long id) {
        peopleService.deletePeople(id);
        // 返回删除成功消息和删除用户的ID
        return ResponseEntity.ok(Map.of(
                "deleteId", id,
                "message", "删除成功",
                "status", 200
        ));
    }
}