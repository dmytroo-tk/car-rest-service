package com.example.carrestservice;

import com.example.carrestservice.exception.CSVFileDataImportException;
import com.example.carrestservice.model.dto.CarDTO;
import com.example.carrestservice.service.CarService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AppRunner implements ApplicationRunner {
    private final CarService carService;
    @Value("${cars-file-path}")
    private String carsFilePath;

    @Override
    public void run(ApplicationArguments args) {
        try (CSVReader csvReader = new CSVReader(new FileReader(carsFilePath))) {
            List<String[]> records = csvReader.readAll();
            records.remove(0);

            List<CarDTO> cars = new ArrayList<>();

            for (String[] record : records) {
                String objectId = record[0];
                String brand = record[1];
                int yearOfManufacture = Integer.parseInt(record[2]);
                String model = record[3];
                String bodyType = record[4];

                cars.add(new CarDTO(objectId, brand, model, yearOfManufacture, bodyType));
            }

            carService.createAll(cars);
        } catch (IOException | CsvException e) {
            throw new CSVFileDataImportException("Data from csv file not imported to db", e);
        }
    }
}
