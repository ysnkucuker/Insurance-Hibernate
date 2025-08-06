package com.yasinkucuker.insurance_hibernate;

import com.yasinkucuker.insurance_hibernate.controller.CustomerController;
import com.yasinkucuker.insurance_hibernate.models.*;
import com.yasinkucuker.insurance_hibernate.utils.EntityManagerUtils;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class InsuranceHibernateMVCApp {
    private static EntityManager entityManager = EntityManagerUtils.getEntityManager("mysql-pu");
    private static CustomerController customerController = new CustomerController();
    private static Customer newCustomer = new Customer("Yasin Küçüker", "Sarıyer İstanbul", 1111111111,"05431129459");

    public static void main(String[] args){
        if(checkData()){
            System.out.println("Test data not exist! Will be persisted....");
            persistTestData();
        }

        try {
            // executes test methods
            findAllCustomer();
            persistNewCustomer();
            updateCustomer();
            findCustomer();
            deleteCustomer();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void findCustomer(){
        com.yasinkucuker.insurance_hibernate.utils.LogUtils.logMethodStart(new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName());
        System.out.println(customerController.findCustomer(newCustomer.getId()));
    }

    private static void deleteCustomer(){
        com.yasinkucuker.insurance_hibernate.utils.LogUtils.logMethodStart(new Object(){
        }
                .getClass()
                .getEnclosingMethod()
                .getName());
        customerController.deleteCustomer(newCustomer);
    }

    private static void findAllCustomer(){
        com.yasinkucuker.insurance_hibernate.utils.LogUtils.logMethodStart(new Object(){
        }
                .getClass()
                .getEnclosingMethod()
                .getName());
        List<Customer> customerList = customerController.findAllCustomer();
        for(int i=1; i<=customerList.size(); i++){
            System.out.println(i + " --> " + customerList.get(i-1));
        }
    }

    private static void updateCustomer(){
        com.yasinkucuker.insurance_hibernate.utils.LogUtils.logMethodStart(new Object(){}.getClass().getEnclosingMethod().getName());
        newCustomer.setAddress("Karaman...");
        customerController.saveCustomer(newCustomer);
    }

    private static void persistNewCustomer(){
        com.yasinkucuker.insurance_hibernate.utils.LogUtils.logMethodStart(new Object(){
        }
                .getClass()
                .getEnclosingMethod()
                .getName());
        customerController.saveCustomer(newCustomer);
        findAllCustomer();
    }

    private static boolean checkData(){
        return entityManager.createQuery("from Customer", Customer.class).getResultList().size() == 0;
    }

    private static void persistTestData(){
        System.out.println("Start........");

        Customer customer1 = new Customer("Ali Veli", "Karaman", 123123123L, "1234556678");
        Customer customer2 = new Customer("Kırk Dokuz", "İzmir", 123132133L, "123458978978");
        Customer customer3 = new Customer("Elli", "İstanbul", 513213123L, "12340567564");

        Vehicle car1 = new Car("Hyundai Accent", 2020, "70YSN35", "Yellow");
        Vehicle car2 = new Car("Buggatti Veyron", 2025, "35YSN25", "Black");
        Vehicle moto1 = new Motorcycle("Yamaha", 2015, "06YSN06", 222.55);
        Vehicle moto2 = new Motorcycle("Kawasaki", 2023, "34YSN34", 195.6);
        Vehicle moto3 = new Motorcycle("Suzuki", 2018, "34YSN34", 213.67);

        car1.setCustomer(customer1);
        car2.setCustomer(customer2);
        moto1.setCustomer(customer1);
        moto2.setCustomer(customer3);
        moto3.setCustomer(customer2);

        Accident accident1 = new Accident(LocalDate.of(2022, Month.APRIL, 22));
        Accident accident2 = new Accident(LocalDate.of(2021, Month.AUGUST, 2));
        Accident accident3 = new Accident(LocalDate.of(2020, Month.JANUARY, 22));

        car1.getAccidentList().add(accident1);
        car2.getAccidentList().add(accident1);
        moto1.getAccidentList().add(accident3);
        moto2.getAccidentList().add(accident1);
        moto3.getAccidentList().add(accident2);

        EntityManager entityManager = EntityManagerUtils.getEntityManager("mysql-pu");

        try{
            entityManager.getTransaction().begin();

            entityManager.persist(customer1);
            entityManager.persist(customer2);
            entityManager.persist(customer3);

            entityManager.persist(car1);
            entityManager.persist(car2);
            entityManager.persist(moto1);
            entityManager.persist(moto2);
            entityManager.persist(moto3);

            entityManager.persist(accident1);
            entityManager.persist(accident2);
            entityManager.persist(accident3);

            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            EntityManagerUtils.closeEntityManager(entityManager);
        }
        System.out.println("FINISH.....");
    }
}
