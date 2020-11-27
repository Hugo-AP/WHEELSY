package com.hacknet.wheelsy.controller;
import com.hacknet.wheelsy.domain.model.Vehicle;
import com.hacknet.wheelsy.domain.service.VehicleService;
import com.hacknet.wheelsy.resource.VehicleResource;
import com.hacknet.wheelsy.resource.SaveVehicleResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class VehicleController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/vehicle")
    public Page<VehicleResource> getAllVehicle(Pageable pageable) {
        Page<Vehicle> VehiclePage = vehicleService.getAllVehicle(pageable);
        List<VehicleResource> resources = VehiclePage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }
    @PostMapping("/products/{productId}/vehicle")
    public VehicleResource createVehicle(
            @PathVariable Long productId,
            @Valid @RequestBody SaveVehicleResource resource
    ) {
        Vehicle vehicle = convertToEntity(resource);
        return convertToResource(vehicleService.createVehicle(productId,vehicle));
    }
    @PutMapping("/vehicle/{vehicleId}")
    public VehicleResource updateEntrepreneur(@PathVariable Long vehicleId, @RequestBody SaveVehicleResource resource) {
        Vehicle vehicle = convertToEntity(resource);
        return convertToResource(vehicleService.updateVehicle(vehicleId, vehicle));
    }
    @DeleteMapping("/vehicle/{vehicleId}")
    public ResponseEntity<?> deleteSpecialist(@PathVariable Long vehicleId) {
        return vehicleService.deleteVehicle(vehicleId);
    }

    private Vehicle convertToEntity(SaveVehicleResource resource) {
        return mapper.map(resource, Vehicle.class);
    }

    private VehicleResource convertToResource(Vehicle entity) {
        return mapper.map(entity, VehicleResource.class);
    }

}