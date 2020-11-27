package com.hacknet.wheelsy.controller;
import com.hacknet.wheelsy.domain.model.Accessories;
import com.hacknet.wheelsy.domain.service.AccessoriesService;
import com.hacknet.wheelsy.resource.AccessoriesResource;
import com.hacknet.wheelsy.resource.SaveAccessoriesResource;
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

    @GetMapping("/accessories")
    public Page<AccessoriesResource> getAllAccessories(Pageable pageable) {
        Page<Accessories> AccessoriesPage = accessoriesService.getAllAccessories(pageable);
        List<AccessoriesResource> resources = AccessoriesPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }
    @PostMapping("/products/{productId}/accessories")
    public AccessoriesResource createAccessories(
            @PathVariable Long productId,
            @Valid @RequestBody SaveAccessoriesResource resource
    ) {
        Accessories accessories = convertToEntity(resource);
        return convertToResource(accessoriesService.createAccessories(productId,accessories));
    }
    @PutMapping("/accessories/{accessoriesId}")
    public AccessoriesResource updateEntrepreneur(@PathVariable Long accessoriesId, @RequestBody SaveAccessoriesResource resource) {
        Accessories accessories = convertToEntity(resource);
        return convertToResource(accessoriesService.updateAccessories(accessoriesId, accessories));
    }
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