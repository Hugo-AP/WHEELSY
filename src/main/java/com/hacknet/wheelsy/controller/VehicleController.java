package com.hacknet.wheelsy.controller;
import com.hacknet.wheelsy.domain.model.Vehicle;
import com.hacknet.wheelsy.domain.service.VehicleService;
import com.hacknet.wheelsy.resource.VehicleResource;
import com.hacknet.wheelsy.resource.SaveVehicleResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get Vehicles", description = "Get All Vehicles by Pages", tags = {"Vehicles"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Vehicles returned", content = @Content(mediaType = "application/json"))
    })

    @GetMapping("/vehicle")
    public Page<VehicleResource> getAllVehicle(Pageable pageable) {
        Page<Vehicle> VehiclePage = vehicleService.getAllVehicle(pageable);
        List<VehicleResource> resources = VehiclePage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(summary = "Create Vehicle", description = "Create a new Vehicle", tags = {"Vehicles"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle created", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/products/{productId}/vehicle")
    public VehicleResource createVehicle(
            @PathVariable Long productId,
            @Valid @RequestBody SaveVehicleResource resource
    ) {
        Vehicle vehicle = convertToEntity(resource);
        return convertToResource(vehicleService.createVehicle(productId,vehicle));
    }
    @Operation(summary = "Update Vehicle", description = "Update a Vehicle for given Id", tags = {"Vehicles"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/vehicle/{vehicleId}")
    public VehicleResource updateEntrepreneur(@PathVariable Long vehicleId, @RequestBody SaveVehicleResource resource) {
        Vehicle vehicle = convertToEntity(resource);
        return convertToResource(vehicleService.updateVehicle(vehicleId, vehicle));
    }
    @Operation(summary = "Delete Vehicle", description = "Delete a Vehicle for given Id", tags = {"Vehicles"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle deleted", content = @Content(mediaType = "application/json"))
    })
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