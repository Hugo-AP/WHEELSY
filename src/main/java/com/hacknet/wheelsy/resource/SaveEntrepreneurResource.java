package com.hacknet.wheelsy.resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveEntrepreneurResource {


    @NotNull
    @NotBlank
    @Size(max = 100)
    private String ruc;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String phone;

    public String getName() {
        return name;
    }

    public SaveEntrepreneurResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public SaveEntrepreneurResource setPhone(String phone) {
        this.phone = phone;
        return this;
    }


    public String getEmail() {
        return email;
    }

    public SaveEntrepreneurResource setEmail(String email) {
        this.email = email;
        return this;

    }
    public String getRuc() {
        return ruc;
    }

    public SaveEntrepreneurResource setRuc(String ruc) {
        this.ruc = ruc;
        return this;
    }

}
