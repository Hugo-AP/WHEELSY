package com.hacknet.wheelsy.domain.repository;

import com.hacknet.wheelsy.domain.model.Sales;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import  java.util.Optional;

public interface SalesRepository extends JpaRepository<Sales,Long>{
    Page<Sales> findByUserId(Long SalesId, Pageable pageable);
    Optional<Sales> findByIdAndUserId(Long id, Long user_id);
    //Optional<Sales> findByTitle(String title);
}
