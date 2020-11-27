package com.hacknet.wheelsy.controller;

import com.hacknet.wheelsy.domain.model.Sales;
import com.hacknet.wheelsy.domain.model.SubscriptionPlan;
import com.hacknet.wheelsy.domain.service.SalesService;
import com.hacknet.wheelsy.resource.SaveSalesResource;
import com.hacknet.wheelsy.resource.SalesResource;
import com.hacknet.wheelsy.resource.SubscriptionPlanResource;
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
public class SalesController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SalesService salesService;

    @Operation(summary = "Get Sales by userId", description = "Get All Sales by userId", tags = {"Sales"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Sales returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/users/{user_id}/sales")
    public Page<SalesResource> getAllSalesByUserId(
            @PathVariable Long user_id, Pageable pageable) {

        Page<Sales> salesPage = salesService.getAllSalesByUserId(user_id, pageable);
        List<SalesResource> resources = salesPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(summary = "Get Sales", description = "Get All Sales by pages", tags = {"Sales"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Sales returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/sales")
    public Page<SalesResource> getAllSales(Pageable pageable) {
        Page<Sales> salesPage = salesService.getAllSales(pageable);
        List<SalesResource> resources = salesPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(summary = "Get Sales by Id and userId", description = "Get All Sales by Id and userId", tags = {"Sales"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Sales returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/user/{user_id}/sales/{salesId}")
    public SalesResource getSalesByIdAndUserId(@PathVariable(name = "user_id") Long user_id,
                                                         @PathVariable(name = "salesId") Long salesId) {
        return convertToResource(salesService.getSalesByIdAndUserId(user_id, salesId));
    }
    @Operation(summary = "Create Sales", description = "Create a new Sales", tags = {"Sales"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sales created", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/user/{user_id}/sales")
    public SalesResource createSales(@PathVariable Long user_id, @Valid @RequestBody SaveSalesResource resource) {
        return convertToResource(salesService.createSales(user_id, convertToEntity(resource)));
    }
    @Operation(summary = "Update Sales", description = "Update a Sales for given Id", tags = {"Sales"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sales updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("user/{user_id}/sales/{salesId}")
    public SalesResource updateSales(@PathVariable(value = "user_id")Long user_id, @PathVariable(value = "salesId")Long salesId,
                                     @Valid @RequestBody SaveSalesResource resource){
        return convertToResource(salesService.updateSales(user_id, salesId, convertToEntity(resource)));
    }
    @Operation(summary = "Delete Sales", description = "Delete a Sales for given Id", tags = {"Sales"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sales deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("user/{user_id}/sales/{salesId}")
    public ResponseEntity<?> deleteSales(@PathVariable (value = "user_id") Long user_id,
                                        @PathVariable (value = "salesId")Long salesId) {
        return salesService.deleteSales(user_id, salesId);
    }

    private Sales convertToEntity(SaveSalesResource resource) {
        return mapper.map(resource, Sales.class);
    }

    private SalesResource convertToResource(Sales entity) {
        return mapper.map(entity, SalesResource.class);
    }
}
