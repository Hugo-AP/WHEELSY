package com.hacknet.wheelsy.resource;

import com.hacknet.wheelsy.domain.model.AuditModel;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class SalesResource extends AuditModel{
    //users_id
    private Long id;
    private Date date_register;
    private String time;
    private String way_to_pay;

    public Long getId() {
        return id;
    }

    public SalesResource setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getDate_register() {
        return date_register;
    }

    public SalesResource setDate_register(Date date_register) {
        this.date_register = date_register;
        return this;
    }

    public String getTime() {
        return time;
    }

    public SalesResource setTime(String time) {
        this.time = time;
        return this;
    }

    public String getWay_to_pay() {
        return way_to_pay;
    }

    public SalesResource setWay_to_pay(String way_to_pay) {
        this.way_to_pay = way_to_pay;
        return this;
    }
}
