package com.hande.RestfulDemo.controller;

import com.hande.RestfulDemo.entities.Customer;
import com.hande.RestfulDemo.model.CustomerDTO;
import com.hande.RestfulDemo.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CustomerControllerIT {

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testBeerIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerController.getCustomerById(UUID.randomUUID());
        });
    }

    @Test
    void testGetById() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO dto = customerController.getCustomerById(customer.getCustomerId());

        assertThat(dto).isNotNull();
    }

    @Test
    void testListCustomer() {
        List<CustomerDTO> dtos = customerController.listCustomer();

        assertThat(dtos.size()).isEqualTo(3);
    }

    @Rollback //return the databse, to original state, otherwise the other test fails
    @Transactional
    @Test
    void testEmptyList() {
        customerRepository.deleteAll();;
        List<CustomerDTO> dtos = customerController.listCustomer();

        assertThat(dtos.size()).isEqualTo(0);
    }
}