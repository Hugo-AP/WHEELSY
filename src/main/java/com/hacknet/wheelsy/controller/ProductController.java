package com.hacknet.wheelsy.controller;

import com.hacknet.wheelsy.domain.model.Category;
import com.hacknet.wheelsy.domain.model.Entrepreneur;
import com.hacknet.wheelsy.domain.model.Product;

import com.hacknet.wheelsy.domain.model.User;
import com.hacknet.wheelsy.domain.service.ProductService;
import com.hacknet.wheelsy.resource.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProductService productService;

    @GetMapping("/entrepreneurs/{entrepreneurId}/products")
    public Page<ProductResource> getAllProductsByEntrepreneurs (
            @PathVariable Long entrepreneurId,Pageable pageable){

        Page<Product> productPage = productService.getAllProductsByEntrepreneurId(entrepreneurId,pageable);
        List<ProductResource> resources = productPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/entrepreneurs/{entrepreneurId}/products/{productId}")
    public ProductResource getProductByIdAndEntrepreneurId(@PathVariable(name = "entrepreneurId") Long entrepreneurId,
                                                         @PathVariable(name = "productId") Long productId) {
        return convertToResource(productService.getProductByIdAndEntrepreneurId(productId, entrepreneurId));
    }

    @GetMapping("/products/{category}")
    public ProductResource getAllProductsByCategory(@PathVariable (value = "category") String category) {
        return convertToResource(productService.getProductByCategory(category));
    }

    @GetMapping("/products")
    public Page<ProductResource> getAllProducts(Pageable pageable) {
        Page<Product> productPage = productService.getAllProducts(pageable);
        List<ProductResource> resources = productPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("/entrepreneurs/{entrepreneurId}/products")
    public ProductResource createProducts(
            @PathVariable Long entrepreneurId,
            @Valid @RequestBody SaveProductResource resource ){
        return convertToResource(productService.createProduct(entrepreneurId,convertToEntity(resource)));
    }
    @PutMapping("/entrepreneurs/{entrepreneurId}/products/{productId}")
    public ProductResource updateProduct(
            @PathVariable (value = "entrepreneurId") Long entrepreneurId,
            @PathVariable (value = "productId") Long productId,
            @Valid @RequestBody SaveProductResource resource){
        return convertToResource(productService.updateProduct(entrepreneurId,productId,convertToEntity(resource)));
    }
    @DeleteMapping("/entrepreneurs/{entrepreneurId}/products/{productId}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable (value = "entrepreneurId") Long entrepreneurId,
            @PathVariable (value = "productId") Long productId  ){
        return productService.deleteProduct(entrepreneurId,productId);
    }

    private Product convertToEntity(SaveProductResource resource) {
        return mapper.map(resource, Product.class);
    }

    private ProductResource convertToResource(Product entity) {
        return mapper.map(entity, ProductResource.class);
    }


}
