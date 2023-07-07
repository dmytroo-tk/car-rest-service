package com.example.carrestservice.service;

import com.example.carrestservice.model.dto.CarDTO;

import java.util.List;

public interface CarService {
    CarDTO create(CarDTO carDTO);
    CarDTO get(String id);
    List<CarDTO> getAll();
    CarDTO update(CarDTO carDTO);
    void delete(String id);
}
