package com.hacknet.wheelsy.domain.repository;

import com.hacknet.wheelsy.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
