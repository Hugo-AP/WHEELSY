package com.hacknet.wheelsy.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveCategoryResource {
    @NotNull
    @Size(max = 30)
    private String name;

    public String getName() {
        return name;
    }

    public SaveCategoryResource setName(String name) {
        this.name = name;
        return this;
    }
}
