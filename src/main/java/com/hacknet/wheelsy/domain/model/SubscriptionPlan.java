package com.hacknet.wheelsy.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "subscription_plan")
public class SubscriptionPlan extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 10)
    private String type;

    @NotNull
    @Size(max = 30)
    private String description;

    @NotNull
    private int cost;

    @NotNull
    @Size(max=100)
    private String name;

    public Long getId() {
        return id;
    }

    public SubscriptionPlan setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {return type;}

    public SubscriptionPlan setType(String type) {
        this.type = type;
        return this;
    }

    public String getDescription(){return description;}

    public SubscriptionPlan setDescription(String description){
        this.description=description;
        return this;
    }

    public int getCost(){return cost;}

    public SubscriptionPlan setCost(int cost){
        this.cost=cost;
        return this;
    }

    public String getName(){return name;}

    public SubscriptionPlan setName(String name){
        this.name=name;
        return this;
    }
}
