package com.hacknet.wheelsy.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "product")
public class Product extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @NotNull
    @ManyToOne(fetch=FetchType.LAZY,optional = false)
    @JoinColumn(name="entrepreneurs_id",nullable = false)
    @JsonIgnore
    private Entrepreneur entrepreneur;



    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy="products")
    private List<Sales> sales;

    @NotNull
    @Size(max = 25)
    private String category;

    @NotNull
    private int sku;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public Entrepreneur getEntrepreneur() {
        return entrepreneur;
    }

    public Product setEntrepreneur(Entrepreneur entrepreneur) {
        this.entrepreneur = entrepreneur;
        return this;
    }

    public List<Sales> getSales() {
        return sales;
    }

    public Product setSales(List<Sales> sales) {
        this.sales = sales;
        return this;
    }
}
