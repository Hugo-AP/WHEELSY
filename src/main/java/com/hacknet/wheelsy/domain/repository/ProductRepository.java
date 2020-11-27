package com.hacknet.wheelsy.domain.repository;

import com.hacknet.wheelsy.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findByEntrepreneurId(Long EntrepreneurId, Pageable pageable);
    ///////
    Optional<Product> findByIdAndEntrepreneurId(Long id, Long entrepreneurId);
    Optional<Product> findByCategory(String category);
    //////
}

