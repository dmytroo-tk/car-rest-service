package com.example.carrestservice.model.mapper;

import com.example.carrestservice.model.dto.CarDTO;
import com.example.carrestservice.model.entity.Car;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarMapper {
    private final ModelMapper modelMapper;

    public CarDTO toCarDto(Car car) {
        return modelMapper.map(car, CarDTO.class);
    }

    public Car toCar(CarDTO carDTO) {
        return modelMapper.map(carDTO, Car.class);
    }

    public List<CarDTO> toCarDto(List<Car> cars) {
        return cars.stream()
                .map(car -> modelMapper.map(car, CarDTO.class))
                .toList();
    }

    public List<Car> toCar(List<CarDTO> carDTOs) {
        return carDTOs.stream()
                .map(carDTO -> modelMapper.map(carDTO, Car.class))
                .toList();
    }
}
