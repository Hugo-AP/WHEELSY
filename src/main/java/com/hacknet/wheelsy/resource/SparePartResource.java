package com.hacknet.wheelsy.resource;

import com.hacknet.wheelsy.domain.model.AuditModel;
public class SparePartResource extends AuditModel {
    private Long id;

    private String name;
    private String description;


    public Long getId() {
        return id;
    }
    public SparePartResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SparePartResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SparePartResource setDescription(String description) {
        this.description = description;
        return this;
    }
}