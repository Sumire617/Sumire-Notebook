package org.sumire.security.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Entity
@Data
@Table(name = "user")
public class User implements UserDetails {
    @Id
    // 主键自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 唯一
    @Column(unique = true)
    private String username;

    private String password;

    private String roles;

    // 实现UserDetails接口方法
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 将roles字符串转换为GrantedAuthority列表
        return Arrays.stream(roles.split(","))
                // 加上前缀"ROLE_", 这是Spring Security的默认角色前缀
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .toList();
    }

    // 其他必须实现的UserDetails方法（isEnabled等返回true）
}