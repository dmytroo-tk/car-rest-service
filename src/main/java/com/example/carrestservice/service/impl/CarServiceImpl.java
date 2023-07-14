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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public CarDTO create(CarDTO carDTO) {
        try {
            Car car = carMapper.toEntity(carDTO);
            return carMapper.toDto(carRepository.save(car));
        } catch (RuntimeException e) {
            throw new EntityNotCreatedException(carDTO + " not created", e);
        }
    }

    @Override
    public List<CarDTO> createAll(List<CarDTO> carDTOs) {
        try {
            List<Car> cars = carMapper.toEntity(carDTOs);
            return carMapper.toDto(carRepository.saveAll(cars));
        } catch (RuntimeException e) {
            throw new EntityNotCreatedException(carDTOs + " not created", e);
        }
    }

    @Override
    public CarDTO get(String id) {
        try {
            return carMapper.toDto(carRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("Car with id: " + id + " not found")));
        } catch (RuntimeException e) {
            throw new EntityNotFoundException("Car with id: " + id + " not found");
        }
    }

    @Override
    public Page<CarDTO> getAll(Pageable pageable) {
        Page<Car> carPage = carRepository.findAll(pageable);
        List<CarDTO> carDTOs = carMapper.toDto(carPage.getContent());

        return new PageImpl<>(carDTOs, pageable, carPage.getTotalElements());
    }

    @Override
    public CarDTO update(CarDTO carDTO) {
        try {
            Car car = carRepository.findById(carDTO.objectId()).orElseThrow(
                    () -> new EntityNotFoundException("Car with id: " + carDTO.objectId() + " not found"));
            BeanUtils.copyProperties(carMapper.toEntity(carDTO), car);

            return carMapper.toDto(carRepository.save(car));
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

    @Override
    public Page<CarDTO> searchCars(String brand, String model, Integer minYear, Integer maxYear, String bodyType, Pageable pageable) {
        Page<Car> carPage = carRepository.search(brand, model, minYear, maxYear, bodyType, pageable);
        List<CarDTO> carDTOs = carMapper.toDto(carPage.getContent());

        return new PageImpl<>(carDTOs, pageable, carPage.getTotalElements());
    }
}
