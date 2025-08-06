package com.yasinkucuker.insurance_hibernate.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Kendisi otomatik id vericek
    private long id;
    private LocalDate accident;
    // Bir kazaya birden fazla araç karışabilir.
    // Many to many'de mappedby yazmadığımz taraf owner olur yani Vehicle
    @ManyToMany(mappedBy = "accidentList")
    private Set<Vehicle> vehicleList = new HashSet<>();

    public Accident() {
    }

    public Accident(LocalDate accident) {
        this.accident = accident;
    }

    public LocalDate getAccident() {
        return accident;
    }

    public void setAccident(LocalDate accident) {
        this.accident = accident;
    }

    public Set<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(Set<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Accident accident1 = (Accident) o;
        return Objects.equals(accident, accident1.accident);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(accident);
    }

    @Override
    public String toString() {
        return "Accident{" +
                "accident=" + accident +
                '}';
    }
}

