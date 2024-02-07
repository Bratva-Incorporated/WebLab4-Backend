package com.porunit.l4.repositories;

import com.porunit.l4.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByUsername(String username);

    boolean existsByUsername(String username);
}
