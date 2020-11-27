package com.hacknet.wheelsy.controller;
import com.hacknet.wheelsy.domain.model.Entrepreneur;
import com.hacknet.wheelsy.domain.service.EntrepreneurService;
import com.hacknet.wheelsy.resource.EntrepreneurResource;
import com.hacknet.wheelsy.resource.SaveEntrepreneurResource;
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
public class EntrepreneurController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private EntrepreneurService entrepreneurService;

    @Operation(summary = "Get entrepreneurs", description = "Get All entrepreneurs by Pages", tags = {"Entrepreneurs"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All entrepreneurs returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/entrepreneurs")
    public Page<EntrepreneurResource> getAllEntrepreneurs(Pageable pageable) {
        Page<Entrepreneur> EntrepreneurPage = entrepreneurService.getAllEntrepreneurs(pageable);
        List<EntrepreneurResource> resources = EntrepreneurPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(summary = "Create entrepreneur", description = "Create a new entrepreneur", tags = {"Entrepreneurs"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrepreneur created", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/entrepreneurs")
    public EntrepreneurResource createEntrepreneur(@Valid @RequestBody SaveEntrepreneurResource resource) {
        Entrepreneur entrepreneur = convertToEntity(resource);
        return convertToResource(entrepreneurService.createEntrepreneur(entrepreneur));
    }
    @Operation(summary = "Update entrepreneur", description = "Update a entrepreneur for given Id", tags = {"Entrepreneurs"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrepreneur updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/entrepreneurs/{entrepreneurId}")
    public EntrepreneurResource updateEntrepreneur(@PathVariable Long entrepreneurId, @RequestBody SaveEntrepreneurResource resource) {
        Entrepreneur entrepreneur = convertToEntity(resource);
        return convertToResource(entrepreneurService.updateEntrepreneur(entrepreneurId, entrepreneur));
    }
    @Operation(summary = "Delete entrepreneur", description = "Delete a entrepreneur for given Id", tags = {"Entrepreneurs"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrepreneur Deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/entrepreneurs/{entrepreneurId}")
    public ResponseEntity<?> deleteSpecialist(@PathVariable Long entrepreneurId) {
        return entrepreneurService.deleteEntrepreneur(entrepreneurId);
    }

    private Entrepreneur convertToEntity(SaveEntrepreneurResource resource) {
        return mapper.map(resource, Entrepreneur.class);
    }

    private EntrepreneurResource convertToResource(Entrepreneur entity) {
        return mapper.map(entity, EntrepreneurResource.class);
    }

}

