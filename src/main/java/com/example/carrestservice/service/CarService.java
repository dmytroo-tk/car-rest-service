package com.example.carrestservice.service;

import com.example.carrestservice.model.dto.CarDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService {
    CarDTO create(CarDTO carDTO);

    List<CarDTO> createAll(List<CarDTO> carDTOs);

    CarDTO get(String id);

    Page<CarDTO> getAll(Pageable pageable);

    CarDTO update(CarDTO carDTO);

    void delete(String id);

    Page<CarDTO> searchCars(String brand, String model, Integer minYear, Integer maxYear, String bodyType, Pageable pageable);
}
