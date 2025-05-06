package org.sumire.learning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sumire.learning.model.People;
import org.sumire.learning.repository.PeopleRepository;

import java.util.List;

// 用于标记该类是一个服务类
// 服务类是用于处理业务逻辑的类
@Service
public class PeopleService {
    // 用于注入PeopleRepository
    @Autowired
    private PeopleRepository peopleRepository;
    // 用于获取所有的people
    public List<People> getAllPeople() {
        return peopleRepository.findAll();
    }
    // 保存people
    public People savePeople(People people) {
        return peopleRepository.save(people);
    }
    // 根据id获取people
    public People getPeopleById(Long id) {
        return peopleRepository.findById(id).orElse(null);
    }
    // 根据id删除people
    public void deletePeople(Long id) {
        peopleRepository.deleteById(id);
    }
}
