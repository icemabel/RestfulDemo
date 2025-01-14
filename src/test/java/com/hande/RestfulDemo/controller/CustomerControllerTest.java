package com.hande.RestfulDemo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/*
 * 14/01/2025
 * handebarkan
 */
@SpringBootTest
class CustomerControllerTest {

    @Autowired
    CustomerController customerController;

    @Test
    void getCustomerById() {
        System.out.println(customerController.getCustomerById(UUID.randomUUID()));
    }
}