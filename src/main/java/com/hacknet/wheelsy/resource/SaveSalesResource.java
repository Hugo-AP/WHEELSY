package com.hacknet.wheelsy.resource;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class SaveSalesResource {
    //users_id
    @NotNull
    private Date date_register;

    @NotNull

    private String time;

    @NotNull
    @Size(max=100)
    private String way_to_pay;

    public Date getDate_register() {
        return date_register;
    }

    public SaveSalesResource setDate_register(Date date_register) {
        this.date_register = date_register;
        return this;
    }

    public String getTime() {
        return time;
    }

    public SaveSalesResource setTime(String time) {
        this.time = time;
        return this;
    }

    public String getWay_to_pay() {
        return way_to_pay;
    }

    public SaveSalesResource setWay_to_pay(String way_to_pay) {
        this.way_to_pay = way_to_pay;
        return this;
    }
}
