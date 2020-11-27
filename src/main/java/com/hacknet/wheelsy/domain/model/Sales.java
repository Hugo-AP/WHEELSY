package com.hacknet.wheelsy.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

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
    @Size(max = 10)
    private Date date_register;

    @NotNull
    @Size(max = 10)
    private Date time;

    @NotNull
    @Size(max=100)
    private String way_to_pay;


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

    public Date getTime() {
        return time;
    }

    public Sales setTime(Date time) {
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
}
