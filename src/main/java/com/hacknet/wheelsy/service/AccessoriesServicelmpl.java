package com.hacknet.wheelsy.service;

import com.hacknet.wheelsy.domain.model.Accessories;
import com.hacknet.wheelsy.domain.repository.AccessoriesRepository;
import com.hacknet.wheelsy.domain.service.AccessoriesService;
import com.hacknet.wheelsy.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class AccessoriesServicelmpl implements AccessoriesService {
    @Autowired
    private AccessoriesRepository accessoriesRepository;

    @Override
    public Page<Accessories> getAllAccessories(Pageable pageable) {
        return accessoriesRepository.findAll(pageable);
    }

    @Override
    public Accessories getAccessoriesById(Long accessoriesId) {
        return accessoriesRepository.findById(accessoriesId).orElseThrow(()->new ResourceNotFoundException("Accessories","Id", accessoriesId));
    }

    @Override
    public Accessories createAccessories(Accessories accessories) {
        return accessoriesRepository.save(accessories);
    }

    @Override
    public Accessories updateAccessories(Long accessoriesId, Accessories accessoriesDetails) {
        return accessoriesRepository.findById(accessoriesId).map(accessories -> {

            accessories.setName(accessoriesDetails.getName());
            accessories.setDescription(accessoriesDetails.getDescription());
            return accessoriesRepository.save(accessories);
        }).orElseThrow(()-> new ResourceNotFoundException("Entrepreneur","Id",accessoriesId));

    }

    @Override
    public ResponseEntity<?> deleteAccessories(Long accessoriesId) {
        Accessories accessories = accessoriesRepository.findById(accessoriesId).
                orElseThrow(()-> new ResourceNotFoundException("Specialist", "Id", accessoriesId));
        accessoriesRepository.delete(accessories);
        return ResponseEntity.ok().build();
    }

}