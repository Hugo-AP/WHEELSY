package com.hacknet.wheelsy.controller;

import com.hacknet.wheelsy.domain.model.SubscriptionPlan;
import com.hacknet.wheelsy.domain.service.SubscriptionPlanService;
import com.hacknet.wheelsy.resource.SaveSubscriptionPlanResource;
import com.hacknet.wheelsy.resource.SubscriptionPlanResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SubscriptionPlanController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SubscriptionPlanService subscriptionPlanService;

    @GetMapping("/plans")
    public Page<SubscriptionPlanResource> getAllSubscriptionPlans(Pageable pageable) {
        Page<SubscriptionPlan> userPage = subscriptionPlanService.getAllSubscriptionPlanServices(pageable);
        List<SubscriptionPlanResource> resources = userPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }
    @PostMapping("/plans")
    public SubscriptionPlanResource createSubscriptionPlan(@Valid @RequestBody SaveSubscriptionPlanResource resource) {
        SubscriptionPlan subscriptionPlan = convertToEntity(resource);
        return convertToResource(subscriptionPlanService.createSubscriptionPlan(subscriptionPlan));
    }
    @PutMapping("/plans/{SubscriptionPlanId}")
    public SubscriptionPlanResource updateSubscriptionPlan(@PathVariable Long SubscriptionPlanId, @RequestBody SaveSubscriptionPlanResource resource) {
        SubscriptionPlan subscriptionPlan = convertToEntity(resource);
        return convertToResource(subscriptionPlanService.updateSubscriptionPlan(SubscriptionPlanId, subscriptionPlan));
    }
    @DeleteMapping("/plans/{SubscriptionPlanId}")
    public ResponseEntity<?> deleteSpecialist(@PathVariable Long SubscriptionPlanId) {
        return subscriptionPlanService.deleteSubscriptionPlan(SubscriptionPlanId);
    }

    private SubscriptionPlan convertToEntity(SaveSubscriptionPlanResource resource) {
        return mapper.map(resource, SubscriptionPlan.class);
    }

    private SubscriptionPlanResource convertToResource(SubscriptionPlan entity) {
        return mapper.map(entity, SubscriptionPlanResource.class);
    }

}
