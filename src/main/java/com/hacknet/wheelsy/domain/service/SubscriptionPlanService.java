package com.hacknet.wheelsy.domain.service;

import com.hacknet.wheelsy.domain.model.SubscriptionPlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SubscriptionPlanService {
    Page<SubscriptionPlan> getAllSubscriptionPlanServices(Pageable pageable);
    SubscriptionPlan getSubscriptionPlanById(Long SubscriptionPlanId);
    SubscriptionPlan createSubscriptionPlan(SubscriptionPlan SubscriptionPlan);
    SubscriptionPlan updateSubscriptionPlan(Long SubscriptionPlanId, SubscriptionPlan SubscriptionPlanDetails);
    ResponseEntity<?> deleteSubscriptionPlan(Long SubscriptionPlanId);
}
