package com.yasinkucuker.insurance_hibernate.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Kendisi otomatik id vericek
    private long id;
    private String model;
    private int year;
    private String plate;

    // Bir aracın sadece bir müşterisi olur.
    @ManyToOne
    private Customer customer;
    @ManyToMany // Set Liste göre daha optimize. Listte baştan oluşturup ekliyor
    private Set<Accident> accidentList = new HashSet<>(); // Bir araç 0 veya birden fazla kazaya karışabilir

    public Vehicle() {
    }

    public Vehicle(String model, int year, String plate) {
        this.model = model;
        this.year = year;
        this.plate = plate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Set<Accident> getAccidentList() {
        return accidentList;
    }

    public void setAccidentList(Set<Accident> accidentList) {
        this.accidentList = accidentList;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(plate, vehicle.plate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(plate);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "model='" + model + '\'' +
                ", year=" + year +
                ", plate='" + plate + '\'' +
                '}';
    }
}
