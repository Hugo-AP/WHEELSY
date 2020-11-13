package com.hacknet.wheelsy.service;

import com.hacknet.wheelsy.domain.model.SubscriptionPlan;
import com.hacknet.wheelsy.domain.model.User;
import com.hacknet.wheelsy.domain.repository.SubscriptionPlanRepository;
import com.hacknet.wheelsy.domain.repository.UserRepository;
import com.hacknet.wheelsy.domain.service.SubscriptionPlanService;
import com.hacknet.wheelsy.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;

    @Override
    public Page<SubscriptionPlan> getAllSubscriptionPlanServices(Pageable pageable) {
        return subscriptionPlanRepository.findAll(pageable);
    }

    @Override
    public SubscriptionPlan getSubscriptionPlanById(Long SubscriptionPlanId) {
        return subscriptionPlanRepository.findById(SubscriptionPlanId).orElseThrow(()->new ResourceNotFoundException("SubscriptionPlan","Id", SubscriptionPlanId));
    }

    @Override
    public SubscriptionPlan createSubscriptionPlan(SubscriptionPlan SubscriptionPlan) {
        return subscriptionPlanRepository.save(SubscriptionPlan);
    }

    @Override
    public SubscriptionPlan updateSubscriptionPlan(Long SubscriptionPlanId, SubscriptionPlan SubscriptionPlanDetails) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(SubscriptionPlanId)
                .orElseThrow(() -> new ResourceNotFoundException("subscription_plan", "Id", SubscriptionPlanId));
        subscriptionPlan.setName(SubscriptionPlanDetails.getName());
        subscriptionPlan.setCost(SubscriptionPlanDetails.getCost());
        subscriptionPlan.setType(SubscriptionPlanDetails.getType());
        subscriptionPlan.setDescription(SubscriptionPlanDetails.getDescription());

        return subscriptionPlanRepository.save(subscriptionPlan);
    }

    @Override
    public ResponseEntity<?> deleteSubscriptionPlan(Long SubscriptionPlanId) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(SubscriptionPlanId).
                orElseThrow(()-> new ResourceNotFoundException("Specialist", "Id", SubscriptionPlanId));
        subscriptionPlanRepository.delete(subscriptionPlan);
        return ResponseEntity.ok().build();
    }
}
