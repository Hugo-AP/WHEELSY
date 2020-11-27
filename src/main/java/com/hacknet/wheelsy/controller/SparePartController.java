package com.hacknet.wheelsy.controller;
import com.hacknet.wheelsy.domain.model.SparePart;
import com.hacknet.wheelsy.domain.service.SparePartService;
import com.hacknet.wheelsy.resource.SparePartResource;
import com.hacknet.wheelsy.resource.SaveSparePartResource;
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
public class SparePartController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SparePartService sparePartService;

    @Operation(summary = "Get SpareParts", description = "Get All SpareParts by Pages", tags = {"SpareParts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All SpareParts returned", content = @Content(mediaType = "application/json"))
    })

    @GetMapping("/sparePart")
    public Page<SparePartResource> getAllSparePart(Pageable pageable) {
        Page<SparePart> SparePartPage = sparePartService.getAllSparePart(pageable);
        List<SparePartResource> resources = SparePartPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(summary = "Create SparePart", description = "Create a new SparePart", tags = {"SpareParts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SparePart created", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/products/{productId}/sparePart")
    public SparePartResource createSparePart(
            @PathVariable Long productId,
            @Valid @RequestBody SaveSparePartResource resource
    ) {
        SparePart sparePart = convertToEntity(resource);
        return convertToResource(sparePartService.createSparePart(productId,sparePart));
    }
    @Operation(summary = "Update SparePart", description = "Update a SparePart for given Id", tags = {"SpareParts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SparePart updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/sparePart/{sparePartId}")
    public SparePartResource updateEntrepreneur(@PathVariable Long sparePartId, @RequestBody SaveSparePartResource resource) {
        SparePart sparePart = convertToEntity(resource);
        return convertToResource(sparePartService.updateSparePart(sparePartId, sparePart));
    }
    @Operation(summary = "Delete SparePart", description = "Delete a SparePart for given Id", tags = {"SpareParts"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SparePart deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/sparePart/{sparePartId}")
    public ResponseEntity<?> deleteSpecialist(@PathVariable Long sparePartId) {
        return sparePartService.deleteSparePart(sparePartId);
    }

    private SparePart convertToEntity(SaveSparePartResource resource) {
        return mapper.map(resource, SparePart.class);
    }

    private SparePartResource convertToResource(SparePart entity) {
        return mapper.map(entity, SparePartResource.class);
    }

}