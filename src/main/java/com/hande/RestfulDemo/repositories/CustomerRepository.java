package com.hande.RestfulDemo.repositories;

import com.hande.RestfulDemo.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/*
 * 15/01/2025
 * handebarkan
 */
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

}
