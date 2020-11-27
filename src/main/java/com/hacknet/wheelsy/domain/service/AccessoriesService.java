package com.hacknet.wheelsy.domain.service;

import com.hacknet.wheelsy.domain.model.Accessories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AccessoriesService {
    Page<Accessories> getAllAccessories(Pageable pageable);
    Accessories getAccessoriesById(Long accessoriesId);
    Accessories createAccessories(Long productId,Accessories accessories);
    Accessories updateAccessories(Long accessoriesId, Accessories accessoriesDetails);
    ResponseEntity<?> deleteAccessories(Long accessoriesId);}