package com.hacknet.wheelsy.domain.service;

import com.hacknet.wheelsy.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    Page<Product> getAllProducts(Pageable pageable);
    Page<Product> getAllProductsByEntrepreneurId(Long EntrepreneurId ,Pageable pageable);
    Product getProductById(Long productId);
    Product createProduct(Long EntrepreneurId ,Product product);
    Product updateProduct(Long productId,Long entrepreneurId, Product productDetails);
    ResponseEntity<?> deleteProduct(Long productId,Long entrepreneurId);
}