package com.hacknet.wheelsy.service;

import com.hacknet.wheelsy.domain.model.Vehicle;
import com.hacknet.wheelsy.domain.repository.VehicleRepository;
import com.hacknet.wheelsy.domain.repository.ProductRepository;
import com.hacknet.wheelsy.domain.service.VehicleService;
import com.hacknet.wheelsy.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Vehicle> getAllVehicle(Pageable pageable) {
        return vehicleRepository.findAll(pageable);
    }

    @Override
    public Vehicle getVehicleById(Long vehicleId) {
        return vehicleRepository.findById(vehicleId).orElseThrow(()->new ResourceNotFoundException("Vehicle","Id", vehicleId));
    }

    @Override
    public Vehicle createVehicle(Long productId,Vehicle vehicle) {
        return productRepository.findById(productId).map(product -> {
            vehicle.setProduct(product);
            return vehicleRepository.save(vehicle);
        }).orElseThrow(()->new ResourceNotFoundException(
                "Product","Id",productId));

    }

    @Override
    public Vehicle updateVehicle(Long vehicleId, Vehicle vehicleDetails) {
        return vehicleRepository.findById(vehicleId).map(vehicle -> {

            vehicle.setName(vehicleDetails.getName());
            vehicle.setDescription(vehicleDetails.getDescription());
            return vehicleRepository.save(vehicle);
        }).orElseThrow(()-> new ResourceNotFoundException("Entrepreneur","Id",vehicleId));

    }

    @Override
    public ResponseEntity<?> deleteVehicle(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).
                orElseThrow(()-> new ResourceNotFoundException("Specialist", "Id", vehicleId));
        vehicleRepository.delete(vehicle);
        return ResponseEntity.ok().build();
    }

}