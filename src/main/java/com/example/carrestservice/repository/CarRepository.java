package com.example.carrestservice.repository;

import com.example.carrestservice.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    @Query("SELECT c FROM Car c WHERE " +
            "(:brand IS NULL OR c.brand = :brand) AND " +
            "(:model IS NULL OR c.model = :model) AND " +
            "(:minYear IS NULL OR c.yearOfManufacture >= :minYear) AND " +
            "(:maxYear IS NULL OR c.yearOfManufacture <= :maxYear) AND " +
            "(:bodyType IS NULL OR c.bodyType = :bodyType)")
    List<Car> search(
            @Param("brand") String brand,
            @Param("model") String model,
            @Param("minYear") Integer minYear,
            @Param("maxYear") Integer maxYear,
            @Param("bodyType") String bodyType
    );
}