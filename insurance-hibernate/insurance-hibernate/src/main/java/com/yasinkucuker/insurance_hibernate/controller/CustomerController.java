package com.yasinkucuker.insurance_hibernate.controller;

import com.yasinkucuker.insurance_hibernate.models.Customer;
import com.yasinkucuker.insurance_hibernate.models.Vehicle;
import com.yasinkucuker.insurance_hibernate.service.CustomerService;

import java.util.List;

public class CustomerController {
    private CustomerService customerService = new CustomerService();

    public List<Customer> findAllCustomer()
    {
        return  customerService.findAll();
    }

    public Customer findCustomer(long id){
        return  customerService.findById(id);
    }

    public void saveCustomer(Customer customer){
        customerService.saveDatabase(customer);
    }

    public void deleteCustomer(Customer customer){
        customerService.deleteFromDatabase(customer);
    }

    public List<Vehicle> findAllVehicleOfCustomerWithSSID(long ssid){
        return customerService.findAllVehicleOfCustomerWithSSID(ssid);
    }
}
