package com.hacknet.wheelsy.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sales")
public class Sales extends AuditModel{
    //users_id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date date_register;


    @NotNull
    private String time;

    @NotNull
    @Size(max=100)
    private String way_to_pay;



    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "sales_details",
            joinColumns = { @JoinColumn(name = "sale_id")},
            inverseJoinColumns = { @JoinColumn(name = "product_id")})
    private List<Product> products;

    public User getUser() {
        return user;
    }

    public Sales setUser(User user) {
        this.user = user;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Sales setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getDate_register() {
        return date_register;
    }

    public Sales setDate_register(Date date_register) {
        this.date_register = date_register;
        return this;
    }

    public String getTime() {
        return time;
    }

    public Sales setTime(String time) {
        this.time = time;
        return this;
    }


    public String getWay_to_pay() {
        return way_to_pay;
    }

    public Sales setWay_to_pay(String way_to_pay) {
        this.way_to_pay = way_to_pay;
        return this;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Sales setProducts(List<Product> products) {
        this.products = products;
        return this;
    }

    public boolean isSaleWith(Product product) {
        return this.getProducts().contains(product);
    }

    public Sales OnSaleWith(Product product) {
        if(!this.isSaleWith(product))
            this.getProducts().add(product);
        return this;
    }

}
