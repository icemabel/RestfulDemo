package com.hande.RestfulDemo.services;

import com.hande.RestfulDemo.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

/*
 * 14/01/2025
 * handebarkan
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private Map<UUID, CustomerDTO> customerMap;

    public CustomerServiceImpl() {
        this.customerMap = new HashMap<>();

        CustomerDTO customer1 = CustomerDTO.builder()
                .customerId(UUID.randomUUID())
                .customerName("John Doe")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        CustomerDTO customer2 = CustomerDTO.builder()
                .customerId(UUID.randomUUID())
                .customerName("Mark Twin")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        CustomerDTO customer3 = CustomerDTO.builder()
                .customerId(UUID.randomUUID())
                .customerName("Anna Marie")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(customer1.getCustomerId(), customer1);
        customerMap.put(customer2.getCustomerId(), customer2);
        customerMap.put(customer3.getCustomerId(), customer3);
    }

    @Override
    public CustomerDTO getCustomerById(UUID id) {
        return customerMap.get(id);
    }

    @Override
    public List<CustomerDTO> listCustomers() {

        return new ArrayList<>(customerMap.values());
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer) {
        CustomerDTO savedCustomer = CustomerDTO.builder()
                .customerId(UUID.randomUUID())
                .customerName(customer.getCustomerName())
                .version(customer.getVersion())
                .createdDate(customer.getCreatedDate())
                .lastModifiedDate(customer.getLastModifiedDate())
                .build();
        customerMap.put(savedCustomer.getCustomerId(), savedCustomer);

        return savedCustomer;
    }

    @Override
    public Optional<CustomerDTO> updateCustomerById(UUID customerId, CustomerDTO customer) {
        CustomerDTO existing = customerMap.get(customerId);
        existing.setCustomerName(customer.getCustomerName());
        existing.setVersion(customer.getVersion());

        return Optional.of(existing);
    }

    @Override
    public Boolean deleteCustomerById(UUID customerId) {

        customerMap.remove(customerId);
        return true;
    }

    @Override
    public void patchCustomerById(UUID customerId, CustomerDTO customer) {
        CustomerDTO existing = customerMap.get(customerId);

        if (StringUtils.hasText(customer.getCustomerName())) {
            existing.setCustomerName(customer.getCustomerName());
        }
    }

}
