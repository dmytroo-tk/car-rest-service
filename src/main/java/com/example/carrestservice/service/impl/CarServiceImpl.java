package com.example.carrestservice.service.impl;

import com.example.carrestservice.exception.EntityNotCreatedException;
import com.example.carrestservice.exception.EntityNotDeletedException;
import com.example.carrestservice.exception.EntityNotFoundException;
import com.example.carrestservice.exception.EntityNotUpdatedException;
import com.example.carrestservice.model.dto.CarDTO;
import com.example.carrestservice.model.entity.Car;
import com.example.carrestservice.model.mapper.CarMapper;
import com.example.carrestservice.repository.CarRepository;
import com.example.carrestservice.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    private final CarMapper mapper;

    @Override
    public CarDTO create(CarDTO carDTO) {
        try {
            Car car = mapper.toCar(carDTO);
            return mapper.toCarDto(carRepository.save(car));
        } catch (RuntimeException e) {
            throw new EntityNotCreatedException(carDTO + " not created", e);
        }
    }

    @Override
    public CarDTO get(String id) {
        try {
            return mapper.toCarDto(carRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("Car with id: " + id + " not found")));
        } catch (RuntimeException e) {
            throw new EntityNotFoundException("Car with id: " + id + " not found");
        }
    }

    @Override
    public List<CarDTO> getAll() {
        return mapper.toCarDto(carRepository.findAll());
    }

    @Override
    public CarDTO update(CarDTO carDTO) {
        try {
            Car car = carRepository.findById(carDTO.objectId()).orElseThrow(
                    () -> new EntityNotFoundException("Car with id: " + carDTO.objectId() + " not found"));
            BeanUtils.copyProperties(mapper.toCar(carDTO), car);

            return mapper.toCarDto(carRepository.save(car));
        } catch (RuntimeException e) {
            throw new EntityNotUpdatedException(carDTO + " not updated", e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            Car car = carRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("Car with id: " + id + " not found"));
            carRepository.delete(car);
        } catch (RuntimeException e) {
            throw new EntityNotDeletedException("Car with id: " + id + " not deleted", e);
        }
    }
}
