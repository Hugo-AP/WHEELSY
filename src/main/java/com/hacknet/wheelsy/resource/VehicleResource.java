package com.hacknet.wheelsy.resource;

import com.hacknet.wheelsy.domain.model.AuditModel;
public class VehicleResource extends AuditModel {
    private Long id;

    private String name;
    private String description;


    public Long getId() {
        return id;
    }
    public VehicleResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public VehicleResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public VehicleResource setDescription(String description) {
        this.description = description;
        return this;
    }
}