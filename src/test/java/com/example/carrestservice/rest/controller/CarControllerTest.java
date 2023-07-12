package com.example.carrestservice.rest.controller;

import com.example.carrestservice.model.dto.CarDTO;
import com.example.carrestservice.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @Test
    void testGetAllCars() throws Exception {
        List<CarDTO> cars = new ArrayList<>();
        cars.add(new CarDTO("sdasd1", "BMW", "523", 2014, "Sedan"));
        cars.add(new CarDTO("sdasd2", "Audi", "Q5", 2012, "SUV"));

        when(carService.getAll(any(Pageable.class))).thenReturn(new PageImpl<>(cars));

        mockMvc.perform(get("/api/v1/cars?size=10&page=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].objectId").value("sdasd1"))
                .andExpect(jsonPath("$.content[0].brand").value("BMW"))
                .andExpect(jsonPath("$.content[0].model").value("523"))
                .andExpect(jsonPath("$.content[0].yearOfManufacture").value(2014))
                .andExpect(jsonPath("$.content[0].bodyType").value("Sedan"));
    }

    @Test
    void testGetCar() throws Exception {
        CarDTO carDTO = new CarDTO("sdasd1", "BMW", "523", 2014, "Sedan");

        when(carService.get(any(String.class))).thenReturn(carDTO);

        mockMvc.perform(get("/api/v1/cars/{id}", "sdasd1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.objectId").value("sdasd1"))
                .andExpect(jsonPath("$.brand").value("BMW"))
                .andExpect(jsonPath("$.model").value("523"))
                .andExpect(jsonPath("$.yearOfManufacture").value(2014))
                .andExpect(jsonPath("$.bodyType").value("Sedan"));
    }

    @Test
    void testCreateCar() throws Exception {
        CarDTO carDTO = new CarDTO("3", "Toyota", "Corolla", 2012, "Sedan");
        when(carService.create(any(CarDTO.class))).thenReturn(carDTO);

        mockMvc.perform(post("/api/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "objectId" : "3",
                                    "brand" : "Toyota",
                                    "model" : "Corolla",
                                    "yearOfManufacture" : 2012,
                                    "bodyType" : "Sedan"
                                }"""))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.objectId").value(carDTO.objectId()))
                .andExpect(jsonPath("$.brand").value(carDTO.brand()))
                .andExpect(jsonPath("$.model").value(carDTO.model()))
                .andExpect(jsonPath("$.yearOfManufacture").value(carDTO.yearOfManufacture()))
                .andExpect(jsonPath("$.bodyType").value(carDTO.bodyType()));

        verify(carService, times(1)).create(carDTO);
    }

    @Test
    void testDeleteCar() throws Exception {
        doNothing().when(carService).delete(any(String.class));

        mockMvc.perform(delete("/api/v1/cars/{id}", "sdas1"))
                .andExpect(status().isNoContent());

        verify(carService).delete(any(String.class));
    }
    @Test
    void testSearchCars() throws Exception {
        List<CarDTO> cars = new ArrayList<>();
        cars.add(new CarDTO("sdasd1", "BMW", "523", 2014, "Sedan"));

        when(carService.searchCars(anyString(), anyString(), anyInt(), anyInt(), anyString(), any(Pageable.class))).thenReturn(new PageImpl<>(cars));

        mockMvc.perform(get("/api/v1/cars/search?size=10&page=1")
                        .param("brand", "BMW")
                        .param("model", "523")
                        .param("minYear", "2010")
                        .param("maxYear", "2020")
                        .param("bodyType", "Sedan"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].objectId").value("sdasd1"))
                .andExpect(jsonPath("$.content[0].brand").value("BMW"))
                .andExpect(jsonPath("$.content[0].model").value("523"))
                .andExpect(jsonPath("$.content[0].yearOfManufacture").value(2014))
                .andExpect(jsonPath("$.content[0].bodyType").value("Sedan"));
    }
}