package com.hacknet.wheelsy.controller;


import com.hacknet.wheelsy.domain.model.User;

import com.hacknet.wheelsy.domain.service.UserService;

import com.hacknet.wheelsy.resource.SaveUserResource;

import com.hacknet.wheelsy.resource.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Assign Subscribe", description = "Assign a new Subscribe", tags = {"Subscriptions"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription created", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/users/{userId}/plans/{subscriptionPlanId}")
    public UserResource assignSubscribe(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "subscriptionPlanId") Long subscriptionPlanId) {
        return convertToResource(userService.assignSubscription(userId, subscriptionPlanId));
    }
    @Operation(summary = "Unassign Subscribe", description = "Unassign a new Subscribe", tags = {"Subscriptions"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription deleted", content = @Content(mediaType = "application/json"))
    })
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
