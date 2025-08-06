package com.yasinkucuker.insurance_hibernate.models;

import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Car extends Vehicle{
    private String color;

    public Car(String color) {
        this.color = color;
    }

    public Car(String model, int year, String plate, String color) {
        super(model, year, plate);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Car car = (Car) o;
        return Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                '}';
    }
}