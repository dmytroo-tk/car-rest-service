package com.example.carrestservice.model.mapper;

import com.example.carrestservice.model.dto.CarDTO;
import com.example.carrestservice.model.entity.Car;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarDTO toDto(Car car);

    Car toEntity(CarDTO carDTO);

    List<CarDTO> toDto(List<Car> cars);

    List<Car> toEntity(List<CarDTO> carDTOs);
}
