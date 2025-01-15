package com.hande.RestfulDemo.repositories;

import com.hande.RestfulDemo.entities.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/*
 * 15/01/2025
 * handebarkan
 */
public interface BeerRepository extends JpaRepository<Beer, UUID> {
}
