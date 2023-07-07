package com.example.carrestservice.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "cars")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @Column(name = "object_id", unique = true)
    private String objectId;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "year_of_manufacture")
    private int yearOfManufacture;
    @Enumerated(EnumType.STRING)
    @Column(name = "body_type")
    private String bodyType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Car car = (Car) o;
        return objectId != null && Objects.equals(objectId, car.objectId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
