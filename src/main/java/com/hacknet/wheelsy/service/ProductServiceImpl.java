package com.hacknet.wheelsy.service;

import com.hacknet.wheelsy.domain.model.Product;
import com.hacknet.wheelsy.domain.repository.EntrepreneurRepository;
import com.hacknet.wheelsy.domain.repository.ProductRepository;
import com.hacknet.wheelsy.domain.repository.UserRepository;
import com.hacknet.wheelsy.domain.service.ProductService;
import com.hacknet.wheelsy.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntrepreneurRepository entrepreneurRepository;


    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> getAllProductsByEntrepreneurId(Long entrepreneurId, Pageable pageable) {
        return productRepository.findByEntrepreneurId(entrepreneurId,pageable);
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException(
                "Product","Id", productId
        ));
    }

    @Override
    public Product createProduct(Long entrepreneurId ,Product product) {
        return entrepreneurRepository.findById(entrepreneurId).map(entrepreneur->{
            product.setEntrepreneur(entrepreneur);
            return productRepository.save(product);
        }).orElseThrow(()->new ResourceNotFoundException(
                "Entrepreneur","Id", entrepreneurId
        ));
    }

    @Override
    public Product updateProduct(Long entrepreneurId,Long productId, Product productDetails) {
        if (!entrepreneurRepository.existsById(entrepreneurId))
            throw new ResourceNotFoundException("Entrepreneur","Id",entrepreneurId);
        return productRepository.findById(productId).map(product -> {
            product.setCategory(productDetails.getCategory());
            product.setSku(productDetails.getSku());
            return productRepository.save(product);
        }).orElseThrow(()->new ResourceNotFoundException("Product","Id",productId));
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long productId,Long entrepreneurId) {
        if (!entrepreneurRepository.existsById(entrepreneurId))
            throw new ResourceNotFoundException("Entrepreneur","Id",entrepreneurId);
        return productRepository.findById(productId).map(product -> {
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Product","Id",productId));
    }
}
