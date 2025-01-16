package com.hande.RestfulDemo.services;

import com.hande.RestfulDemo.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/*
 * 14/01/2025
 * handebarkan
 */
public interface CustomerService {
    CustomerDTO getCustomerById(UUID id);

    List<CustomerDTO> listCustomers();

    CustomerDTO saveNewCustomer(CustomerDTO customer);

    Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customer);

    Boolean deleteCustomerById(UUID customerId);

    void patchCustomerById(UUID customerId, CustomerDTO customer);
}
