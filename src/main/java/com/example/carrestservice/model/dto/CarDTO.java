package com.example.carrestservice.model.dto;

public record CarDTO(
        String objectId,
        String brand,
        String model,
        int yearOfManufacture,
        String bodyType) {
}