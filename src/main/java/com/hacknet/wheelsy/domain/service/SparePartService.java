package com.hacknet.wheelsy.domain.service;

import com.hacknet.wheelsy.domain.model.SparePart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SparePartService {
    Page<SparePart> getAllSparePart(Pageable pageable);
    SparePart getSparePartById(Long sparePartId);
    SparePart createSparePart(Long productId,SparePart sparePart);
    SparePart updateSparePart(Long sparePartId, SparePart sparePartDetails);
    ResponseEntity<?> deleteSparePart(Long sparePartId);}