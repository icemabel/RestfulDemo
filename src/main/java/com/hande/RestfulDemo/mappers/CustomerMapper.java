package com.hande.RestfulDemo.mappers;

import com.hande.RestfulDemo.entities.Customer;
import com.hande.RestfulDemo.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO dto);

    CustomerDTO customerToCustomerDto(Customer customer);

}
