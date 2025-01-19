package com.hande.RestfulDemo.services;

import com.hande.RestfulDemo.model.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/*
 * 14/01/2025
 * handebarkan
 */
public interface BeerService {
    List<BeerDTO> listBeers();

    BeerDTO getBeerById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beer);

    Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer);

    Boolean deleteById(UUID beerId);

    Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beer);
}
