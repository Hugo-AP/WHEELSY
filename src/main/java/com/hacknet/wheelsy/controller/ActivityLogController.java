package com.hacknet.wheelsy.controller;


import com.hacknet.wheelsy.domain.model.Entrepreneur;
import com.hacknet.wheelsy.domain.model.User;
import com.hacknet.wheelsy.domain.service.EntrepreneurService;
import com.hacknet.wheelsy.domain.service.UserService;
import com.hacknet.wheelsy.resource.EntrepreneurResource;
import com.hacknet.wheelsy.resource.SaveEntrepreneurResource;
import com.hacknet.wheelsy.resource.SaveUserResource;
import com.hacknet.wheelsy.resource.UserResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ActivityLogController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserService userService;


    @PostMapping("/users/{userId}/entrepreneurs/{entrepreneurId}")
    public UserResource assignActivity(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "entrepreneurId") Long entrepreneurId) {
        return convertToResource(userService.assignActivity(userId, entrepreneurId));
    }

    @DeleteMapping("/users/{userId}/entrepreneurs/{entrepreneurId}")
    public UserResource unAssignActivity(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "entrepreneurId") Long entrepreneurId) {
        return convertToResource(userService.unassignActivity(userId, entrepreneurId));
    }
    @GetMapping("entrepreneurs/{entrepreneurId}/users")
    public Page<UserResource> getAllUsersByEntrepreneurId(
            @PathVariable(name = "entrepreneurId") Long entrepreneurId,
            Pageable pageable) {
        Page<User> userPage = userService.getAllUsersByEntrepreneurId(entrepreneurId,pageable);
        List<UserResource> resources = userPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }


}
