package org.sumire.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sumire.learning.model.People;

// 用于操作people表的JPA仓库接口
// JpaRepository是Spring Data JPA提供的一个接口,用于操作数据库
// 第一个泛型参数是实体类,第二个泛型参数是主键类型
public interface PeopleRepository extends JpaRepository<People, Long> {
}
