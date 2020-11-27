package com.hacknet.wheelsy.domain.service;


import com.hacknet.wheelsy.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
    Page<User> getAllUsers(Pageable pageable);
    User getUserById(Long userId);
    User createUser(User user);
    User updateUser(Long userId, User userDetails);
    ResponseEntity<?> deleteUser(Long userId);
    User assignSubscription(Long userId, Long subscriptionPlanId);
    User unassignSubscription(Long userId, Long subscriptionPlanId);
}
