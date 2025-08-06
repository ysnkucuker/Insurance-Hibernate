package com.yasinkucuker.insurance_hibernate.repository;

import com.yasinkucuker.insurance_hibernate.models.Customer;
import com.yasinkucuker.insurance_hibernate.models.Vehicle;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer>{
    List<Vehicle> findAllVehicleOfCustomerWithSSID(long ssid);
}
