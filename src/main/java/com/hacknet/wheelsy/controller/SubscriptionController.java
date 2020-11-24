package com.hacknet.wheelsy.controller;


import com.hacknet.wheelsy.domain.model.User;

import com.hacknet.wheelsy.domain.service.UserService;

import com.hacknet.wheelsy.resource.SaveUserResource;

import com.hacknet.wheelsy.resource.UserResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SubscriptionController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserService userService;

    @PostMapping("/users/{userId}/plans/{subscriptionPlanId}")
    public UserResource assignSubscribe(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "subscriptionPlanId") Long subscriptionPlanId) {
        return convertToResource(userService.assignSubscription(userId, subscriptionPlanId));
    }

    @DeleteMapping("/users/{userId}/plans/{subscriptionPlanId}")
    public UserResource unAssignSubscribe(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "subscriptionPlanId") Long subscriptionPlanId) {
        return convertToResource(userService.unassignSubscription(userId, subscriptionPlanId));
    }

    private User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }
}
