package com.bms.globalcustomvalidations.repository;

import com.bms.globalcustomvalidations.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}