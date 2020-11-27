package com.hacknet.wheelsy.service;

import com.hacknet.wheelsy.domain.model.SubscriptionPlan;
import com.hacknet.wheelsy.domain.model.User;
import com.hacknet.wheelsy.domain.repository.SubscriptionPlanRepository;
import com.hacknet.wheelsy.domain.repository.UserRepository;
import com.hacknet.wheelsy.domain.service.UserService;
import com.hacknet.wheelsy.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id", userId));
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User userDetails) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        user.setName(userDetails.getName());
        user.setLastname(userDetails.getLastname());
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setEmail(userDetails.getEmail());
        user.setAddress(userDetails.getAddress());

        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        User user = userRepository.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("Specialist", "Id", userId));
        userRepository.delete(user);
        return ResponseEntity.ok().build();

    }

    @Override
    public User assignSubscription(Long userId, Long subscriptionPlanId) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(subscriptionPlanId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "SubscriptionPlan", "Id", subscriptionPlanId));
        return userRepository.findById(userId).map(
                user -> userRepository.save(user.SubscribeWith(subscriptionPlan)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User", "Id", userId));
    }

    @Override
    public User unassignSubscription(Long userId, Long subscriptionPlanId) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(subscriptionPlanId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "SubscriptionPlan", "Id", subscriptionPlanId));
        return userRepository.findById(userId).map(
                user -> userRepository.save(user.UnsubscribeWith(subscriptionPlan)))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User", "Id", userId));
    }


}
