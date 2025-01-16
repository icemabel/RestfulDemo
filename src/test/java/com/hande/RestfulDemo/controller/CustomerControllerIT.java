package com.hande.RestfulDemo.controller;

import com.hande.RestfulDemo.entities.Customer;
import com.hande.RestfulDemo.mappers.CustomerMapper;
import com.hande.RestfulDemo.model.CustomerDTO;
import com.hande.RestfulDemo.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    CustomerMapper customerMapper;

    @Test
    void testDeleteByIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerController.deleteById(UUID.randomUUID());
        });
    }

    @Transactional
    @Rollback
    @Test
    void testDeleteById() {
        Customer customer = customerRepository.findAll().get(0);

        ResponseEntity responseEntity = customerController.deleteById(customer.getCustomerId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));
        assertThat(customerRepository.findById(customer.getCustomerId()).isEmpty());
    }

    @Test
    void testUpdateNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerController.updateCustomerById(UUID.randomUUID(), CustomerDTO.builder().build());
        });

    }

    @Transactional
    @Rollback
    @Test
    void updateExistingCustomerTest() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDto(customer);

        customerDTO.setCustomerId(null);
        customerDTO.setVersion(null);
        final String customerName = "UPDATED";
        customerDTO.setCustomerName(customerName);

        ResponseEntity responseEntity = customerController.updateCustomerById(customer.getCustomerId(), customerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Customer updatedCustomer = customerRepository.findById(customer.getCustomerId()).get();
        assertThat(updatedCustomer.getCustomerName()).isEqualTo(customerName);
    }

    @Transactional
    @Rollback
    @Test
    void testSaveNewCustomer() {
        CustomerDTO dto = CustomerDTO.builder()
                .customerName("New Name")
                .build();
        ResponseEntity responseEntity = customerController.handlePost(dto);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[4]);

        Customer customer = customerRepository.findById(savedUUID).get();
        assertThat(customer).isNotNull();
    }

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