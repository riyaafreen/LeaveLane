package com.example.leavemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.leavemanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
 User findByEmail(String email);
}