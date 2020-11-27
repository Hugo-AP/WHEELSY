package com.hacknet.wheelsy.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "accessories")
public class Accessories extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY,optional = false)
    @JoinColumn(name="product_id",nullable = false)
    @JsonIgnore
    private Product product;

    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    @Size(max = 250)
    private String description;

    public Long getId() {
        return id;
    }

    public Accessories setId(Long id) {
        this.id = id;
        return this;
    }


    public String getName() {
        return name;
    }

    public Accessories setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Accessories setDescription(String description) {
        this.description = description;
        return this;
    }

}