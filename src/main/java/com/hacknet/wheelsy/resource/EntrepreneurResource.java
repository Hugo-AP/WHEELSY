package com.hacknet.wheelsy.resource;

import com.hacknet.wheelsy.domain.model.AuditModel;
public class EntrepreneurResource extends AuditModel {
    private Long id;
    private String ruc;
    private String name;
    private String phone;
    private String email;

    public Long getId() {
        return id;
    }
    public EntrepreneurResource setId(Long id) {
        this.id = id;
        return this;
    }
    public String getRuc() {
        return ruc;
    }
    public EntrepreneurResource setRuc(String ruc) {
        this.ruc = ruc;
        return this;
    }
    public String getName() {
        return name;
    }

    public EntrepreneurResource setName(String name) {
        this.name = name;
        return this;
    }
    public String getEmail() {
        return email;
    }

    public EntrepreneurResource setEmail(String email) {
        this.email = email;
        return this;
    }
    public String getPhone() {
        return phone;
    }

    public EntrepreneurResource setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}






