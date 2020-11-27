package com.hacknet.wheelsy.controller;

import com.hacknet.wheelsy.domain.model.Product;
import com.hacknet.wheelsy.domain.model.Sales;
import com.hacknet.wheelsy.domain.model.User;
import com.hacknet.wheelsy.domain.service.ProductService;
import com.hacknet.wheelsy.domain.service.SalesService;
import com.hacknet.wheelsy.resource.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SalesDetails {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SalesService salesService;
    @Autowired
    private ProductService productService;

    @PostMapping("/sales/{saleId}/products/{productId}")
    public SalesResource assignSale(
            @PathVariable(name = "saleId") Long saleId,
            @PathVariable(name = "productId") Long productId) {
        return convertToResource(salesService.assignProduct(saleId, productId));
    }

    @GetMapping("/sales/{saleId}/products")
    public Page<ProductResource> getAllProductsBySaleId(
            @PathVariable(name = "saleId") Long saleId,
            Pageable pageable) {
        Page<Product> productPage = productService.getAllProductsBySalesId(saleId,pageable);
        List<ProductResource> resources = productPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private Sales convertToEntity(SaveSalesResource resource) {
        return mapper.map(resource, Sales.class);
    }

    private SalesResource convertToResource(Sales entity) {
        return mapper.map(entity, SalesResource.class);
    }

    private Product convertToEntity(SaveProductResource resource) {
        return mapper.map(resource, Product.class);
    }

    private ProductResource convertToResource(Product entity) {
        return mapper.map(entity, ProductResource.class);
    }

}
