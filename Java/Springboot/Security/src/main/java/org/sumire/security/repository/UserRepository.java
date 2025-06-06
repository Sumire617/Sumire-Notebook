package org.sumire.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sumire.security.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}