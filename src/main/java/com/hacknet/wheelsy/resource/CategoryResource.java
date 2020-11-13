package com.hacknet.wheelsy.resource;

import com.hacknet.wheelsy.domain.model.AuditModel;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryResource extends AuditModel {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public CategoryResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryResource setName(String name) {
        this.name = name;
        return this;
    }
}
