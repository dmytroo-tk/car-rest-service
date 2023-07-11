package com.example.carrestservice.rest.controller;

import com.example.carrestservice.model.dto.CarDTO;
import com.example.carrestservice.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @PostMapping
    public ResponseEntity<CarDTO> create(@RequestBody CarDTO car) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.create(car));
    }

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAll() {
        return ResponseEntity.ok(carService.getAll());
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

    @GetMapping("/search")
    public ResponseEntity<List<CarDTO>> searchCars(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) Integer maxYear,
            @RequestParam(required = false) String bodyType
    ) {
        List<CarDTO> cars = carService.searchCars(brand, model, minYear, maxYear, bodyType);
        return ResponseEntity.ok(cars);
    }
}
