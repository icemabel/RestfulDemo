package com.hande.RestfulDemo.controller;

import com.hande.RestfulDemo.model.Beer;
import com.hande.RestfulDemo.model.Customer;
import com.hande.RestfulDemo.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/*
 * 14/01/2025
 * handebarkan
 */
@Slf4j
@RequiredArgsConstructor
@RestController //convert to json body
@RequestMapping
public class CustomerController {
    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = "/api/v1/customer" + "/{customerId}";

    private final CustomerService customerService;

    @GetMapping(CUSTOMER_PATH)
    public List<Customer> listBCustomer() {
        return customerService.listCustomers();
    }

    @GetMapping(CUSTOMER_PATH_ID)
    public Customer getCustomerById(@PathVariable("customerId") UUID customerId) {
        log.debug("Get customerId by Id - in controller");

        return customerService.getCustomerById(customerId);
    }

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity handlePost(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveNewCustomer(customer);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/customer" + savedCustomer.getCustomerId().toString());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);

    }

    @PutMapping(CUSTOMER_PATH_ID)
    public ResponseEntity updateCustomerById(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer) {
        customerService.updateCustomerById(customerId, customer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity deleteById(@PathVariable("customerId") UUID customerId){
        customerService.deleteCustomerById(customerId);
        return new ResponseEntity((HttpStatus.NO_CONTENT));
    }

    @PatchMapping(CUSTOMER_PATH_ID)
    public ResponseEntity patchById(@PathVariable("customerId") UUID customerId, @RequestBody Customer customer) {
        customerService.patchCustomerById(customerId, customer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
