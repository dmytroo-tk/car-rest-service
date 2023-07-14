package com.example.carrestservice.rest.controller;

import com.example.carrestservice.model.dto.CarDTO;
import com.example.carrestservice.service.CarService;
import lombok.RequiredArgsConstructor;
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
public class CarController {
    private final CarService carService;

    @PostMapping
    public ResponseEntity<CarDTO> create(@RequestBody CarDTO car) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.create(car));
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<CarDTO>> getAll(@PageableDefault(sort = {"brand"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(carService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> get(@PathVariable String id) {
        return ResponseEntity.ok(carService.get(id));
    }

    @PutMapping
    public ResponseEntity<CarDTO> update(@RequestBody CarDTO car) {
        return ResponseEntity.ok(carService.update(car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/search", params = {"page", "size"})
    public ResponseEntity<Page<CarDTO>> searchCars(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) Integer maxYear,
            @RequestParam(required = false) String bodyType,
            @PageableDefault(sort = {"brand"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<CarDTO> cars = carService.searchCars(brand, model, minYear, maxYear, bodyType, pageable);
        return ResponseEntity.ok(cars);
    }
}
