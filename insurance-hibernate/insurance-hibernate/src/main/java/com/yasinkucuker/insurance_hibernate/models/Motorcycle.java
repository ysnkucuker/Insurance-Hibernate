package com.yasinkucuker.insurance_hibernate.models;

import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Motorcycle extends Vehicle{
    private double enginePower;

    public Motorcycle(double enginePower) {
        this.enginePower = enginePower;
    }

    public Motorcycle(String model, int year, String plate, double enginePower) {
        super(model, year, plate);
        this.enginePower = enginePower;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(double enginePower) {
        this.enginePower = enginePower;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Motorcycle that = (Motorcycle) o;
        return Double.compare(enginePower, that.enginePower) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), enginePower);
    }

    @Override
    public String toString() {
        return "Motorcycle{" +
                "enginePower=" + enginePower +
                '}';
    }
}

