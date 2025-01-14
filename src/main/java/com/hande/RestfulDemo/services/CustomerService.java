package com.hande.RestfulDemo.services;

import com.hande.RestfulDemo.model.Customer;

import java.util.List;
import java.util.UUID;

/*
 * 14/01/2025
 * handebarkan
 */
public interface CustomerService {
    Customer getCustomerById(UUID id);

    List<Customer> listCustomers();

    Customer saveNewCustomer(Customer customer);

    void updateCustomerById(UUID customerId, Customer customer);

    void deleteCustomerById(UUID customerId);

    void patchCustomerById(UUID customerId, Customer customer);
}
