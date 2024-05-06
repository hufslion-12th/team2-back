package com.likelion.mini2team.repository;

import com.likelion.mini2team.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
