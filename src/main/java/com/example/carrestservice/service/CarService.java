package com.example.carrestservice.service;

import com.example.carrestservice.model.dto.CarDTO;

import java.util.List;

public interface CarService {
    CarDTO create(CarDTO carDTO);

    List<CarDTO> createAll(List<CarDTO> carDTOs);

    CarDTO get(String id);

    List<CarDTO> getAll();

    CarDTO update(CarDTO carDTO);

    void delete(String id);

    List<CarDTO> searchCars(String brand, String model, Integer minYear, Integer maxYear, String bodyType);
}
