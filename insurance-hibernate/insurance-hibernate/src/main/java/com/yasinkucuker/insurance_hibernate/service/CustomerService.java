package com.yasinkucuker.insurance_hibernate.service;

import com.yasinkucuker.insurance_hibernate.models.Customer;
import com.yasinkucuker.insurance_hibernate.models.Vehicle;
import com.yasinkucuker.insurance_hibernate.repository.CustomerRepository;
import com.yasinkucuker.insurance_hibernate.utils.EntityManagerUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CustomerService implements CustomerRepository {
    EntityManager em = EntityManagerUtils.getEntityManager("mysql-pu");
    @Override
    public List<Vehicle> findAllVehicleOfCustomerWithSSID(long ssid) {
        TypedQuery<Customer> q = em.createQuery("FROM Customer c Where c.ssid = :ssid", Customer.class);
        q.setParameter("ssid", ssid);
        Customer customer = q.getSingleResult(); // Lazy loading
        return customer.getVehicleList();
    }

    @Override
    public List<Customer> findAll() {
        // JPQL & HQL
        if(!em.isOpen()){
            em = EntityManagerUtils.getEntityManager("mysql-pu");
        }
        // select * from customer
        return em.createQuery("FROM Customer", Customer.class).getResultList();
        // em.createNativeQuery("select * from customer", Customer.class).getResultList();
    }

    @Override
    public Customer findById(long id) {
        if(!em.isOpen()){
            em = EntityManagerUtils.getEntityManager("mysql-pu");
        }
        // select * from customer where id = ?
        Customer customer = em.find(Customer.class, id);
        return  customer;
    }

    @Override
    public void saveDatabase(Customer object) {
        try{
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
            System.out.println("Customer saved...");
        }catch (Exception e){
            em.getTransaction().rollback();
        }finally {
            em.close();
        }
    }

    @Override
    public void deleteFromDatabase(Customer object) {
        try{
            em.getTransaction().begin();
            em.remove(object);
            em.getTransaction().commit();
            System.out.println("Customer deleted");
        }catch (Exception e){
            em.getTransaction().rollback();
        }finally {
            em.close();
        }
    }

    @Override
    public void deleteFromDatabase(int id) {

    }

    @Override
    public void updateOnDatabase(Customer object) {

    }
}
