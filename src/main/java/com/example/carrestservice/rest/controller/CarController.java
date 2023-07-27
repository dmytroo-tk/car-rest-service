package com.example.carrestservice.rest.controller;

import com.example.carrestservice.model.dto.CarDTO;
import com.example.carrestservice.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
@CrossOrigin
public class CarController {
    private final CarService carService;

    @PostMapping
    @Operation(summary = "create", description = "Create car")
    @ApiResponse(responseCode = "201", description = "Car was created successfully", content = @Content(mediaType = "application/json"))
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<CarDTO> create(@RequestBody CarDTO car) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.create(car));
    }

    @GetMapping(params = {"page", "size"})
    @Operation(summary = "getAll", description = "Get all cars")
    @ApiResponse(responseCode = "200", description = "Info about cars was got successfully", content = @Content(mediaType = "application/json"))
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Page<CarDTO>> getAll(@PageableDefault(sort = {"brand"}, direction = Sort.Direction.DESC) @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(carService.getAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "get", description = "Get car by id")
    @ApiResponse(responseCode = "200", description = "Info about car was got successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Car with specified id was not found")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<CarDTO> get(@PathVariable String id) {
        return ResponseEntity.ok(carService.get(id));
    }

    @PutMapping
    @GetMapping(params = {"page", "size"})
    @Operation(summary = "update", description = "Update an existing car")
    @ApiResponse(responseCode = "200", description = "Car was updated successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Car with specified id was not found")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<CarDTO> update(@RequestBody CarDTO car) {
        return ResponseEntity.ok(carService.update(car));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete", description = "Delete car by id")
    @ApiResponse(responseCode = "204", description = "Car was deleted successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Car with specified id was not found")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/search", params = {"page", "size"})
    @Operation(summary = "search", description = "Search cars by params")
    @ApiResponse(responseCode = "200", description = "Info about cars was got successfully")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Page<CarDTO>> searchCars(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) Integer maxYear,
            @RequestParam(required = false) String bodyType,
            @PageableDefault(sort = {"brand"}, direction = Sort.Direction.DESC) @ParameterObject Pageable pageable
    ) {
        Page<CarDTO> cars = carService.searchCars(brand, model, minYear, maxYear, bodyType, pageable);
        return ResponseEntity.ok(cars);
    }
}
