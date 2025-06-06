package org.sumire.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.sumire.security.repository.UserRepository;

// 配置类，用于定义 Spring Security 的安全配置
@Configuration
// 启用 Spring Security 的 Web 安全支持，自动加载默认配置
@EnableWebSecurity
// 启用方法级安全控制，允许在方法上使用 @PreAuthorize 等注解
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    // 密码加密器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // 安全配置过滤器链
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 新增会话管理配置
                .sessionManagement(session -> session
                        // 总是创建会话
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                        // 会话固定保护
                        .sessionFixation().migrateSession()
                )
                // 请求授权规则
                .authorizeHttpRequests((authorize) -> authorize
                        // 允许匿名访问
                        .requestMatchers("/login", "/custom-login", "/logout", "/register", "/register-page","/select").permitAll()
                        // 静态资源放行
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        // .authenticated()只要有身份就能访问
                        .requestMatchers("/home").authenticated()
                        // .hasRole需要 ADMIN 角色才能访问
                        .requestMatchers("/admin").hasRole("ADMIN")
                        // 其他请求需要认证
                        .anyRequest().authenticated()
                )
                // 表单登录配置
                .formLogin(form -> form
                        // 如果使用默认的登录页面,就不要设置loginPage
                        .loginPage("/custom-login")
                        .successHandler((request, response, authentication) -> {
                            if (request.getParameter("register") != null) {
                                response.sendRedirect("/home");
                            } else {
                                response.sendRedirect("/home");
                            }
                        })
                        // 明确指定登录处理路径
                        .loginProcessingUrl("/login")
                        // 登录成功后跳转
                        .defaultSuccessUrl("/home", true)

                )
                .logout(logout -> logout
                        // 指定登出路径
                        .logoutUrl("/logout")
                        // 注销后跳转
                        .logoutSuccessUrl("/login?logout")
                        // 使session失效
                        .invalidateHttpSession(true)
                        // 删除会话cookie
                        .deleteCookies("JSESSIONID")
                        // 清除认证信息
                        .clearAuthentication(true)
                        // 允许所有人访问登出
                        .permitAll()
        )
                // CSRF禁用
                .csrf(csrf -> csrf.disable())
                // 自定义过滤器（日志记录）
                .addFilterBefore((request, response, chain) -> {
                    System.out.println("收到请求: " + request.getRequestId());
                    chain.doFilter(request, response);
                }, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    /*
    内存用户服务
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}password") // 暂时禁用加密
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin123")
                .roles("ADMIN", "USER")  // 同时拥有两种角色
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
     */
    // 替换为数据库用户服务
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("用户不存在"));
    }
}
