package com.hacknet.wheelsy.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "entrepreneurs")
public class Entrepreneur extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String ruc;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Size(max = 100)
    private String email;

    @NotNull
    @Size(max = 20)
    private String phone;


    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy="entrepreneurs")
    private List<User> users;

    public Long getId() {
        return id;
    }

    public Entrepreneur setId(Long id) {
        this.id = id;
        return this;
    }
    public String getRuc() {
        return ruc;
    }

    public Entrepreneur setRuc(String ruc) {
        this.ruc =ruc;
        return this;
    }

    public String getName() {
        return name;
    }

    public Entrepreneur setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Entrepreneur setEmail(String email) {
        this.email = email;
        return this;
    }
    public String getPhone() {
        return phone;
    }

    public Entrepreneur setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public List<User> getUsers() {
        return users;
    }

    public Entrepreneur setUsers(List<User> users) {
        this.users = users;
        return this;
    }

}