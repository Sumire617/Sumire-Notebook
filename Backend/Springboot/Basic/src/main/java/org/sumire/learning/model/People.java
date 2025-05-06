package org.sumire.learning.model;

import jakarta.persistence.*;
import lombok.Data;

// 用于指定该类是一个实体类
@Entity
// 用于生成getter和setter方法
@Data
// 指定表名
@Table(name = "people")
public class People {
    // 指定主键
    @Id
    // 指定列名和是否允许为空
    @Column(name = "id", nullable = false)
    // 指定主键生成策略,这里使用自增策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
