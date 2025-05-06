package com.demo.mybatis.model;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@Builder
public class User implements Serializable {
    private int id;
    private String name;
    private int age;
    private String email;

}
