package com.hacknet.wheelsy.controller;
import com.hacknet.wheelsy.domain.model.Accessories;
import com.hacknet.wheelsy.domain.service.AccessoriesService;
import com.hacknet.wheelsy.resource.AccessoriesResource;
import com.hacknet.wheelsy.resource.SaveAccessoriesResource;
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
public class AccessoriesController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AccessoriesService accessoriesService;

    @Operation(summary = "Get accessories", description = "Get All Accessories by Pages", tags = {"Accessories"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Accessories returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/accessories")
    public Page<AccessoriesResource> getAllAccessories(Pageable pageable) {
        Page<Accessories> AccessoriesPage = accessoriesService.getAllAccessories(pageable);
        List<AccessoriesResource> resources = AccessoriesPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(summary = "Create accessory", description = "Create a new accessory", tags = {"Accessories"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accessory created", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/products/{productId}/accessories")
    public AccessoriesResource createAccessories(
            @PathVariable Long productId,
            @Valid @RequestBody SaveAccessoriesResource resource
    ) {
        Accessories accessories = convertToEntity(resource);
        return convertToResource(accessoriesService.createAccessories(productId,accessories));
    }
    @Operation(summary = "Update accessory", description = "Update a accessory for given Id", tags = {"Accessories"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accessory updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/accessories/{accessoriesId}")
    public AccessoriesResource updateEntrepreneur(@PathVariable Long accessoriesId, @RequestBody SaveAccessoriesResource resource) {
        Accessories accessories = convertToEntity(resource);
        return convertToResource(accessoriesService.updateAccessories(accessoriesId, accessories));
    }
    @Operation(summary = "Delete accessory", description = "Delete a accessory for given Id", tags = {"Accessories"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accessory deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/accessories/{accessoriesId}")
    public ResponseEntity<?> deleteSpecialist(@PathVariable Long accessoriesId) {
        return accessoriesService.deleteAccessories(accessoriesId);
    }

    private Accessories convertToEntity(SaveAccessoriesResource resource) {
        return mapper.map(resource, Accessories.class);
    }

    private AccessoriesResource convertToResource(Accessories entity) {
        return mapper.map(entity, AccessoriesResource.class);
    }

}