package com.hacknet.wheelsy.domain.service;

import com.hacknet.wheelsy.domain.model.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface VehicleService {
    Page<Vehicle> getAllVehicle(Pageable pageable);
    Vehicle getVehicleById(Long accessoriesId);
    Vehicle createVehicle(Long productId,Vehicle accessories);
    Vehicle updateVehicle(Long accessoriesId, Vehicle accessoriesDetails);
    ResponseEntity<?> deleteVehicle(Long accessoriesId);}