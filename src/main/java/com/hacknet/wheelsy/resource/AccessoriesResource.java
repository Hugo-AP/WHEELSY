package com.hacknet.wheelsy.resource;

import com.hacknet.wheelsy.domain.model.AuditModel;
public class AccessoriesResource extends AuditModel {
    private Long id;

    private String name;
    private String description;


    public Long getId() {
        return id;
    }
    public AccessoriesResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AccessoriesResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AccessoriesResource setDescription(String description) {
        this.description = description;
        return this;
    }
}