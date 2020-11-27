package com.hacknet.wheelsy.resource;

        import javax.validation.constraints.NotBlank;
        import javax.validation.constraints.NotNull;
        import javax.validation.constraints.Size;

public class SaveProductResource {

    @NotNull
    @NotBlank
    @Size(max = 25)
    private String category;

    @NotNull
    private int sku;


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
}