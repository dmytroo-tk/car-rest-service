package com.example.carrestservice.model.mapper;

import com.example.carrestservice.model.dto.CarDTO;
import com.example.carrestservice.model.entity.Car;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CarMapper {
    CarDTO mapCarToDto(Car car);

    Car mapDtoToCar(CarDTO carDto);

    List<CarDTO> mapCarToDto(List<Car> cars);

    List<Car> mapDtoToCar(List<CarDTO> carDtos);
}
