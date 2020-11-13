package com.hacknet.wheelsy.resource;

import com.hacknet.wheelsy.domain.model.AuditModel;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SubscriptionPlanResource extends AuditModel {

    private Long id;
    private String type;
    private String description;
    private int cost;
    private String name;

    public Long getId() {
        return id;
    }

    public SubscriptionPlanResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public SubscriptionPlanResource setType(String type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SubscriptionPlanResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getCost() {
        return cost;
    }

    public SubscriptionPlanResource setCost(int cost) {
        this.cost = cost;
        return this;
    }

    public String getName() {
        return name;
    }

    public SubscriptionPlanResource setName(String name) {
        this.name = name;
        return this;
    }
}
