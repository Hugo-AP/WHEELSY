package com.hacknet.wheelsy.resource;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveSubscriptionPlanResource {

    @NotNull
    @NotBlank
    @Size(max = 10)
    private String type;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String description;

    @NotNull
    private int cost;

    @NotNull
    @NotBlank
    @Size(max=100)
    private String name;

    public String getType() {
        return type;
    }

    public SaveSubscriptionPlanResource setType(String type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveSubscriptionPlanResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getCost() {
        return cost;
    }

    public SaveSubscriptionPlanResource setCost(int cost) {
        this.cost = cost;
        return this;
    }

    public String getName() {
        return name;
    }

    public SaveSubscriptionPlanResource setName(String name) {
        this.name = name;
        return this;
    }
}
