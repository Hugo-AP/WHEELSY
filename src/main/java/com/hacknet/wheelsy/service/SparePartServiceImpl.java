package com.hacknet.wheelsy.service;

import com.hacknet.wheelsy.domain.model.SparePart;
import com.hacknet.wheelsy.domain.repository.SparePartRepository;
import com.hacknet.wheelsy.domain.repository.ProductRepository;
import com.hacknet.wheelsy.domain.service.SparePartService;
import com.hacknet.wheelsy.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class SparePartServiceImpl implements SparePartService {
    @Autowired
    private SparePartRepository sparePartRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<SparePart> getAllSparePart(Pageable pageable) {
        return sparePartRepository.findAll(pageable);
    }

    @Override
    public SparePart getSparePartById(Long sparePartId) {
        return sparePartRepository.findById(sparePartId).orElseThrow(()->new ResourceNotFoundException("SparePart","Id", sparePartId));
    }

    @Override
    public SparePart createSparePart(Long productId,SparePart sparePart) {
        return productRepository.findById(productId).map(product -> {
            sparePart.setProduct(product);
            return sparePartRepository.save(sparePart);
        }).orElseThrow(()->new ResourceNotFoundException(
                "Product","Id",productId));

    }

    @Override
    public SparePart updateSparePart(Long sparePartId, SparePart sparePartDetails) {
        return sparePartRepository.findById(sparePartId).map(sparePart -> {

            sparePart.setName(sparePartDetails.getName());
            sparePart.setDescription(sparePartDetails.getDescription());
            return sparePartRepository.save(sparePart);
        }).orElseThrow(()-> new ResourceNotFoundException("Entrepreneur","Id",sparePartId));

    }

    @Override
    public ResponseEntity<?> deleteSparePart(Long sparePartId) {
        SparePart sparePart = sparePartRepository.findById(sparePartId).
                orElseThrow(()-> new ResourceNotFoundException("Specialist", "Id", sparePartId));
        sparePartRepository.delete(sparePart);
        return ResponseEntity.ok().build();
    }

}