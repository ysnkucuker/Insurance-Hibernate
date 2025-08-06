package com.yasinkucuker.insurance_hibernate.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Customer {
    // variables & members & fields
    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Kendisi otomatik id vericek
    private long id;
    private String fullName;
    private String address;
    private long ssidNumber;
    private String phoneNumber;

    // Bir müşterinin birden fazla aracı var.
    // İlişkinin sahibi kim ise (one to many'lerde one olan sınıf) diğerinde nasıl isimlendirildi ise o değeri ver.
    @OneToMany(mappedBy = "customer")
    private List<Vehicle> vehicleList = new ArrayList<>();

    // constructors
    public Customer() {
    }

    public Customer(String fullName, String address, long ssidNumber, String phoneNumber) {
        this.fullName = fullName;
        this.address = address;
        this.ssidNumber = ssidNumber;
        this.phoneNumber = phoneNumber;
    }

    // Getter & Setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getSsidNumber() {
        return ssidNumber;
    }

    public void setSsidNumber(long ssidNumber) {
        this.ssidNumber = ssidNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    // toString - equals & hashCode

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return ssidNumber == customer.ssidNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ssidNumber);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", ssidNumber=" + ssidNumber +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
